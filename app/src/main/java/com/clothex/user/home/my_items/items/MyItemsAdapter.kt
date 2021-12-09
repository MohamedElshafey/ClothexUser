package com.clothex.user.home.my_items.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.user.data.my_items.MyItem
import com.clothex.user.databinding.AdapterMyItemBinding
import com.clothex.user.home.my_items.minimal.MinimalItemAdapter
import com.clothex.user.utils.setImageFromUrl

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class MyItemsAdapter(private val list: List<MyItem>, val callback: (MyItem) -> Unit) :
    Adapter<MyItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterMyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myItem: MyItem) {
            with(myItem.shop) {
                binding.titleTV.text = name
                binding.addressTV.text = addressName
                setImageFromUrl(binding.logoIV, logoUrl)
            }
            binding.itemsRV.adapter = MinimalItemAdapter(myItem.myItems.take(2))
            binding.itemsCount.text = "+${(myItem.myItems.size - 2)} items"
            binding.root.setOnClickListener { callback.invoke(myItem) }
        }
    }

}