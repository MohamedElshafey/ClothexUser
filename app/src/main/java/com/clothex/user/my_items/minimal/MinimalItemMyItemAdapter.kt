package com.clothex.user.my_items.minimal

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.user.R
import com.clothex.user.databinding.AdapterMinimalProductMyItemBinding

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class MinimalItemMyItemAdapter(
    private val list: List<MyItem>,
    private val callback: (MyItem) -> Unit,
) :
    Adapter<MinimalItemMyItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMinimalProductMyItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.absoluteAdapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterMinimalProductMyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(minimalProduct: MyItem) {
            binding.root.setOnClickListener {
                callback.invoke(minimalProduct)
            }
            binding.colorItem.colorView.apply {
                val backgroundDrawable = background as GradientDrawable
                backgroundDrawable.setColor(Color.parseColor(minimalProduct.colorCode))
                background = backgroundDrawable
            }
            val viewModel = MinimalItemViewModel(minimalProduct)
            binding.viewModel = viewModel
            binding.priceTV.text =
                String.format(
                    binding.root.context.getString(R.string.egp),
                    viewModel.totalPrice.toInt()
                )
        }
    }
}