package com.clothex.user.home.location.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.R
import com.clothex.user.data.SavedLocation
import com.clothex.user.databinding.AdapterItemSavedLocationBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class SavedLocationAdapter(private val list: List<SavedLocation>) :
    RecyclerView.Adapter<SavedLocationAdapter.ViewHolder>() {
    private var selectedItemPosition = list.indexOf(list.first { it.isSelected })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemSavedLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.layoutPosition]) { layoutPosition ->
            if (selectedItemPosition != -1) {
                list[selectedItemPosition].isSelected = false
                notifyItemChanged(selectedItemPosition)
            }
            selectedItemPosition = layoutPosition
            list[selectedItemPosition].isSelected = true
            notifyItemChanged(selectedItemPosition)
        }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemSavedLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(savedLocation: SavedLocation, onClick: (Int) -> Unit) {
            val context = binding.root.context
            if (savedLocation.isSelected) {
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
            binding.titleTV.text = savedLocation.title
            binding.subTitleTV.text = savedLocation.subTitle
            binding.root.setOnClickListener { onClick.invoke(layoutPosition) }
        }
    }

}