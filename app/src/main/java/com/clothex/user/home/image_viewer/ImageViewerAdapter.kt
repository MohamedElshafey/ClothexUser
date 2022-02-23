package com.clothex.user.home.image_viewer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.data.domain.model.product.Media
import com.clothex.user.databinding.AdapterImageViewerBinding
import com.clothex.user.utils.setImageFromUrl

/**
 * Created by Mohamed Elshafey on 10/19/2020.
 */

class ImageViewerAdapter(private val mediaList: List<Media>) :
    Adapter<ImageViewerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterImageViewerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mediaList[holder.adapterPosition])
    }

    override fun getItemCount(): Int = mediaList.size

    inner class ViewHolder(val binding: AdapterImageViewerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(media: Media) {
            binding.imageview.resetZoom()
            setImageFromUrl(binding.imageview, media.source)
        }
    }
}