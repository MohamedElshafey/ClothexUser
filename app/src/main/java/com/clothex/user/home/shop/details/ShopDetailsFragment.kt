package com.clothex.user.home.shop.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.clothex.user.R
import com.clothex.user.databinding.FragmentShopDetailsBinding
import com.clothex.user.log.MainLogEvents
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class ShopDetailsFragment : Fragment() {
    lateinit var binding: FragmentShopDetailsBinding
    private val mViewModel: ShopDetailsViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopDetailsBinding.inflate(LayoutInflater.from(context), container, false)
        val shopId = ShopDetailsFragmentArgs.fromBundle(requireArguments()).shopId
        mViewModel.fetchDetails(shopId)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager2.offscreenPageLimit = 3
        mViewModel.shopMutableLiveData.observe(viewLifecycleOwner) { shop ->
            shop?.let {
                mViewModel.logEvent(
                    MainLogEvents.OpenShop(shop.id, shop.getName(mViewModel.isArabic()))
                )
            }
            if (binding.viewPager2.adapter == null) {
                binding.viewPager2.adapter = ShopDetailsPager(childFragmentManager, lifecycle, shop)
                TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
                    when (position) {
                        0 -> {
                            tab.text = context?.getString(R.string.items)
                        }
                        1 -> {
                            tab.text = context?.getString(R.string.info)
                        }
                        2 -> {
                            tab.text = context?.getString(R.string.offers)
                        }
                        3 -> {
                            tab.text = context?.getString(R.string.photos)
                        }
                    }
                }.attach()
            }
        }
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))

            }
        })
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
    }

}