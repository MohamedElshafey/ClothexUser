package com.clothex.user.home.shop.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.clothex.data.domain.model.shop.Shop
import com.clothex.user.home.shop.info.ShopInfoFragment
import com.clothex.user.home.shop.items.SearchProductsFragment
import com.clothex.user.home.shop.offer.ShopOfferFragment
import com.clothex.user.home.shop.photos.ShopPhotosFragment

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */
class ShopDetailsPager(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val shop: Shop
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchProductsFragment.newInstance(shop.id)
            1 -> ShopInfoFragment.newInstance(shop)
            2 -> ShopOfferFragment.newInstance(shop.id)
            else -> ShopPhotosFragment.newInstance(shop)
        }
    }
}