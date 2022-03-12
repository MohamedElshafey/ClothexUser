package com.clothex.user.home.search.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.clothex.user.R
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.databinding.FilterProductBottomSheetBinding
import com.clothex.user.home.color.ColorsAdapter
import com.clothex.user.utils.addChip
import com.google.android.material.chip.Chip
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class FilterProductBottomSheet : DefaultBottomSheet() {

    companion object {
        const val REQUEST_KEY = "FilterRequestKey"
    }

    lateinit var binding: FilterProductBottomSheetBinding
    private val viewModel: FilterProductViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getColorFilter()
        viewModel.getSizeFilter()
        viewModel.getPriceStartFilter()
        viewModel.getPriceEndFilter()
    }

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
        binding.priceRangeSlider.addOnChangeListener { _, _, fromUser ->
            setPriceRangeText()
        }
        viewModel.priceStartMutableLiveData.observe(viewLifecycleOwner, { price ->
            if (price != null && price > 0) {
                binding.priceRangeSlider.setValues(
                    price.toFloat(),
                    binding.priceRangeSlider.values[1]
                )
            }
        })
        viewModel.priceEndMutableLiveData.observe(viewLifecycleOwner, { price ->
            if (price != null && price > 0) {
                binding.priceRangeSlider.setValues(
                    binding.priceRangeSlider.values[0],
                    price.toFloat()
                )
            }
        })

        binding.categoryChipGroup.addChip(layoutInflater, *typeList.toTypedArray())
        binding.sizeChipGroup.addChip(layoutInflater, *sizeList.toTypedArray())

        viewModel.sizeMutableLiveData.observe(viewLifecycleOwner, {
            val chip: Chip? = binding.sizeChipGroup.findViewWithTag(it)
            chip?.isChecked = true
        })

        binding.sizeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            viewModel.selectedSize = group.findViewById<Chip>(checkedId).text.toString()
        }

        binding.colorRV.adapter = ColorsAdapter(colorList) {
            viewModel.selectedColor = it
        }

        binding.applyFilterButton.setOnClickListener {
            viewModel.selectedColor?.let { color -> viewModel.setColorFilter(color) }
            viewModel.selectedSize?.let { size -> viewModel.setSizeFilter(size) }
            viewModel.selectedPriceStart?.let { price -> viewModel.setPriceStartFilter(price) }
            viewModel.selectedPriceEnd?.let { price -> viewModel.setPriceEndFilter(price) }
            setFragmentResult(REQUEST_KEY, bundleOf())
            dismiss()
        }
    }

    private fun setPriceRangeText() {
        with(binding.priceRangeSlider.values) {
            viewModel.selectedPriceStart = get(0).toInt()
            viewModel.selectedPriceEnd = get(1).toInt()
            binding.rangeFromTV.text = String.format(getString(R.string.egp), get(0).toInt())
            binding.rangeToTV.text = String.format(getString(R.string.egp), get(1).toInt())
        }
    }

}