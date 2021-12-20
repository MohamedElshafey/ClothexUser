package com.clothex.user.my_items.orders

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.Fragment
import com.clothex.user.R
import com.clothex.user.data.activeOrders
import com.clothex.user.data.orders.Order
import com.clothex.user.databinding.FragmentActiveOrdersBinding
import java.util.*

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */
class ActiveOrdersFragment : Fragment() {

    lateinit var binding: FragmentActiveOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActiveOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    val onClickOrderCallback: (Order) -> Unit = {
        it.shop.location?.let { location ->
            val uri = String.format(
                Locale.ENGLISH,
                "http://maps.google.com/maps?q=loc:%f,%f",
                location.latitude, location.longitude
            )
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }
    }

    var ordersAdapter = OrdersAdapter(activeOrders, onClickOrderCallback)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterContainer.setOnClickListener {
            showFilterList()
        }

        binding.ordersRV.adapter = ordersAdapter
    }

    private fun showFilterList() {
        val listPopupWindow = ListPopupWindow(requireContext(), null, R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = binding.filterContainer
        val list = OrderStatus.values()
        listPopupWindow.setAdapter(OrderStatusFilterAdapter(requireContext(), list))
        listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            val selectedItem = list[position]
            val orders = activeOrders.filter { it.orderStatus == selectedItem }
            ordersAdapter = OrdersAdapter(orders, onClickOrderCallback)
            binding.ordersRV.adapter = ordersAdapter
            listPopupWindow.dismiss()
        }
        listPopupWindow.show()
    }

}