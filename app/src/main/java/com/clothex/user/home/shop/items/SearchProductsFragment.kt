package com.clothex.user.home.shop.items

import android.os.Bundle
import android.view.View
import com.clothex.user.home.search.SearchBaseFragment
import com.clothex.user.home.search.filter.FilterProductBottomSheet
import com.clothex.user.home.search.sort.SortProductBottomSheet

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
class SearchProductsFragment : SearchBaseFragment() {

    companion object {
        fun newInstance(shopId: String): SearchProductsFragment {
            val args = Bundle()
            args.putString("shopId", shopId)
            val fragment = SearchProductsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.shopId = requireArguments().getString("shopId")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actionBar.visibility = View.GONE
        binding.searchBar.searchBarContainer.visibility = View.GONE
//        binding.recyclerView.adapter = ProductAdapter(getItemsList(requireContext())) {
//            findNavController().navigate(R.id.productDetailsFragment, bundleOf("item" to it))
//        }
        binding.sortContainer.setOnClickListener {
            SortProductBottomSheet().show(parentFragmentManager, "")
        }

        binding.filterContainer.setOnClickListener {
            FilterProductBottomSheet().show(parentFragmentManager, "")
        }
    }

}