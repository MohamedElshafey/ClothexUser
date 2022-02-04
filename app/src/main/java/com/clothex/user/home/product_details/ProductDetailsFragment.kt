package com.clothex.user.home.product_details

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.clothex.data.domain.model.body.MyItemBody
import com.clothex.user.databinding.FragmentProductDetailsBinding
import com.clothex.user.home.branch.BranchAdapter
import com.clothex.user.home.color.ColorsAdapter
import com.clothex.user.home.image.ImageAdapter
import com.clothex.user.utils.addChip
import com.clothex.user.utils.addDivider
import com.clothex.user.utils.setHeightPercentage
import com.google.android.material.chip.Chip
import org.koin.android.ext.android.inject


/**
 * Created by Mohamed Elshafey on 29/11/2021.
 */
class ProductDetailsFragment : Fragment() {

    private val mViewModel: ProductDetailsViewModel by inject()
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = ProductDetailsFragmentArgs.fromBundle(requireArguments()).productId
        mViewModel.getProductDetails(productId)

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.mainImagesRV)

        binding.contentContainer.minusIV.isEnabled = false

        binding.contentContainer.listPriceTV.paintFlags =
            binding.contentContainer.listPriceTV.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        binding.toolbar.setHeightPercentage(15)
        binding.appbar.setHeightPercentage(50)

        mViewModel.mainImagesLiveData.observe(viewLifecycleOwner, {
            binding.mainImagesRV.adapter = ImageAdapter(it)
            binding.indicator.attachToRecyclerView(binding.mainImagesRV, pagerSnapHelper)
        })

        mViewModel.colorsLiveData.observe(viewLifecycleOwner, {
            val list: List<String> = it.map { it.code ?: "" }

            binding.contentContainer.colorRV.adapter = ColorsAdapter(list) { color ->
                mViewModel.selectColor(color)
            }
        })

        mViewModel.sizesLiveData.observe(viewLifecycleOwner, { list ->
            binding.contentContainer.sizeChipGroup.removeAllViews()
            binding.contentContainer.sizeChipGroup.addChip(
                layoutInflater,
                *list.map { it.title }.toTypedArray()
            )
        })

        binding.contentContainer.sizeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = binding.contentContainer.sizeChipGroup.findViewById(checkedId) as Chip?
            val selectedSize =
                mViewModel.sizesLiveData.value?.first { it.title.equals(chip?.text.toString()) }
            selectedSize?.let { mViewModel.selectSize(it) }
        }

        mViewModel.branchesLiveData.observe(viewLifecycleOwner, {
            binding.contentContainer.branchesRV.adapter = BranchAdapter(it) { branch ->
                mViewModel.selectBranch(branch)
            }
        })

        binding.contentContainer.plusIV.setOnClickListener {
            mViewModel.quantity += 1
            binding.contentContainer.plusIV.isEnabled = (mViewModel.quantity < 9)
            binding.contentContainer.minusIV.isEnabled = (mViewModel.quantity > 1)
        }

        binding.contentContainer.minusIV.setOnClickListener {
            mViewModel.quantity -= 1
            binding.contentContainer.plusIV.isEnabled = (mViewModel.quantity < 9)
            binding.contentContainer.minusIV.isEnabled = (mViewModel.quantity > 1)
        }

        binding.contentContainer.branchesRV.addDivider()

        binding.backIV.setOnClickListener { findNavController().navigateUp() }

        binding.contentContainer.addToMyItemsButton.setOnClickListener {
            val selectedColorCode = mViewModel.selectedColor?.code
            val selectedSizeName = mViewModel.selectedSize?.title
            val quantity = mViewModel.quantity
            val shopId = mViewModel.product?.shop?.id
            val selectedBranchId = mViewModel.selectedBranch?.id
            if (selectedColorCode != null && selectedSizeName != null && selectedBranchId != null && shopId != null) {
                val myItemBody = MyItemBody(
                    branch_id = selectedBranchId,
                    color_code = selectedColorCode,
                    product_id = productId,
                    shop_id = shopId,
                    quantity = quantity,
                    size_name = selectedSizeName
                )
                mViewModel.addToMyItem(myItemBody) { myItem ->
                    findNavController().navigate(
                        ProductDetailsFragmentDirections.actionProductDetailsFragmentToAddToMyListDialog(
                            myItem
                        )
                    )
                }
            } else {
                val message = when {
                    selectedColorCode == null -> "You should select color to proceed!"
                    selectedSizeName == null -> "You should select size to proceed!"
                    else -> "You should select branch to proceed!"
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}