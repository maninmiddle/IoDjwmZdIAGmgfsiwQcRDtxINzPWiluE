package com.example.project.presentation.adapters.banner

import androidx.recyclerview.widget.RecyclerView
import com.example.project.data.model.Banner
import com.example.project.databinding.BannerItemBinding

class BannerViewHolder(val binding: BannerItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(banner: Banner) {
        binding.ivBanner.setImageResource(banner.imgRes)
    }
}