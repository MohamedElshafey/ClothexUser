package com.clothex.user.home.shop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.user.databinding.AdapterItemShopBinding

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    var shops = mutableListOf<HomeShop>()
    var callback: ((HomeShop) -> Unit)? = null

    constructor(list: List<HomeShop>? = null, callback: (HomeShop) -> Unit) : super() {
        list?.let { shops.addAll(it) }
        this.callback = callback
    }

    constructor()

    fun update(list: List<HomeShop>) {
        shops.addAll(list)
        notifyItemRangeInserted(this.shops.size - list.size, this.shops.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemShopBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(shops[holder.absoluteAdapterPosition])

    override fun getItemCount(): Int = shops.size

    @SuppressLint("NotifyDataSetChanged")
    fun reset() {
        shops.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: AdapterItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shop: HomeShop) {
            binding.viewModel = ShopItemViewModel(shop)
            binding.root.setOnClickListener { callback?.invoke(shop) }
        }
    }

}