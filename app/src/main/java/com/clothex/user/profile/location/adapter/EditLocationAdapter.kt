package com.clothex.user.profile.location.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.local.room.entity.SavedLocation
import com.clothex.user.databinding.AdapterItemEditSavedLocationBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class EditLocationAdapter(
    private val list: List<SavedLocation>,
    private val callback: EditLocationCallback
) :
    RecyclerView.Adapter<EditLocationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemEditSavedLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.layoutPosition]) { layoutPosition ->
            callback.onItemDeleted(list[layoutPosition])
            notifyItemChanged(layoutPosition)
        }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemEditSavedLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(savedLocation: SavedLocation, onClick: (Int) -> Unit) {
            binding.titleTV.text = savedLocation.title
            binding.subTitleTV.text = savedLocation.subTitle
            binding.deleteIV.setOnClickListener { onClick.invoke(layoutPosition) }
        }
    }

}