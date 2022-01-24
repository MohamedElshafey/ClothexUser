package com.clothex.user.my_items.orders

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.clothex.data.domain.model.order.OrderState
import com.clothex.user.R


/**
 * Created by Mohamed Elshafey on 20/12/2021.
 */
class OrderStateFilterAdapter(context: Context, objects: Array<OrderState>) :
    ArrayAdapter<OrderState>(context, 0, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var currentItemView: View? = convertView

        if (currentItemView == null) {
            currentItemView =
                LayoutInflater.from(context).inflate(R.layout.order_state, parent, false)
        }
        val orderStatus: OrderState = getItem(position)!!

        currentItemView?.findViewById<TextView>(R.id.statusTV)?.apply {
            text = context.getString(orderStatus.title)
            setTextColor(orderStatus.contentColor)
            val backgroundDrawable = background as GradientDrawable
            backgroundDrawable.setColor(orderStatus.backgroundColor)
        }

        return currentItemView!!
    }

}