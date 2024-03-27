package com.example.project.presentation.adapters.banner

import androidx.recyclerview.widget.DiffUtil
import com.example.project.data.model.Banner

class BannerItemCallback : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.imgRes == newItem.imgRes
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }
}