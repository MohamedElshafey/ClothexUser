package com.clothex.user.offer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.data.domain.model.offer.Offer
import com.clothex.user.R
import com.clothex.user.databinding.AdapterItemVoucherBinding
import com.clothex.user.utils.DateUtil.toLocalTimeZone
import com.clothex.user.utils.setImageFromUrl
import com.clothex.user.utils.setRotationByLocale

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */

class OfferAdapter(
    private val list: List<Offer>,
    private val onItemSelected: (Offer) -> Unit,
    private val isArabic: Boolean
) :
    Adapter<OfferAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemVoucherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.absoluteAdapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemVoucherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(offer: Offer) {
            binding.leadingIV.setRotationByLocale()
            offer.apply {
                binding.titleTV.text = getTitle(isArabic)
                val context = binding.root.context
                val subTitle = String.format(
                    context.getString(R.string.expired_in),
                    offer.expiryDate.toLocalTimeZone(context)
                )
                binding.subtitleTV.text = subTitle
                setImageFromUrl(binding.logoIV, logo.source)
            }
            binding.root.setOnClickListener {
                onItemSelected.invoke(offer)
            }
        }
    }

}