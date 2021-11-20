package com.clothex.user.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.data.productList
import com.clothex.user.data.shopList
import com.clothex.user.databinding.FragmentHomeBinding
import com.clothex.user.home.product.ProductAdapter
import com.clothex.user.home.shop.ShopAdapter


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
        binding.productRV.adapter = ProductAdapter(productList)
        binding.shopsRV.adapter = ShopAdapter(shopList)
        val layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = (width * 0.7f).toInt()
                return true
            }
        }
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.shopsRV.layoutManager = layoutManager

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}