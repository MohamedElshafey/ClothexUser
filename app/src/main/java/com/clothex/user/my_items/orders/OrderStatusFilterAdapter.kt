package com.clothex.user.my_items.orders

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.clothex.user.R


/**
 * Created by Mohamed Elshafey on 20/12/2021.
 */
class OrderStatusFilterAdapter(context: Context, objects: Array<OrderStatus>) :
    ArrayAdapter<OrderStatus>(context, 0, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var currentItemView: View? = convertView

        if (currentItemView == null) {
            currentItemView =
                LayoutInflater.from(context).inflate(R.layout.order_status, parent, false)
        }
        val orderStatus: OrderStatus = getItem(position)!!//OrderStatus.valueOf()

        currentItemView?.findViewById<TextView>(R.id.statusTV)?.apply {
            text = context.getString(orderStatus.title)
            setTextColor(orderStatus.contentColor)
            val backgroundDrawable = background as GradientDrawable
            backgroundDrawable.setColor(orderStatus.backgroundColor)
        }

        return currentItemView!!
    }

}