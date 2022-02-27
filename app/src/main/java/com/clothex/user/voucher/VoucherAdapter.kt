package com.clothex.user.voucher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.user.R
import com.clothex.user.databinding.AdapterItemVoucherBinding
import com.clothex.user.utils.DateUtil.toLocalTimeZone
import com.clothex.user.utils.setImageFromUrl
import com.clothex.user.utils.setRotationByLocale

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */

class VoucherAdapter(
    private val list: List<Voucher>,
    private val onItemSelected: (Voucher) -> Unit,
    private val isArabic: Boolean
) :
    Adapter<VoucherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemVoucherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemVoucherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(voucher: Voucher) {
            binding.leadingIV.setRotationByLocale()
            with(voucher) {
                binding.titleTV.text = getTitle(isArabic)
                val context = binding.root.context
                if (redeemed) {
                    binding.subtitleTV.setText(R.string.voucher_redeemed)
                    binding.subtitleTV.setTextColor(
                        ContextCompat.getColor(context, R.color.tag_deal)
                    )
                    binding.grayOut(0.25f)
                } else {
                    val subTitle = String.format(
                        context.getString(R.string.expired_in),
                        expiryDate.toLocalTimeZone(context)
                    )
                    binding.subtitleTV.text = subTitle
                    binding.subtitleTV.setTextColor(
                        ContextCompat.getColor(context, R.color.island_aqua)
                    )
                }
                setImageFromUrl(binding.logoIV, logo.source)
            }
            binding.root.setOnClickListener {
                if (voucher.redeemed.not())
                    onItemSelected.invoke(voucher)
            }
        }
    }

    fun AdapterItemVoucherBinding.grayOut(percentage: Float) {
        logoIV.alpha = percentage
        titleTV.alpha = percentage
        leadingIV.alpha = percentage
    }

}