package com.clothex.user.home.shop.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.clothex.user.data.Shop
import com.clothex.user.databinding.FragmentShopDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class ShopDetailsFragment : Fragment() {
    lateinit var binding: FragmentShopDetailsBinding
    lateinit var mViewModel: ShopDetailsViewModel
    private var shop: Shop? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopDetailsBinding.inflate(LayoutInflater.from(context), container, false)
        shop = ShopDetailsFragmentArgs.fromBundle(requireArguments()).shop
        mViewModel = ShopDetailsViewModel(shop = shop!!)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager2.adapter = ShopDetailsPager(childFragmentManager, lifecycle, shop!!)
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Items"
                }
                1 -> {
                    tab.text = "Info"
                }
                2 -> {
                    tab.text = "Photos"
                }
            }
        }.attach()
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
    }

}