package com.clothex.user.home.image

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.user.data.Media
import com.clothex.user.databinding.AdapterImageViewBinding
import com.clothex.user.utils.setImageFromUrl

/**
 * Created by Mohamed Elshafey on 10/19/2020.
 */

class ImageAdapter(
    private val mediaList: List<Media>,
    private val imageSize: ImageSize = ImageSize.FULL_SIZE,
    private val scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_INSIDE,
    private val hasPadding: Boolean = false
) : Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterImageViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val layoutParams = binding.root.layoutParams
        val metrics = parent.context.resources.displayMetrics
        layoutParams.height = if (imageSize.height > 0) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            imageSize.height.toFloat(),
            metrics
        ).toInt() else imageSize.height
        layoutParams.width = if (imageSize.width > 0) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            imageSize.width.toFloat(),
            metrics
        ).toInt() else imageSize.width
        if (hasPadding) {
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, metrics).toInt().let {
                binding.imageview.setPadding(it, 0, it, 0)
            }
        } else {
            binding.imageview.setPadding(0, 0, 0, 0)
        }
        val shapeAppearanceModel = binding.imageview.shapeAppearanceModel.toBuilder()
            .setAllCornerSizes(imageSize.cornerRadius)
            .build()
        binding.imageview.shapeAppearanceModel = shapeAppearanceModel
        binding.root.layoutParams = layoutParams
        binding.imageview.scaleType = scaleType
        return ViewHolder(binding)
    }


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