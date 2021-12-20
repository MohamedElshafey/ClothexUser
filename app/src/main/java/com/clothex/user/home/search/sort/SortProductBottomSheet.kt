package com.clothex.user.home.search.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clothex.user.R
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.data.SortItem
import com.clothex.user.databinding.SortProductBottomSheetBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class SortProductBottomSheet : DefaultBottomSheet() {
    lateinit var binding: SortProductBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SortProductBottomSheetBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    val list = listOf<SortItem>(
        SortItem("Best match", R.drawable.ic_best_match, true),
        SortItem("From lowest to highest price", R.drawable.ic_ascending, false),
        SortItem("From highest to lowest price", R.drawable.ic_descending, false)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = SortAdapter(list)
    }

}