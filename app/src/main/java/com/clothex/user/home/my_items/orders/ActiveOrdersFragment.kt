package com.clothex.user.home.my_items.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clothex.user.data.activeOrders
import com.clothex.user.databinding.FragmentActiveOrdersBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ordersRV.adapter = OrdersAdapter(activeOrders)
    }

}