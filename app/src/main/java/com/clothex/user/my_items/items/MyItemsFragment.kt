package com.clothex.user.my_items.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.data.my_items.MyItemGroup
import com.clothex.user.databinding.FragmentMyItemsBinding
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */

class MyItemsFragment : Fragment() {

    lateinit var binding: FragmentMyItemsBinding
    private val mViewModel: MyItemsViewModel by inject()

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
        mViewModel.fetchMyItems("123456789")
        mViewModel.mutableMyItemsLiveData.observe(viewLifecycleOwner, Observer { myItems ->
            val grouped = myItems?.groupBy {
                it.branch
            }
            val groupList = mutableListOf<MyItemGroup>()
            grouped?.forEach {
                groupList.add(MyItemGroup(it.value.first().shop, it.key, it.value))
            }
            binding.recyclerView.adapter = MyItemsAdapter(groupList) {
                val bundle = bundleOf("myItem" to it)
                findNavController().navigate(R.id.bookFragment, bundle)
            }
        })

    }
}