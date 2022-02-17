package com.clothex.user.voucher

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
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
            voucher.apply {
                binding.titleTV.text = getTitle(isArabic)
                val context = binding.root.context
                val subTitle = String.format(
                    context.getString(R.string.expired_in),
                    voucher.expiryDate.toLocalTimeZone(context)
                )
                binding.subtitleTV.text = subTitle
                setImageFromUrl(binding.logoIV, logo.source)
                binding.container.setBackgroundColor(
                    if (voucher.redeemed) Color.parseColor("#30000000")
                    else Color.WHITE
                )
            }
            binding.root.setOnClickListener {
                if (voucher.redeemed.not())
                    onItemSelected.invoke(voucher)
            }
        }
    }

}