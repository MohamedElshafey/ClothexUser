package com.clothex.user.my_items.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.user.data.my_items.MyItemGroup
import com.clothex.user.databinding.AdapterMyItemBinding
import com.clothex.user.my_items.minimal.MinimalItemAdapter
import com.clothex.user.utils.setImageFromUrl

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class MyItemsAdapter(private val list: List<MyItemGroup>, val callback: (MyItemGroup) -> Unit) :
    Adapter<MyItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterMyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myItemGroup: MyItemGroup) {
            with(myItemGroup) {
                binding.titleTV.text = shop.name
                binding.addressTV.text = branch.address?.name
                setImageFromUrl(binding.logoIV, shop.logo?.source)
            }
            binding.itemsRV.adapter = MinimalItemAdapter(myItemGroup.myItems.take(2))
            val myItemsCount = myItemGroup.myItems.size
            if (myItemsCount > 2)
                binding.itemsCount.text = String.format("+%s items", myItemsCount - 2)
            binding.itemsCount.isGone = myItemsCount <= 2
            binding.root.setOnClickListener { callback.invoke(myItemGroup) }
        }
    }

}