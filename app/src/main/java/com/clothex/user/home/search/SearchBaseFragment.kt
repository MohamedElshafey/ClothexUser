package com.clothex.user.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.clothex.user.R
import com.clothex.user.databinding.FragmentSearchProductBinding
import com.clothex.user.home.product.ProductPagingAdapter
import com.clothex.user.home.search.filter.FilterProductBottomSheet
import com.clothex.user.home.search.sort.SortProductBottomSheet
import com.clothex.user.home.shop.ShopAdapter
import com.clothex.user.utils.PagingLoadCallback
import com.clothex.user.utils.addLoadCallback
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
open class SearchBaseFragment : Fragment() {

    lateinit var binding: FragmentSearchProductBinding
    val viewModel: SearchViewModel by inject()

    private val productPagingAdapter = ProductPagingAdapter {
        findNavController().navigate(
            R.id.productDetailsFragment, bundleOf(
                "product_id" to it.id
            )
        )
    }

    private val shopAdapter = ShopAdapter {
        findNavController().navigate(
            SearchBaseFragmentDirections.actionSearchProductFragmentToShopDetailsFragment(
                it.id
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.isProducts = SearchBaseFragmentArgs.fromBundle(it).product
        }
        viewModel.clearFilter()
        viewModel.reset()
        resetProductPagingAdapter()
        shopAdapter.reset()
        viewModel.fetch(null)
        setFragmentResultListener(FilterProductBottomSheet.REQUEST_KEY) { _, _ ->
            resetProductPagingAdapter()
            shopAdapter.reset()
            viewModel.reset()
            viewModel.fetch(binding.searchBar.searchET.text.toString())
        }
        setFragmentResultListener(SortProductBottomSheet.REQUEST_KEY) { _, _ ->
            resetProductPagingAdapter()
            shopAdapter.reset()
            viewModel.reset()
            viewModel.fetch(binding.searchBar.searchET.text.toString())
        }
        productPagingAdapter.addLoadCallback(object : PagingLoadCallback {
            override fun loading() {
                if (viewModel.isProducts)
                    binding.noResultsMessage.isVisible = false
            }

            override fun success() {
                if (viewModel.isProducts)
                    binding.noResultsMessage.isVisible = false
            }

            override fun empty() {
                if (viewModel.isProducts)
                    binding.noResultsMessage.isVisible = true
            }
        })
    }

    private fun resetProductPagingAdapter() {
        lifecycleScope.launch {
            productPagingAdapter.reset()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listPopupWindow = ListPopupWindow(requireContext(), null, R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = binding.searchBar.menu
        val items = context?.getString(R.string.items)
        val shops = context?.getString(R.string.shops)
        val list = listOf(items, shops)
        listPopupWindow.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item, list))
        listPopupWindow.setSelection(if (viewModel.isProducts) 0 else 1)
        listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            val selectedItem = list[position]
            binding.searchBar.searchET.text = null
            if (selectedItem == items) {
                showProducts()
                viewModel.fetch(null)
            } else {
                showShops()
                viewModel.fetch(null)
            }
            binding.searchBar.menu.text = selectedItem
            listPopupWindow.dismiss()
        }

        if (viewModel.isProducts) {
            showProducts()
            binding.searchBar.menu.text = list[0]
        } else {
            showShops()
            binding.searchBar.menu.text = list[1]
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.shimmerFrame.root.isVisible = isLoading
        }

        viewModel.productLiveData.observe(viewLifecycleOwner) { products ->
            binding.shimmerFrame.root.isGone = true
            productPagingAdapter.submitData(lifecycle, products)
        }

        viewModel.shopLiveData.observe(viewLifecycleOwner) {
            binding.shimmerFrame.root.isGone = true
            shopAdapter.update(it)
        }

        binding.searchBar.menu.setOnClickListener {
            listPopupWindow.show()
        }
        binding.clearSearchImageView.setOnClickListener {
            binding.searchBar.searchET.text = null
        }
        binding.searchBar.searchET.doAfterTextChanged {
            resetProductPagingAdapter()
            shopAdapter.reset()
            viewModel.reset()
            viewModel.fetch(it.toString())
            binding.clearSearchImageView.isVisible = it.isNullOrEmpty().not()
        }

        binding.sortContainer.setOnClickListener {
            findNavController().navigate(R.id.sortProductBottomSheet)
//            findNavController().navigate(SearchBaseFragmentDirections.actionSearchProductFragmentToSortProductBottomSheet())
        }

        binding.filterContainer.setOnClickListener {
//            findNavController().navigate(SearchBaseFragmentDirections.actionSearchProductFragmentToFilterProductBottomSheet())
            findNavController().navigate(R.id.filterProductBottomSheet)
        }

        binding.actionBar.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.isEmptyLiveData.observe(viewLifecycleOwner) {
            binding.noResultsMessage.isVisible = it
        }

        viewModel.isFilterApplied.observe(viewLifecycleOwner) {
            binding.filterIndicator.isVisible = it
        }

        viewModel.isSortApplied.observe(viewLifecycleOwner) {
            binding.sortIndicator.isVisible = it
        }
    }

    private fun showProducts() {
        viewModel.isProducts = true
        binding.filterContainer.isGone = false
        binding.sortContainer.isGone = false
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        binding.recyclerView.adapter = productPagingAdapter
    }

    private fun showShops() {
        viewModel.isProducts = false
        binding.filterContainer.isGone = true
        binding.sortContainer.isGone = true
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = shopAdapter
    }

}