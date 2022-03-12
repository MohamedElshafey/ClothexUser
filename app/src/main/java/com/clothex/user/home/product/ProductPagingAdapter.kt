package com.clothex.user.home.product

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.user.R
import com.clothex.user.databinding.AdapterItemProductGridBinding
import com.clothex.user.home.product.ProductPagingAdapter.ViewHolder
import com.clothex.user.utils.setShapeColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ProductComparator : DiffUtil.ItemCallback<HomeProduct>() {
    override fun areItemsTheSame(oldItem: HomeProduct, newItem: HomeProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HomeProduct, newItem: HomeProduct): Boolean {
        return oldItem == newItem
    }
}

class ProductPagingAdapter(
    private val callback: (HomeProduct) -> Unit
) : PagingDataAdapter<HomeProduct, ViewHolder>(ProductComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemProductGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    inner class ViewHolder(val binding: AdapterItemProductGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: HomeProduct) {
            val viewModel = ProductItemLanguageViewModel(product)
            val context = binding.root.context
            binding.viewModel = viewModel
            with(context.getString(R.string.egp)) {
                binding.priceTV.text = String.format(this, viewModel.totalPrice)
                if (viewModel.oldPrice != null)
                    binding.oldPriceTV.text = String.format(this, viewModel.oldPrice)
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
                            String.format(" (%d", viewModel.savedPercentage) + "%)"
                    binding.discountTV.text = discountTitle
                }
            }

            binding.oldPriceTV.paintFlags =
                binding.oldPriceTV.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            try {
                binding.tagTV.setShapeColor(Color.parseColor(product.tagColor))
            } catch (ignore: Exception) {
            }
            binding.root.setOnClickListener { callback.invoke(product) }
        }
    }

    suspend fun reset() = withContext(Dispatchers.IO) {
        submitData(PagingData.empty())
    }
}