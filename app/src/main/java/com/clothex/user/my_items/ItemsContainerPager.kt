package com.clothex.user.my_items

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.clothex.user.my_items.items.MyItemsFragment
import com.clothex.user.my_items.orders.ActiveOrdersFragment

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */
class ItemsContainerPager(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            MyItemsFragment()
        else
            ActiveOrdersFragment()
    }
}