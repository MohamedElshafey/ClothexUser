package com.clothex.user.home.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.user.data.Media
import com.clothex.user.databinding.AdapterImageViewBinding
import com.clothex.user.utils.setImageFromUrl

/**
 * Created by Mohamed Elshafey on 10/19/2020.
 */

class ImageAdapter(private val mediaList: List<Media>) : Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            AdapterImageViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mediaList[holder.adapterPosition])
    }

    override fun getItemCount(): Int = mediaList.size

    inner class ViewHolder(val binding: AdapterImageViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(media: Media) {
            setImageFromUrl(binding.imageview, media.source)
        }
    }
}