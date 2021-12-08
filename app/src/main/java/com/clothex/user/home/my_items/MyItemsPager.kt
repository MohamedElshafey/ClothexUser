package com.clothex.user.home.my_items

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.clothex.user.home.my_items.items.MyItemsFragment
import com.clothex.user.home.my_items.orders.OrdersFragment

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */
class MyItemsPager(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            MyItemsFragment()
        else
            OrdersFragment()
    }
}