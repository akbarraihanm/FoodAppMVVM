package com.learn.foodapp.presentation.meals

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.learn.foodapp.data.models.meal.MealData
import com.learn.foodapp.databinding.MealItemBinding
import com.learn.foodapp.presentation.detailmeal.DetailMealActivity

class MealAdapter: RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private val callback = object: DiffUtil.ItemCallback<MealData>() {
        override fun areItemsTheSame(oldItem: MealData, newItem: MealData): Boolean {
            return oldItem.strMeal == newItem.strMeal
        }

        override fun areContentsTheSame(oldItem: MealData, newItem: MealData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = differ.currentList[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MealViewHolder(private val binding: MealItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: MealData) {
            binding.apply {
                with(meal) {
                    tvMeal.text = strMeal
                    Glide.with(ivMeal.context)
                        .load(strMealThumb)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                        .into(ivMeal)
                }
                binding.root.setOnClickListener {
                    val i = Intent(binding.root.context, DetailMealActivity::class.java).apply {
                        putExtra(DetailMealActivity.extra, meal)
                    }
                    binding.root.context.startActivity(i)
                }
            }
        }

    }
}