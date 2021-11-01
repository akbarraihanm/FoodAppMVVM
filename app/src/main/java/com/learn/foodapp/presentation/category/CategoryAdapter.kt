package com.learn.foodapp.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.learn.foodapp.data.models.category.CategoryData
import com.learn.foodapp.databinding.CategoryItemBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val callback = object: DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.strCategory == newItem.strCategory
        }

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((CategoryData)->Unit)? = null

    fun setOnItemClickListener(listener: (CategoryData)->Unit) {
        onItemClickListener = listener
    }

    inner class CategoryViewHolder(private val binding: CategoryItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryData) {
            binding.apply {
                with(category) {
                    tvCategoryTitle.text = strCategory
                    Glide.with(ivCategory.context)
                        .load(strCategoryThumb)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                        .into(ivCategory)
                    btnChooseCategory.setOnClickListener { 
                        onItemClickListener?.let { it(category) }
                    }
                }
            }
        }
    }
}