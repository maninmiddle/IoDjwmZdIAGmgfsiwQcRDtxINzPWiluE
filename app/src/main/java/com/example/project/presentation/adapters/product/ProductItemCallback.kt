package com.example.project.presentation.adapters.product

import androidx.recyclerview.widget.DiffUtil
import com.example.project.data.model.Meal

class ProductItemCallback: DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }
}