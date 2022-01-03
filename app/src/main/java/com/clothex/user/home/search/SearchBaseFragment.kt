package com.clothex.user.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.clothex.data.domain.model.body.SortEnum
import com.clothex.user.R
import com.clothex.user.data.shopList
import com.clothex.user.databinding.FragmentSearchProductBinding
import com.clothex.user.home.product.ProductAdapter
import com.clothex.user.home.search.sort.SortProductBottomSheet
import com.clothex.user.home.shop.ShopSearchAdapter
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
open class SearchBaseFragment : Fragment() {

    lateinit var binding: FragmentSearchProductBinding
    private val viewModel: SearchViewModel by inject()
    private val productAdapter = ProductAdapter {
        findNavController().navigate(
            SearchBaseFragmentDirections.actionSearchProductFragmentToProductDetailsFragment(
                it.id
            )
        )
    }
    private var loadingMore = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.reset()
        viewModel.fetchProductPage()
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
        val list = listOf("Items", "Shops")
        listPopupWindow.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item, list))
        listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            val selectedItem = list[position]
            if (selectedItem == "Items") {
                showProducts()
            } else {
                showShops()
            }
            binding.searchBar.menu.text = selectedItem
            listPopupWindow.dismiss()
        }

        showProducts()

        binding.searchBar.menu.setOnClickListener {
            listPopupWindow.show()
        }

        viewModel.productLiveData.observe(viewLifecycleOwner, { products ->
            loadingMore = false
            productAdapter.update(products)
            binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
                    val totalItemCount = layoutManager.itemCount;
                    val lastPositions = IntArray(layoutManager.spanCount)
                    layoutManager.findLastCompletelyVisibleItemPositions(lastPositions);
                    val lastVisibleItem = lastPositions[0].coerceAtLeast(lastPositions[1])
                    if (!loadingMore && totalItemCount >= 20 && totalItemCount - 1 <= (lastVisibleItem)) {
                        loadingMore = true
                        viewModel.fetchProductPage()
                    }
                }
            })
        })

        binding.sortContainer.setOnClickListener {
            setFragmentResultListener(SortProductBottomSheet.REQUEST_KEY) { _, bundle ->
//                val sortEnum = bundle[SortProductBottomSheet.SORT_ENUM_KEY] as SortEnum
                productAdapter.reset()
                viewModel.reset()
                viewModel.fetchProductPage()
            }
            findNavController().navigate(SearchBaseFragmentDirections.actionSearchProductFragmentToSortProductBottomSheet())
        }

        binding.filterContainer.setOnClickListener {
            findNavController().navigate(SearchBaseFragmentDirections.actionSearchProductFragmentToFilterProductBottomSheet())
        }

        binding.actionBar.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun showProducts() {
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        binding.recyclerView.adapter = productAdapter
    }

    private fun showShops() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = ShopSearchAdapter(shopList) {
            findNavController().navigate(
                SearchBaseFragmentDirections.actionSearchProductFragmentToShopDetailsFragment(it)
            )
        }
    }

}