package com.clothex.user.home.product_details

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.clothex.user.databinding.FragmentProductDetailsBinding
import com.clothex.user.home.branch.BranchAdapter
import com.clothex.user.home.color.ColorsAdapter
import com.clothex.user.home.image.ImageAdapter
import com.clothex.user.utils.addChip
import com.google.android.material.chip.Chip

/**
 * Created by Mohamed Elshafey on 29/11/2021.
 */
class ProductDetailsFragment : Fragment() {

    private lateinit var mViewModel: ProductDetailsViewModel
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val item = ProductDetailsFragmentArgs.fromBundle(requireArguments()).item
        mViewModel = ViewModelProvider(
            this,
            ProductViewModelFactory(item)
        )[ProductDetailsViewModel::class.java]
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.minusIV.isEnabled = false

        binding.listPriceTV.paintFlags = binding.listPriceTV.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        LinearSnapHelper().attachToRecyclerView(binding.mainImagesRV)

        mViewModel.mainImagesLiveData.observe(viewLifecycleOwner, {
            binding.mainImagesRV.adapter = ImageAdapter(it)
        })

        mViewModel.colorsLiveData.observe(viewLifecycleOwner, {
            val list: List<String> = it.map { it.code ?: "" }

            binding.colorRV.adapter = ColorsAdapter(list) { color ->
                mViewModel.selectColor(color)
            }
        })

        mViewModel.sizesLiveData.observe(viewLifecycleOwner, { list ->
            binding.sizeChipGroup.removeAllViews()
            binding.sizeChipGroup.addChip(layoutInflater, *list.map { it.title }.toTypedArray())
        })

        binding.sizeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = binding.sizeChipGroup.findViewById(checkedId) as Chip
            val selectedSize =
                mViewModel.sizesLiveData.value?.first { it.title.equals(chip.text.toString()) }
            selectedSize?.let { mViewModel.selectSize(it) }
        }

        mViewModel.branchesLiveData.observe(viewLifecycleOwner, {
            binding.branchesRV.adapter = BranchAdapter(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}