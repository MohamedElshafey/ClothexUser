package com.clothex.user.home.shop.items

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.data.getItemsList
import com.clothex.user.home.product.ProductAdapter
import com.clothex.user.home.search.SearchProductFragment

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
class ShopProductsFragment : SearchProductFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBar.searchBarContainer.visibility = View.GONE

        binding.productRV.adapter = ProductAdapter(getItemsList(requireContext())) {
            findNavController().navigate(R.id.productDetailsFragment, bundleOf("item" to it))
        }

        binding.sortContainer.setOnClickListener {
            findNavController().navigate(R.id.sortProductBottomSheet)
        }

        binding.filterContainer.setOnClickListener {
            findNavController().navigate(R.id.filterProductBottomSheet)
        }
    }

}