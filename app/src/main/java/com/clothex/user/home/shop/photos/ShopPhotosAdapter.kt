package com.clothex.user.home.shop.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.user.data.Media
import com.clothex.user.databinding.AdapterShopPhotoBinding
import com.clothex.user.utils.setImageFromUrl

/**
 * Created by Mohamed Elshafey on 10/19/2020.
 */

class ShopPhotosAdapter(private val mediaList: List<Media>) :
    Adapter<ShopPhotosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            AdapterShopPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mediaList[holder.adapterPosition])
    }

    override fun getItemCount(): Int = mediaList.size

    inner class ViewHolder(val binding: AdapterShopPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(media: Media) {
            setImageFromUrl(binding.imageView, media.source)
        }
    }
}