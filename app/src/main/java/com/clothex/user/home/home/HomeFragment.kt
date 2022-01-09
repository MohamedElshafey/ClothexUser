package com.clothex.user.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.data.shopList
import com.clothex.user.databinding.FragmentHomeBinding
import com.clothex.user.home.product.ProductAdapter
import com.clothex.user.home.shop.ShopAdapter
import com.clothex.user.utils.setAllOnClickListener
import org.koin.android.ext.android.inject


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by inject()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = homeViewModel
        homeViewModel.productLiveData.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = false
            binding.productRV.adapter = ProductAdapter(it) { prod ->
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToProductDetailsFragment(prod.id)
                )
            }
        })

        homeViewModel.shopLiveData.observe(viewLifecycleOwner, {
            binding.shopsRV.adapter = ShopAdapter(it) {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToShopDetailsFragment(it.id)
                )
            }
        })

        val layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = (width * 0.7f).toInt()
                return true
            }
        }
        binding.arrivalSeeAllTV.setOnClickListener {
        }
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.shopsRV.layoutManager = layoutManager

        binding.notificationIV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNotificationFragment())
        }

        binding.locationGroup.setAllOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToSelectLocationBottomSheet())
        }

        binding.searchBar.apply {
            searchET.isClickable = false
            searchET.isFocusable = false
            searchBarContainer.setOnClickListener { openSearchFragment() }
            searchET.setOnClickListener { openSearchFragment() }
            menu.setOnClickListener { openSearchFragment() }
        }

        binding.arrivalSeeAllTV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToSearchProductFragment())
        }

        binding.voucherButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToVoucherFragment())
        }

        binding.swipeRefresh.setOnRefreshListener {
            homeViewModel.fetchHome()
        }

    }

    private fun openSearchFragment() {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToSearchProductFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}