package com.clothex.user.my_items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.clothex.user.R
import com.clothex.user.data.my_items.MyItemGroup
import com.clothex.user.databinding.FragmentItemsContainerBinding
import com.clothex.user.my_items.items.MyItemCallback


class ItemsContainerFragment : Fragment(), MyItemCallback {
    private lateinit var itemsContainerViewModel: ItemsContainerViewModel
    private var _binding: FragmentItemsContainerBinding? = null
    private val binding get() = _binding!!


    companion object {
        var lastOpenIndex = -1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        itemsContainerViewModel =
            ViewModelProvider(this)[ItemsContainerViewModel::class.java]
        _binding = FragmentItemsContainerBinding.inflate(inflater, container, false)
        val openOrders = ItemsContainerFragmentArgs.fromBundle(requireArguments()).openOrders
        binding.viewPager.apply {
            offscreenPageLimit = 2
            post {
                currentItem = if (openOrders) 1 else if (lastOpenIndex > -1) lastOpenIndex else 0
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myItemsTab.setOnClickListener {
            binding.viewPager.currentItem = 0
        }
        binding.activeOrdersTab.setOnClickListener {
            binding.viewPager.currentItem = 1
        }
        binding.viewPager.adapter = ItemsContainerPager(childFragmentManager, lifecycle)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                lastOpenIndex = position
                if (position == 0) myItemsSelected()
                else activeOrdersSelected()
            }
        })
        arguments?.clear()
    }

    private fun activeOrdersSelected() {
        binding.activeOrdersTab.setBackgroundResource(R.drawable.tab_bg_shape)
        binding.myItemsTab.background = null
    }

    private fun myItemsSelected() {
        binding.myItemsTab.setBackgroundResource(R.drawable.tab_bg_shape)
        binding.activeOrdersTab.background = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(myItemGroup: MyItemGroup) {
        val bundle = bundleOf("myItem" to myItemGroup)
        findNavController().navigate(R.id.bookFragment, bundle)
    }

    override fun deleteMyItemGroup(myItemGroup: MyItemGroup) {

    }
}