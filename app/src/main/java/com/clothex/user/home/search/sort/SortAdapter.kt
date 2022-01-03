package com.clothex.user.home.search.sort

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.body.SortEnum
import com.clothex.user.R
import com.clothex.user.data.SortItem
import com.clothex.user.databinding.AdapterItemSortBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class SortAdapter(private val list: List<SortItem>, val callback: (SortEnum) -> Unit) :
    RecyclerView.Adapter<SortAdapter.ViewHolder>() {
    private var selectedItemPosition = list.indexOf(list.first { it.isSelected })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemSortBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.layoutPosition]) { layoutPosition, sortEnum ->
            if (selectedItemPosition != -1) {
                list[selectedItemPosition].isSelected = false
                notifyItemChanged(selectedItemPosition)
            }
            selectedItemPosition = layoutPosition
            list[selectedItemPosition].isSelected = true
            notifyItemChanged(selectedItemPosition)
            callback.invoke(sortEnum)
        }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemSortBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sortItem: SortItem, onClick: (Int, SortEnum) -> Unit) {
            val context = binding.root.context
            if (sortItem.isSelected) {
                val color = ContextCompat.getColor(
                    context,
                    R.color.island_aqua_alpha
                )
                binding.root.setBackgroundColor(color)
                binding.selectedIV.visibility = VISIBLE
            } else {
                binding.root.setBackgroundColor(Color.WHITE)
                binding.iconIV.colorFilter = null
                binding.selectedIV.visibility = GONE
            }
            binding.titleTV.text = sortItem.title
            binding.iconIV.setImageResource(sortItem.iconRes)
            binding.root.setOnClickListener { onClick.invoke(layoutPosition, sortItem.sortEnum) }
        }
    }

}