package com.clothex.user.voucher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.user.databinding.AdapterItemVoucherBinding
import com.clothex.user.utils.DateUtil.fromUTCToLocal
import com.clothex.user.utils.setImageFromUrl

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */

class VoucherAdapter(
    private val list: List<Voucher>,
    private val onItemSelected: (Voucher) -> Unit
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
            voucher.apply {
                binding.titleTV.text = title
                binding.subtitleTV.text = "Expired in ${voucher.expiryDate.fromUTCToLocal()}"
                setImageFromUrl(binding.logoIV, logo.source)
            }
            binding.root.setOnClickListener {
                onItemSelected.invoke(voucher)
            }

        }
    }

}