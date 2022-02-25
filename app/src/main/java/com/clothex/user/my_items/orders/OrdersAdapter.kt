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
import com.clothex.data.domain.model.order.MyOrder
import com.clothex.user.R
import com.clothex.user.databinding.AdapterItemOrderBinding
import com.clothex.user.utils.CustomTypefaceSpan
import com.clothex.user.utils.DateUtil.getDifferenceTimeStamp
import com.clothex.user.utils.DateUtil.toLocalTimeZone
import com.clothex.user.utils.setImageFromUrl


/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class OrdersAdapter(val onClickListener: OrderClickCallback, private val isArabic: Boolean) :
    Adapter<OrdersAdapter.ViewHolder>() {

    var list: List<MyOrder> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    fun updateData(list: List<MyOrder>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: AdapterItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: MyOrder) {
            val context = binding.root.context

            with(order.shop) {
                binding.shopTitleTV.text = getName(isArabic)
                setImageFromUrl(binding.logoIV, logo?.source)
            }
            with(order.branch) {
                binding.shopAddressTV.text = address?.getName(isArabic)
            }
            binding.orderIdTV.text = String.format(context.getString(R.string.order), order.orderId)
            binding.placedDateTV.text =
                String.format(
                    context.getString(R.string.placed_on),
                    order.placedOn?.toLocalTimeZone(context)
                )
            val backgroundDrawable = binding.statusTV.background as GradientDrawable
            order.state.let {
                binding.statusTV.text = context.getString(it.title)
                binding.statusTV.setTextColor(it.contentColor)
                backgroundDrawable.setColor(it.backgroundColor)
            }
            binding.statusTV.background = backgroundDrawable
            binding.bookedItemsTV.text = order.bookedItems.size.toString()
            val totalPrice = order.myItems.map { it.product.price * it.quantity }.sum()
            binding.totalPriceTV.text = totalPrice.toString()
            if (order.endTime != null && order.orderTimeStamp != null) {
                binding.orderValidContainer.visibility = View.VISIBLE
                binding.directionButton.visibility = View.VISIBLE
                binding.directionButton.setOnClickListener {
                    onClickListener.onGetDirectionClicked(order)
                }
                val diffTimeStamp = getDifferenceTimeStamp(order.endTime!!) ?: return
                if (diffTimeStamp > 0) {
                    object : CountDownTimer(diffTimeStamp, 1000L) {
                        override fun onTick(millisUntilFinished: Long) {
                            val orderValidMsg = context.getString(R.string.order_valid_msg)

                            val seconds = (millisUntilFinished / 1000).toInt() % 60
                            val minutes = (millisUntilFinished / (1000 * 60) % 60)
                            val hours = (millisUntilFinished / (1000 * 60 * 60) % 24)

                            val hoursInString =
                                String.format("%02d:%02d:%02d", hours, minutes, seconds)
                            val validText = String.format(orderValidMsg, hoursInString)
                            val fontBold =
                                ResourcesCompat.getFont(context, R.font.ibm_plex_sans_arabic_bold)
                            val timeStartIndex = validText.indexOf(hoursInString)
                            val timeEndIndex = timeStartIndex + hoursInString.length
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
                            binding.orderValidTV.setText(R.string.order_run_out_of_time)
                        }
                    }.start()
                } else {
                    binding.orderValidTV.setText(R.string.order_run_out_of_time)
                }
            } else {
                binding.orderValidContainer.visibility = View.GONE
                binding.directionButton.visibility = View.GONE
            }
            binding.root.setOnClickListener {
                onClickListener.onOrderSelected(order)
            }
        }
    }
}