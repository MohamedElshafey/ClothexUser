package com.clothex.user.home.my_items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Fade
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.clothex.user.R
import com.clothex.user.databinding.FragmentMyItemsBinding


class MyItemsFragment : Fragment() {
    private lateinit var myItemsViewModel: MyItemsViewModel
    private var _binding: FragmentMyItemsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
         container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myItemsViewModel =
            ViewModelProvider(this)[MyItemsViewModel::class.java]
        _binding = FragmentMyItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myItemsTab.setOnClickListener {
            binding.myItemsTab.setBackgroundResource(R.drawable.tab_bg_shape)
            binding.activeOrdersTab.background = null
        }
        binding.activeOrdersTab.setOnClickListener {
            binding.activeOrdersTab.setBackgroundResource(R.drawable.tab_bg_shape)
            binding.myItemsTab.background = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}