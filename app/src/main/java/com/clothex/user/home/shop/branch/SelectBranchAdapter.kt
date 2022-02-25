package com.clothex.user.home.shop.branch

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.product.Branch
import com.clothex.user.R
import com.clothex.user.databinding.AdapterItemSavedLocationBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class SelectBranchAdapter(
    private val list: List<Branch>,
    private var selectedItemIndex: Int = 0,
    private val selectBranchCallback: SelectBranchCallback,
    private val isArabic: Boolean
) :
    RecyclerView.Adapter<SelectBranchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemSavedLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.layoutPosition]) { layoutPosition ->
            val oldItemIndex = selectedItemIndex
            selectedItemIndex = layoutPosition
            notifyItemChanged(oldItemIndex)
            notifyItemChanged(selectedItemIndex)
            selectBranchCallback.onBranchSelected(list[selectedItemIndex])
        }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemSavedLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(branch: Branch, onClick: (Int) -> Unit) {
            val context = binding.root.context
            if (selectedItemIndex == adapterPosition) {
                val color = ContextCompat.getColor(
                    context,
                    R.color.island_aqua_alpha
                )
                val filterColor = ContextCompat.getColor(
                    context,
                    R.color.island_aqua
                )
                binding.root.setBackgroundColor(color)
                binding.iconIV.setColorFilter(filterColor)
                binding.selectedIV.visibility = VISIBLE
            } else {
                binding.root.setBackgroundColor(Color.WHITE)
                binding.iconIV.colorFilter = null
                binding.selectedIV.visibility = GONE
            }
            binding.titleTV.text = branch.name
            binding.subTitleTV.text = branch.address?.getName(isArabic)
            binding.root.setOnClickListener { onClick.invoke(layoutPosition) }
        }
    }

}