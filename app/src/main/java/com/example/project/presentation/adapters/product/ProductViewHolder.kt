package com.example.project.presentation.adapters.product

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.data.model.Meal
import com.example.project.databinding.ProductItemBinding

class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Meal) {
        binding.tvTitle.text = product.strMeal
        binding.tvDesc.text = product.strCategory
        Glide.with(binding.root)
            .load(product.strMealThumb)
            .into(binding.ivProduct)
    }
}