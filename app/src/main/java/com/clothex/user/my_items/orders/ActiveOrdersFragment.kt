package com.clothex.user.my_items.orders

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clothex.user.data.activeOrders
import com.clothex.user.databinding.FragmentActiveOrdersBinding
import java.lang.String
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ordersRV.adapter = OrdersAdapter(activeOrders) {
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
    }

}