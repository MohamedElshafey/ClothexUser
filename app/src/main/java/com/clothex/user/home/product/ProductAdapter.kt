package com.clothex.user.home.product

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.data.Product
import com.clothex.user.databinding.AdapterItemProductGridBinding
import com.clothex.user.utils.setShapeColor

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ProductAdapter(private val list: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemProductGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemProductGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.viewModel = ProductItemViewModel(product)
            binding.oldPriceTV.paintFlags =
                binding.oldPriceTV.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            try {
                binding.tagTV.setShapeColor(Color.parseColor(product.tagColor))
            } catch (ignore: Exception){}
        }
    }

}