package com.clothex.user.home.my_items.orders

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.user.R
import com.clothex.user.data.orders.Order
import com.clothex.user.data.orders.OrderStatus
import com.clothex.user.databinding.AdapterItemOrderBinding
import com.clothex.user.utils.setImageFromUrl
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class OrdersAdapter(private val list: List<Order>) : Adapter<OrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            with(order.shop) {
                binding.shopTitleTV.text = name
                binding.shopAddressTV.text = addressName
                setImageFromUrl(binding.logoIV, logoUrl)
            }
            binding.orderIdTV.text = "Order : ${order.orderId}"
            binding.placedDateTV.text = "Placed on 24 Nov 2021 1:30 PM"
            val backgroundDrawable = binding.statusTV.background as GradientDrawable
            when (order.orderStatus) {
                OrderStatus.PENDING -> {
                    binding.statusTV.text = "Pending"
                    binding.statusTV.setTextColor(Color.parseColor("#974b00"))
                    backgroundDrawable.setColor(Color.parseColor("#ffedaf"))
                }
                OrderStatus.ACTIVE -> {
                    binding.statusTV.setTextColor(Color.parseColor("#10c935"))
                    backgroundDrawable.setColor(Color.parseColor("#1A10c935"))
                    binding.statusTV.text = "Active"
                }
                OrderStatus.REJECT -> {
                    binding.statusTV.setTextColor(Color.parseColor("#EC255A"))
                    backgroundDrawable.setColor(Color.parseColor("#1AEC255A"))
                    binding.statusTV.text = "Rejected"
                }
            }
            binding.statusTV.background = backgroundDrawable
            binding.bookedItemsTV.text = order.bookedItems.size.toString()
            val totalPrice = order.bookedItems.map { it.price * it.quantity }.sum()
            binding.totalPriceTV.text = totalPrice.toString()
            if (order.endTime > 0) {
                binding.orderValidContainer.visibility = View.VISIBLE
                binding.directionButton.visibility = View.VISIBLE
                val diffTimeStamp: Long = (1639006661340 + 86400 + 86400) - 1639006661340
                object : CountDownTimer(diffTimeStamp, 1000L) {
                    override fun onTick(millisUntilFinished: Long) {
                        val orderValidMsg =
                            binding.orderValidTV.context.getString(R.string.order_valid_msg)
                        val date = Date(millisUntilFinished)
                        val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
                        binding.orderValidTV.text =
                            String.format(orderValidMsg, simpleDateFormat.format(date))
                    }

                    override fun onFinish() {

                    }
                }.start()
            } else {
                binding.orderValidContainer.visibility = View.GONE
                binding.directionButton.visibility = View.GONE
            }
        }
    }

}