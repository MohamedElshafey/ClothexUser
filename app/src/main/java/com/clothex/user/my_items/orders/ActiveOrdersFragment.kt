package com.clothex.user.my_items.orders

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.order.MyOrder
import com.clothex.data.domain.model.order.OrderState
import com.clothex.user.R
import com.clothex.user.databinding.FragmentActiveOrdersBinding
import org.koin.android.ext.android.inject
import java.util.*

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */
class ActiveOrdersFragment : Fragment() {

    private val mViewModel: ActiveOrdersViewModel by inject()
    lateinit var binding: FragmentActiveOrdersBinding
    private val onClickOrderCallback = object : OrderClickCallback {
        override fun onGetDirectionClicked(order: MyOrder) {
            order.branch.address?.location?.let { location ->
                if (location.coordinates.size == 2) {
                    val uri = String.format(
                        Locale.ENGLISH,
                        "http://maps.google.com/maps?q=loc:%f,%f",
                        location.coordinates[1], location.coordinates[0]
                    )
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    startActivity(intent)
                }
            }
        }

        override fun onOrderSelected(order: MyOrder) {
            val bundle = bundleOf("orderId" to order.id)
            findNavController().navigate(R.id.orderDetailsFragment, bundle)
        }
    }
    private var ordersAdapter = OrdersAdapter(onClickOrderCallback)
    private var myOrders: List<MyOrder>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActiveOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.isVisible = true
        binding.filterContainer.setOnClickListener {
            showFilterList()
        }
        binding.ordersRV.adapter = ordersAdapter
        mViewModel.myOrdersLiveData.observe(viewLifecycleOwner, { orders ->
            binding.progressBar.isGone = true
            myOrders = orders
            ordersAdapter.updateData(orders ?: listOf())
            if (orders.isNullOrEmpty()) {
                binding.messageContainer.apply {
                    messageIV.setImageResource(R.drawable.ic_no_items_found)
                    messageTitleTV.setText(R.string.no_orders_message)
                    messageSubTitleTV.setText(R.string.no_orders_description)
                }
            }
            binding.messageContainer.container.isGone = orders.isNullOrEmpty().not()
        })
    }

    override fun onResume() {
        super.onResume()
        mViewModel.fetchMyOrders()
    }

    private fun showFilterList() {
        val listPopupWindow = ListPopupWindow(requireContext(), null, R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = binding.filterContainer
        val list = OrderState.values()
        listPopupWindow.setAdapter(OrderStateFilterAdapter(requireContext(), list))
        listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            val selectedItem = list[position]
            val orders = myOrders?.filter { it.state == selectedItem }
            ordersAdapter.updateData(orders ?: listOf())
            listPopupWindow.dismiss()
        }
        listPopupWindow.show()
    }

}