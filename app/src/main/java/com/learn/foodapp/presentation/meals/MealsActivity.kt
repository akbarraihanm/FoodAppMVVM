package com.learn.foodapp.presentation.meals

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.learn.foodapp.data.models.category.CategoryData
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.databinding.ActivityMealsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealsBinding
    private var data: CategoryData? = null
    private lateinit var viewModel: MealViewModel

    @Inject
    lateinit var mealAdapter: MealAdapter
    @Inject
    lateinit var viewModelFactory: MealViewModelFactory

    companion object {
        var extraBundle = "EXTRA_BUNDLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent.getParcelableExtra(extraBundle)
        setAppBar()
        viewModel = ViewModelProvider(this, viewModelFactory)[MealViewModel::class.java]
        initRecyclerView()
        showMeals()
    }

    private fun setAppBar() {
        supportActionBar?.title = data?.strCategory
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initRecyclerView() {
        binding.rvMeal.apply {
            adapter = mealAdapter
            layoutManager = LinearLayoutManager(this@MealsActivity)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showMeals() {
        binding.apply {
            Glide.with(ivCategory.context)
                .load(data?.strCategoryThumb)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .into(ivCategory)
            tvCategoryDescription.text = data?.strCategoryDescription
            tvHereAre.text = "Here are list of ${data?.strCategory}'s food"
        }
        viewModel.getMeals(data?.strCategory ?: "Beef")
        viewModel.meals.observe(this, { res ->
            when(res) {
                is Resource.Success -> {
                    hideLoading()
                    res.data?.let { mealAdapter.differ.submitList(it.meals.toList()) }
                    binding.rvMeal.visibility = VISIBLE
                    binding.tvCategoryDescription.visibility = VISIBLE
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

    private fun showLoading() {
        binding.loadingMeals.visibility = VISIBLE
        binding.rvMeal.visibility = GONE
        binding.tvCategoryDescription.visibility = GONE
    }

    private fun hideLoading() {
        binding.loadingMeals.visibility = GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}