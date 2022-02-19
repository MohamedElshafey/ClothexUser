package com.clothex.user.home.notification.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.notification.Notification
import com.clothex.user.R
import com.clothex.user.databinding.AdapterItemNotificationBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class NotificationAdapter(private val notificationCallback: NotificationCallback) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    private var notificationList: List<Notification> = listOf()

    fun setData(list: List<Notification>) {
        this.notificationList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemNotificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(notificationList[holder.adapterPosition])

    override fun getItemCount(): Int = notificationList.size

    inner class ViewHolder(val binding: AdapterItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: Notification) {
            val context = binding.root.context
            val backgroundColor = if (notification.read) Color.WHITE else ContextCompat.getColor(
                context,
                R.color.island_aqua_alpha
            )
            binding.root.setBackgroundColor(backgroundColor)
            binding.viewModel = NotificationItemViewModel(notification)
            binding.root.setOnClickListener {
                notification.read = true
                notificationCallback.onItemClicked(notification)
                notifyItemChanged(layoutPosition)
            }
        }
    }
}