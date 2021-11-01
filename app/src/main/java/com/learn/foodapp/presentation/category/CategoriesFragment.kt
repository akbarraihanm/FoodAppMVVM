package com.learn.foodapp.presentation.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.foodapp.MainActivity
import com.learn.foodapp.R
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.databinding.FragmentCategoriesBinding
import com.learn.foodapp.presentation.meals.MealsActivity
import com.learn.foodapp.presentation.search.SearchMealActivity

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).categoryViewModel
        binding = FragmentCategoriesBinding.bind(view)
        initRecyclerView()
        showCategories()
        adapter.setOnItemClickListener {
            val i = Intent(activity, MealsActivity::class.java).apply {
                putExtra(MealsActivity.extraBundle, it)
            }
            startActivity(i)
        }
        binding.btnSearch.setOnClickListener {
            val i = Intent(activity, SearchMealActivity::class.java)
            startActivity(i)
        }
    }

    private fun initRecyclerView() {
        adapter = (activity as MainActivity).categoryAdapter
        binding.apply {
            rvFoodCategory.adapter = adapter
            rvFoodCategory.layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showCategories() {
        viewModel.getCategories()
        viewModel.categories.observe(viewLifecycleOwner, { res ->
            when(res) {
                is Resource.Success -> {
                    hideLoading()
                    res.data?.let {
                        adapter.differ.submitList(it.categories.toList())
                    }
                    binding.rvFoodCategory.visibility = VISIBLE
                }
                is Resource.Error -> {
                    hideLoading()
                    res.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun showLoading() {
        binding.loadingCategories.visibility = VISIBLE
        binding.rvFoodCategory.visibility = GONE
    }

    private fun hideLoading() {
        binding.loadingCategories.visibility = GONE
    }
}