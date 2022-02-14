package com.clothex.user.home.product

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.user.R
import com.clothex.user.databinding.AdapterItemProductGridBinding
import com.clothex.user.home.product.ProductAdapter.ViewHolder
import com.clothex.user.utils.setShapeColor

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ProductAdapter : RecyclerView.Adapter<ViewHolder> {
    var products = mutableListOf<HomeProduct>()
    var callback: ((HomeProduct) -> Unit)? = null

    constructor(list: List<HomeProduct>? = null, callback: (HomeProduct) -> Unit) : super() {
        list?.let { products.addAll(it) }
        this.callback = callback
    }

    fun append(list: List<HomeProduct>) {
        products.addAll(list)
        notifyItemRangeInserted(this.products.size - list.size, this.products.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemProductGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(products[holder.adapterPosition])

    override fun getItemCount(): Int = products.size

    @SuppressLint("NotifyDataSetChanged")
    fun reset() {
        products.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: AdapterItemProductGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: HomeProduct) {
            val viewModel = ProductItemViewModel(product)
            val context = binding.root.context
            binding.viewModel = viewModel
            with(context.getString(R.string.egp_price_format_float)) {
                binding.priceTV.text = String.format(this, viewModel.totalPrice.toFloat())
                if (viewModel.oldPrice != null)
                    binding.oldPriceTV.text = String.format(this, viewModel.oldPrice.toFloat())
                else
                    binding.oldPriceTV.text = null
            }

            with(context.getString(R.string.egp)) {
                val hasDiscount = viewModel.savedAmount != null || viewModel.savedPercentage != null
                binding.discountTV.isVisible = hasDiscount
                if (hasDiscount) {
                    val savedAmountTitle = context.getString(R.string.saved_amount_format)
                    val discountTitle = savedAmountTitle +
                            ": ${String.format(this, viewModel.savedAmount)}" +
                            " (${viewModel.savedPercentage}%) "
                    binding.discountTV.text = discountTitle
                }
            }

            binding.oldPriceTV.paintFlags =
                binding.oldPriceTV.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            try {
                binding.tagTV.setShapeColor(Color.parseColor(product.tagColor))
            } catch (ignore: Exception) {
            }
            binding.root.setOnClickListener { callback?.invoke(product) }
        }
    }

}