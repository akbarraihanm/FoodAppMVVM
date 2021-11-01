package com.learn.foodapp.presentation.search

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.databinding.ActivitySearchMealBinding
import com.learn.foodapp.databinding.ToolbarItemBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchMealActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchMealBinding
    private lateinit var toolbar: ToolbarItemBinding
    private lateinit var viewModel: SearchMealViewModel

    @Inject
    lateinit var searchMealAdapter: SearchMealAdapter
    @Inject
    lateinit var factory: SearchMealViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        initRecyclerView()
        viewModel = ViewModelProvider(this, factory)[SearchMealViewModel::class.java]
        searchMealQuery()
    }

    private fun setupToolbar() {
        toolbar = binding.toolbar
        supportActionBar?.customView = toolbar.root
        toolbar.btnBack.setOnClickListener { finish() }
        toolbar.root.elevation = 8F
    }

    private fun initRecyclerView() {
        binding.rvMeal.apply {
            adapter = searchMealAdapter
            layoutManager = LinearLayoutManager(this@SearchMealActivity)
        }
    }

    private fun searchMealQuery() {
        toolbar.svMeal.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    getSearchedMeal(p0)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun getSearchedMeal(s: String) {
        viewModel.searchMeal(s)
        viewModel.searchMeal.observe(this, { res ->
            when(res) {
                is Resource.Success -> {
                    hideLoading()
                    binding.apply {
                        val list = res.data?.meals ?: ArrayList()
                        if (list.isNotEmpty()) {
                            searchMealAdapter.differ.submitList(list)
                            tvNoData.visibility = GONE
                            rvMeal.visibility = VISIBLE
                        } else tvNoData.visibility = VISIBLE
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    res.message?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> { showLoading() }
            }
        })
    }

    private fun showLoading() {
        binding.apply {
            loadingMeals.visibility = VISIBLE
            tvNoData.visibility = GONE
            rvMeal.visibility = GONE
        }
    }

    private fun hideLoading() {
        binding.loadingMeals.visibility = GONE
    }
}