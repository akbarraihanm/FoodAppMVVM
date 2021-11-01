package com.learn.foodapp.presentation.detailmeal

import android.graphics.Color
import android.os.Bundle
import android.text.util.Linkify
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.learn.foodapp.R
import com.learn.foodapp.data.models.meal.MealData
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.databinding.ActivityDetailMealBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailMealActivity : AppCompatActivity() {

    companion object {
        var extra = "EXTRA"
    }

    private lateinit var binding: ActivityDetailMealBinding
    private lateinit var viewModel: DetailMealViewModel
    private var data: MealData? = null
    private var menuItem: Menu? = null
    private var isClicked = false

    @Inject
    lateinit var factory: DetailMealViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent.getParcelableExtra(extra)
        setAppbar()
        viewModel = ViewModelProvider(this, factory)[DetailMealViewModel::class.java]
        showDetailMeal()
    }

    private fun setAppbar() {
        supportActionBar?.title = "Detail Meal"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetailMeal() {
        binding.apply {
            Glide.with(ivMeal.context)
                .load(data?.strMealThumb)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .into(ivMeal)
            tvMealTitle.text = data?.strMeal
        }
        viewModel.getDetailMeal(data?.idMeal ?: "0")
        viewModel.detail.observe(this, { res ->
            when(res) {
                is Resource.Success -> {
                    hideLoading()
                    res.data?.let {
                        binding.apply {
                            tvFoodRegion.text = it.meals[0].strArea
                            tvCookingInstructions.text = it.meals[0].strInstructions
                            tvYoutubeUrl.text = it.meals[0].strYoutube
                            tvSourceUrl.text = it.meals[0].strSource
                            layoutDetailMeal.visibility = VISIBLE
                            Linkify.addLinks(tvYoutubeUrl, Linkify.ALL)
                            Linkify.addLinks(tvSourceUrl, Linkify.ALL)
                            tvYoutubeUrl.setLinkTextColor(Color.BLUE)
                            tvSourceUrl.setLinkTextColor(Color.BLUE)
                        }
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    res.message?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun saveMeal() {
        viewModel.saveMeal(
            MealData(null, data?.strMeal, data?.strMealThumb,
                data?.idMeal)
        )
        isClicked = true
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_24)
    }

    private fun getMeal() {
        val detail = data?.idMeal?.let { viewModel.getMeal(it) }
        detail?.observe(this, {
            if (it != null) {
                isClicked = true
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_24)
            } else {
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_border_24)
            }
        })

    }

    private fun showLoading() {
        binding.apply {
            loadingDetail.visibility = VISIBLE
            layoutDetailMeal.visibility = GONE
        }
    }

    private fun hideLoading() {
        binding.loadingDetail.visibility = GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu)
        menuItem = menu
        getMeal()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.btn_save_meal -> {
                if (!isClicked) {
                    saveMeal()
                    Snackbar.make(binding.root, "Meal's bookmarked", Snackbar.LENGTH_LONG).show()
                } else {
                    data?.idMeal?.let { viewModel.deleteMeal(it) }
                    isClicked = false
                    Snackbar.make(binding.root, "Meal's removed from bookmark", Snackbar.LENGTH_LONG).show()
                    menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_border_24)
                }
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }
}