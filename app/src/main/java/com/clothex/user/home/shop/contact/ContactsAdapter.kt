package com.clothex.user.home.shop.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.databinding.AdapterItemContactBinding

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ContactsAdapter(private val list: List<String>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(string: String) {

        }
    }

}