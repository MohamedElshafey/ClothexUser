package com.clothex.user.voucher.shop_with_branch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.voucher.VoucherShop
import com.clothex.user.databinding.AdapterVoucherShopBinding
import com.clothex.user.utils.setImageFromUrl

class VoucherShopAdapter(private val list: List<VoucherShop>) :
    RecyclerView.Adapter<VoucherShopAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            AdapterVoucherShopBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: AdapterVoucherShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VoucherShop) {
            with(item) {
                binding.shopTitleTV.text = shop.name
                binding.addressTV.text = branch.address?.name
                setImageFromUrl(binding.shopLogoIV, shop.logo?.source)
            }
        }
    }
}