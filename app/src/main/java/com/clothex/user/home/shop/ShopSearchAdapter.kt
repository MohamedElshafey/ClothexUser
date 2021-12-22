package com.clothex.user.home.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.data.Shop
import com.clothex.user.databinding.AdapterItemShopSearchBinding
import com.clothex.user.home.image.ImageAdapter
import com.clothex.user.home.image.ImageSize

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ShopSearchAdapter(private val list: List<Shop>, val callback: (Shop) -> Unit) :
    RecyclerView.Adapter<ShopSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemShopSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemShopSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shop: Shop) {
            binding.viewModel = ShopItemViewModel(shop)
            binding.root.setOnClickListener { callback.invoke(shop) }
            binding.photosRV.adapter =
                ImageAdapter(
                    shop.photos,
                    ImageSize.SMALL_SQUARE,
                    ImageView.ScaleType.CENTER_CROP,
                    true
                )
        }
    }

}