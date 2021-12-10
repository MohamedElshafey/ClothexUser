package com.clothex.user.my_items.minimal

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.user.data.my_items.MinimalProduct
import com.clothex.user.databinding.AdapterMinimalProductBinding

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class MinimalItemAdapter(private val list: List<MinimalProduct>) :
    Adapter<MinimalItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMinimalProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterMinimalProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(minimalProduct: MinimalProduct) {
            binding.colorItem.colorView.apply {
                val backgroundDrawable = background as GradientDrawable
                backgroundDrawable.setColor(Color.parseColor(minimalProduct.colorCode))
                background = backgroundDrawable
            }
            binding.viewModel = MinimalItemViewModel(minimalProduct)
        }
    }

}