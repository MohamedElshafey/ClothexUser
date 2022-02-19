package com.clothex.user.home.search.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.clothex.data.domain.model.body.SortEnum
import com.clothex.user.R
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.data.SortItem
import com.clothex.user.databinding.SortProductBottomSheetBinding
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class SortProductBottomSheet : DefaultBottomSheet() {

    companion object {
        const val REQUEST_KEY = "SortRequestKey"
        const val SORT_ENUM_KEY = "SortEnumKey"
    }

    lateinit var binding: SortProductBottomSheetBinding
    private val viewModel: SortProductViewModel by inject()

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.sortMutableLiveData.observe(viewLifecycleOwner, { sortValue ->
            val list = listOf(
                SortItem(
                    getString(R.string.best_match),
                    R.drawable.ic_best_match,
                    sortValue == SortEnum.BEST_MATCH.value,
                    SortEnum.BEST_MATCH
                ),
                SortItem(
                    getString(R.string.asc_price),
                    R.drawable.ic_ascending,
                    sortValue == SortEnum.PRICE_ASC.value,
                    SortEnum.PRICE_ASC
                ),
                SortItem(
                    getString(R.string.desc_price),
                    R.drawable.ic_descending,
                    sortValue == SortEnum.PRICE_DESC.value,
                    SortEnum.PRICE_DESC
                )
            )
            binding.recyclerView.adapter = SortAdapter(list) { sortEnum ->
                viewModel.setSort(sortEnum).invokeOnCompletion {
                    setFragmentResult(REQUEST_KEY, bundleOf(SORT_ENUM_KEY to sortEnum))
                    dismiss()
                }
            }
        })
    }

}