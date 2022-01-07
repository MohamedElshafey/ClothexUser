package com.clothex.user.home.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.user.data.Shop
import com.clothex.user.databinding.AdapterItemShopBinding

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ShopAdapter(private val list: List<HomeShop>, val callback: (HomeShop) -> Unit) :
    RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemShopBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shop: HomeShop) {
            binding.viewModel = ShopItemViewModel(shop)
            binding.root.setOnClickListener { callback.invoke(shop) }
        }
    }

}