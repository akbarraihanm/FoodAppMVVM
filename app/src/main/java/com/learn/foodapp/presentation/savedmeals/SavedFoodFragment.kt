package com.learn.foodapp.presentation.savedmeals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.learn.foodapp.MainActivity
import com.learn.foodapp.R
import com.learn.foodapp.databinding.FragmentSavedFoodBinding
import com.learn.foodapp.presentation.meals.MealAdapter

class SavedFoodFragment : Fragment() {

    private lateinit var binding: FragmentSavedFoodBinding
    private lateinit var viewModel: SavedFoodViewModel
    private lateinit var mealAdapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedFoodBinding.bind(view)
        viewModel = (activity as MainActivity).savedFoodViewModel
        mealAdapter = (activity as MainActivity).mealAdapter
        initRecyclerView()
        viewModel.getSavedMeals().observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                binding.rvSavedMeals.visibility = GONE
                binding.tvNoData.visibility = VISIBLE
            } else {
                mealAdapter.differ.submitList(it)
                binding.rvSavedMeals.visibility = VISIBLE
                binding.tvNoData.visibility = GONE
            }
        })

        val itemTouchCallback = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val meal = mealAdapter.differ.currentList[pos]
                meal?.idMeal?.let { viewModel.deleteMeal(it) }
                Snackbar.make(view, "Meal's removed from bookmark", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveMeal(meal)
                    }
                    show()
                }
            }

        }

        ItemTouchHelper(itemTouchCallback).apply { attachToRecyclerView(binding.rvSavedMeals) }
    }

    private fun initRecyclerView() {
        binding.rvSavedMeals.apply {
            adapter = mealAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}