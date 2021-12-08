package com.clothex.user.home.my_items.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clothex.user.data.myItems
import com.clothex.user.databinding.FragmentMyItemsBinding

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */

class MyItemsFragment : Fragment() {

    lateinit var binding: FragmentMyItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = MyItemsAdapter(myItems)
    }
}