package com.example.project.presentation.adapters.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project.data.model.Banner
import com.example.project.databinding.BannerItemBinding

class BannerAdapter : ListAdapter<Banner, BannerViewHolder>(
    BannerItemCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}