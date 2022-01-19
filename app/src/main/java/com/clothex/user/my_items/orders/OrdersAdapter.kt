package com.clothex.user.my_items.orders

import android.graphics.drawable.GradientDrawable
import android.os.CountDownTimer
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.clothex.user.R
import com.clothex.user.data.orders.Order
import com.clothex.user.databinding.AdapterItemOrderBinding
import com.clothex.user.utils.CustomTypefaceSpan
import com.clothex.user.utils.setImageFromUrl
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class OrdersAdapter(private var list: List<Order>, val onClickListener: (Order) -> Unit) :
    Adapter<OrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    fun filter(status: OrderStatus) {
        list = list.filter {
            it.orderStatus == status
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: AdapterItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            val context = binding.root.context
            with(order.shop) {
                binding.shopTitleTV.text = name
                binding.shopAddressTV.text = addressName
                setImageFromUrl(binding.logoIV, logoUrl)
            }
            binding.orderIdTV.text = "Order : ${order.orderId}"
            binding.placedDateTV.text = "Placed on 24 Nov 2021 1:30 PM"
            val backgroundDrawable = binding.statusTV.background as GradientDrawable
            order.orderStatus.let {
                binding.statusTV.text = context.getString(it.title)
                binding.statusTV.setTextColor(it.contentColor)
                backgroundDrawable.setColor(it.backgroundColor)
            }
            binding.statusTV.background = backgroundDrawable
            binding.bookedItemsTV.text = order.bookedItems.size.toString()
            val totalPrice = order.bookedItems.map { it.product.price * it.quantity }.sum()
            binding.totalPriceTV.text = totalPrice.toString()
            if (order.endTime > 0) {
                binding.orderValidContainer.visibility = View.VISIBLE
                binding.directionButton.visibility = View.VISIBLE
                binding.directionButton.setOnClickListener {
                    onClickListener.invoke(order)
                }
                val diffTimeStamp: Long = ((1639006661340 + 3600) - 1639006661340) * 1000L
                object : CountDownTimer(diffTimeStamp, 1000L) {
                    override fun onTick(millisUntilFinished: Long) {
                        val orderValidMsg = context.getString(R.string.order_valid_msg)
                        val date = Date(millisUntilFinished)
                        val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
                        val timeText = simpleDateFormat.format(date)
                        val validText = String.format(orderValidMsg, timeText)
                        val fontBold =
                            ResourcesCompat.getFont(context, R.font.ibm_plex_sans_arabic_bold)
                        val timeStartIndex = validText.indexOf(timeText)
                        val timeEndIndex = timeStartIndex + timeText.length
                        val spannableStringBuilder = SpannableStringBuilder(validText)
                        spannableStringBuilder.setSpan(
                            CustomTypefaceSpan("", fontBold),
                            timeStartIndex,
                            timeEndIndex,
                            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                        )
                        binding.orderValidTV.text = spannableStringBuilder
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