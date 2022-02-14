package com.clothex.user.my_items.minimal

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.user.R
import com.clothex.user.databinding.AdapterMinimalProductBinding

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class MinimalItemAdapter(
    private val list: List<MyItem>,
    private val bookedItems: List<String>? = null
) :
    Adapter<MinimalItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMinimalProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterMinimalProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(minimalProduct: MyItem) {
            binding.colorItem.colorView.apply {
                val backgroundDrawable = background as GradientDrawable
                backgroundDrawable.setColor(Color.parseColor(minimalProduct.colorCode))
                background = backgroundDrawable
            }
            val viewModel = MinimalItemViewModel(minimalProduct)
            binding.viewModel = viewModel
            binding.priceTV.text =
                String.format(
                    binding.root.context.getString(R.string.egp_price_format_float),
                    viewModel.totalPrice
                )
            if (bookedItems != null) {
                val isBooked = bookedItems.contains(minimalProduct.id)
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        if (isBooked) R.color.booked_item else R.color.unbooked_item
                    )
                )
            }
        }
    }
}