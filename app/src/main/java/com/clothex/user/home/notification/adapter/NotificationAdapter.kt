package com.clothex.user.home.notification.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.R
import com.clothex.user.data.Notification
import com.clothex.user.databinding.AdapterItemNotificationBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class NotificationAdapter(private val list: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemNotificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: Notification) {
            val context = binding.root.context
            val backgroundColor = if (notification.isRead) Color.WHITE else ContextCompat.getColor(
                context,
                R.color.island_aqua_alpha
            )
            binding.root.setBackgroundColor(backgroundColor)
            binding.viewModel = NotificationItemViewModel(notification)
            binding.root.setOnClickListener {
                notification.isRead = true
                notifyItemChanged(layoutPosition)
            }
        }
    }
}