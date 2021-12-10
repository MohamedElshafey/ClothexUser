package com.clothex.user.home.search.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.databinding.FilterProductBottomSheetBinding
import com.clothex.user.home.color.ColorsAdapter
import com.clothex.user.utils.addChip

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class FilterProductBottomSheet : DefaultBottomSheet() {
    lateinit var binding: FilterProductBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilterProductBottomSheetBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    private val typeList = listOf("Men", "Casual", "Semi-formal", "5+")
    private val sizeList = listOf("XS", "S", "M", "L", "XL")
    private val colorList = listOf("#ffffff", "#cfad88", "#ef0000", "#5abfd4", "#e88b00")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPriceRangeText()
        binding.priceRangeSlider.addOnChangeListener { _, _, _ ->
            setPriceRangeText()
        }
        binding.categoryChipGroup.addChip(layoutInflater, *typeList.toTypedArray())
        binding.sizeChipGroup.addChip(layoutInflater, *sizeList.toTypedArray())
        binding.colorRV.adapter = ColorsAdapter(colorList) {

        }
        binding.applyFilterButton.setOnClickListener {
            dismiss()
        }
    }

    private fun setPriceRangeText() {
        with(binding.priceRangeSlider.values) {
            binding.rangeFromTV.text = String.format("EGP %s", get(0))
            binding.rangeToTV.text = String.format("EGP %s", get(1))
        }
    }

}