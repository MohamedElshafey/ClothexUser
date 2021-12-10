package com.clothex.user.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.data.getItemsList
import com.clothex.user.databinding.FragmentSearchProductBinding
import com.clothex.user.home.product.ProductAdapter

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
class SearchProductFragment : Fragment() {

    lateinit var binding: FragmentSearchProductBinding

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
            binding.searchBar.menu.text = selectedItem
            listPopupWindow.dismiss()
        }

        binding.searchBar.menu.setOnClickListener {
            listPopupWindow.show()
        }

        binding.productRV.adapter = ProductAdapter(getItemsList(requireContext())) {
            findNavController().navigate(
                SearchProductFragmentDirections.actionSearchProductFragmentToProductDetailsFragment(
                    it
                )
            )
        }
    }

}