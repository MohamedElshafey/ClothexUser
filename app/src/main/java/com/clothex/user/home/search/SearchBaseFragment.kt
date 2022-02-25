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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.clothex.user.R
import com.clothex.user.databinding.FragmentSearchProductBinding
import com.clothex.user.home.product.ProductAdapter
import com.clothex.user.home.search.filter.FilterProductBottomSheet
import com.clothex.user.home.search.sort.SortProductBottomSheet
import com.clothex.user.home.shop.ShopAdapter
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
open class SearchBaseFragment : Fragment() {

    lateinit var binding: FragmentSearchProductBinding
    val viewModel: SearchViewModel by inject()
    private val productAdapter = ProductAdapter {
        findNavController().navigate(
            R.id.productDetailsFragment, bundleOf(
                "product_id" to it.id
            )
        )
//        findNavController().navigate(
//            SearchBaseFragmentDirections.actionSearchProductFragmentToProductDetailsFragment(
//                it.id
//            )
//        )
    }
    private val shopAdapter = ShopAdapter {
        findNavController().navigate(
            SearchBaseFragmentDirections.actionSearchProductFragmentToShopDetailsFragment(
                it.id
            )
        )
    }

    private var loadingMore = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.isProducts = SearchBaseFragmentArgs.fromBundle(it).product
        }
        viewModel.reset()
        productAdapter.reset()
        shopAdapter.reset()
        viewModel.fetch(null)
        setFragmentResultListener(FilterProductBottomSheet.REQUEST_KEY) { _, _ ->
            productAdapter.reset()
            shopAdapter.reset()
            viewModel.reset()
            viewModel.fetch(null)
        }
        setFragmentResultListener(SortProductBottomSheet.REQUEST_KEY) { _, _ ->
            productAdapter.reset()
            shopAdapter.reset()
            viewModel.reset()
            viewModel.fetch(null)
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
            loadingMore = false
            binding.shimmerFrame.root.isGone = true
            productAdapter.append(products)
        }

        viewModel.shopLiveData.observe(viewLifecycleOwner) {
            loadingMore = false
            binding.shimmerFrame.root.isGone = true
            shopAdapter.update(it)
        }

        binding.searchBar.menu.setOnClickListener {
            listPopupWindow.show()
        }

        binding.searchBar.searchET.doAfterTextChanged {
            productAdapter.reset()
            shopAdapter.reset()
            viewModel.reset()
            viewModel.fetch(it.toString())
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is StaggeredGridLayoutManager) {
                    val totalItemCount = layoutManager.itemCount;
                    val lastPositions = IntArray(layoutManager.spanCount)
                    layoutManager.findLastCompletelyVisibleItemPositions(lastPositions);
                    val lastVisibleItem = lastPositions[0].coerceAtLeast(lastPositions[1])
                    if (!loadingMore && totalItemCount >= 20 && totalItemCount - 1 <= (lastVisibleItem)) {
                        loadingMore = true
                        viewModel.fetch(null)
                    }
                }
            }
        })

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
    }

    private fun showProducts() {
        viewModel.isProducts = true
        binding.filterContainer.isGone = false
        binding.sortContainer.isGone = false
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        binding.recyclerView.adapter = productAdapter
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