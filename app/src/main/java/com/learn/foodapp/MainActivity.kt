package com.learn.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.learn.foodapp.databinding.ActivityMainBinding
import com.learn.foodapp.presentation.category.CategoryAdapter
import com.learn.foodapp.presentation.category.CategoryViewModel
import com.learn.foodapp.presentation.category.CategoryViewModelFactory
import com.learn.foodapp.presentation.meals.MealAdapter
import com.learn.foodapp.presentation.savedmeals.SavedFoodViewModel
import com.learn.foodapp.presentation.savedmeals.SavedFoodViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var categoryViewModel: CategoryViewModel
    @Inject
    lateinit var categoryFactory: CategoryViewModelFactory
    @Inject
    lateinit var categoryAdapter: CategoryAdapter
    @Inject
    lateinit var mealAdapter: MealAdapter
    lateinit var savedFoodViewModel: SavedFoodViewModel
    @Inject
    lateinit var savedFoodFactory: SavedFoodViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryViewModel = ViewModelProvider(this,
            categoryFactory)[CategoryViewModel::class.java]
        savedFoodViewModel = ViewModelProvider(this,
            savedFoodFactory)[SavedFoodViewModel::class.java]
        initView()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavbar.setupWithNavController(navController)
    }
}