package com.clothex.user.voucher.shop_with_branch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.ShopAndBranch
import com.clothex.user.databinding.AdapterVoucherShopBinding
import com.clothex.user.utils.setImageFromUrl

class VoucherShopAdapter(private val list: List<ShopAndBranch>, private val isArabic: Boolean) :
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
        fun bind(item: ShopAndBranch) {
            with(item) {
                binding.shopTitleTV.text = shop.getName(isArabic)
                binding.addressTV.text = branch.address?.getName(isArabic)
                setImageFromUrl(binding.shopLogoIV, shop.logo?.source)
            }
        }
    }
}