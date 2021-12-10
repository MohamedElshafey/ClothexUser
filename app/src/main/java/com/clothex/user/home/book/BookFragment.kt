package com.clothex.user.home.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentBookBinding
import com.clothex.user.my_items.minimal.MinimalItemAdapter
import com.clothex.user.utils.setImageFromUrl

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
class BookFragment : Fragment() {

    lateinit var binding: FragmentBookBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myItem = BookFragmentArgs.fromBundle(requireArguments()).myItem
        with(myItem.shop) {
            binding.shopTitleTV.text = name
            binding.addressTV.text = addressName
            setImageFromUrl(binding.logoIV, logoUrl)
        }
        binding.itemsRV.adapter = MinimalItemAdapter(myItem.myItems)
        val totalPrice = myItem.myItems.map { it.price * it.quantity }.sum()
        binding.subtotalTV.text = String.format("EGP %.02f", totalPrice.toFloat())
        binding.discountTV.text = String.format("EGP %.02f", 0f)
        binding.totalTV.text = String.format("EGP %.02f", totalPrice.toFloat())
        binding.bookButton.setOnClickListener {
            findNavController().navigate(BookFragmentDirections.actionBookFragmentToRequestBookFragment())
        }
    }

}