package com.clothex.user.home.my_items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.clothex.user.R
import com.clothex.user.databinding.FragmentItemsContainerBinding


class ItemsContainerFragment : Fragment() {
    private lateinit var myItemsViewModel: MyItemsViewModel
    private var _binding: FragmentItemsContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myItemsViewModel =
            ViewModelProvider(this)[MyItemsViewModel::class.java]
        _binding = FragmentItemsContainerBinding.inflate(inflater, container, false)
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
        binding.viewPager.adapter = MyItemsPager(childFragmentManager, lifecycle)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) myItemsSelected()
                else activeOrdersSelected()
            }
        })
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
}