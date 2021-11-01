package com.learn.foodapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.learn.foodapp.data.models.detailmeal.DetailMealData
import com.learn.foodapp.databinding.MealItemBinding

class SearchMealAdapter: RecyclerView.Adapter<SearchMealAdapter.ViewHolder>() {

    private val callback = object: DiffUtil.ItemCallback<DetailMealData>() {
        override fun areItemsTheSame(oldItem: DetailMealData, newItem: DetailMealData): Boolean {
            return oldItem.strMeal == newItem.strMeal
        }

        override fun areContentsTheSame(oldItem: DetailMealData, newItem: DetailMealData): Boolean {
            return  oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: MealItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DetailMealData) {
            binding.apply {
                with(data) {
                    Glide.with(ivMeal.context)
                        .load(strMealThumb)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                        .into(ivMeal)
                    tvMeal.text = strMeal
                }
            }
        }
    }
}