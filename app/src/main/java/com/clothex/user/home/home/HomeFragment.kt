package com.clothex.user.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.data.getItemsList
import com.clothex.user.data.shopList
import com.clothex.user.databinding.FragmentHomeBinding
import com.clothex.user.home.product.ProductAdapter
import com.clothex.user.home.shop.ShopAdapter
import com.clothex.user.utils.setAllOnClickListener


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewModel = homeViewModel
        binding.productRV.adapter = ProductAdapter(getItemsList(requireContext()).slice(2..7)) {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToProductDetailsFragment(
                    it
                )
            )
        }
        binding.shopsRV.adapter = ShopAdapter(shopList)
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

        return root
    }

    private fun openSearchFragment() {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToSearchProductFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}