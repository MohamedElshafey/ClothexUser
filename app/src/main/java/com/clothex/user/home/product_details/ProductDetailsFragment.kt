package com.clothex.user.home.product_details

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView.ScaleType
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.clothex.data.domain.model.body.MyItemBody
import com.clothex.data.domain.model.product.Media
import com.clothex.data.domain.model.product.Product
import com.clothex.user.R
import com.clothex.user.databinding.FragmentProductDetailsBinding
import com.clothex.user.home.branch.BranchAdapter
import com.clothex.user.home.color.ColorsAdapter
import com.clothex.user.home.image.ImageAdapter
import com.clothex.user.home.image.ImageSelectedListener
import com.clothex.user.utils.*
import com.google.android.material.chip.Chip
import org.koin.android.ext.android.inject


/**
 * Created by Mohamed Elshafey on 29/11/2021.
 */
class ProductDetailsFragment : Fragment(), ImageSelectedListener {

    private val mViewModel: ProductDetailsViewModel by inject()
    lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.logScreen(ProductDetailsFragment::class.java.simpleName)
        binding.backIV.setRotationByLocale()

        binding.contentContainer.shimmerFrame.startShimmer()
        binding.contentContainer.shimmerFrame.isGone = false

        val productId = ProductDetailsFragmentArgs.fromBundle(requireArguments()).productId
        mViewModel.getProductDetails(productId)

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.mainImagesRV)

        mViewModel.productMutableLiveData.observe(viewLifecycleOwner) {
            binding.contentContainer.shimmerFrame.hideShimmer()
            binding.contentContainer.shimmerFrame.isGone = true
            updateProductData(it)
        }

        binding.contentContainer.minusIV.isEnabled = false

        binding.contentContainer.listPriceTV.paintFlags =
            binding.contentContainer.listPriceTV.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        binding.toolbar.setHeightPercentage(15)
        binding.appbar.setHeightPercentage(50)

        mViewModel.mainImagesLiveData.observe(viewLifecycleOwner) {
            binding.mainImagesRV.adapter =
                ImageAdapter(
                    it,
                    scaleType = ScaleType.CENTER_INSIDE,
                    onImageSelectedListener = this
                )
            binding.indicator.attachToRecyclerView(binding.mainImagesRV, pagerSnapHelper)
        }

        mViewModel.colorsLiveData.observe(viewLifecycleOwner) {
            val list: List<String> = it.map { it.code ?: "" }
            binding.contentContainer.colorRV.adapter = ColorsAdapter(list, true) { color ->
                mViewModel.selectColor(color)
            }
        }

        mViewModel.sizesLiveData.observe(viewLifecycleOwner) { list ->
            binding.contentContainer.sizeChipGroup.removeAllViews()
            binding.contentContainer.sizeChipGroup.addChip(
                layoutInflater,
                *list.map { it.title }.toTypedArray()
            )
            binding.contentContainer.sizeChipGroup.check(
                binding.contentContainer.sizeChipGroup.findViewWithTag<Chip>(
                    list.first().title
                ).id
            )
        }

        binding.contentContainer.sizeChipGroup.setOnCheckedChangeListener { _, checkedId ->
            val chip = binding.contentContainer.sizeChipGroup.findViewById(checkedId) as Chip?
            val selectedSize =
                mViewModel.sizesLiveData.value?.first { it.title == chip?.text.toString() }
            selectedSize?.let { mViewModel.selectSize(it) }
        }

        mViewModel.branchesLiveData.observe(viewLifecycleOwner) {
            binding.contentContainer.branchesRV.adapter =
                BranchAdapter(it, mViewModel.isArabic()) { branch ->
                    mViewModel.selectBranch(branch)
                }
            if (it.isNotEmpty())
                mViewModel.selectBranch(it.first())
        }

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
                    selectedColorCode == null -> getString(R.string.select_color_first)
                    selectedSizeName == null -> getString(R.string.select_size_first)
                    else -> getString(R.string.select_branch_first)
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.shareIV.setOnClickListener {
            mViewModel.product?.id?.let { id ->
                context?.shareDeepLink(getProductDetailsDeeplink(id))
            }
        }
    }

    private fun updateProductData(product: Product?) {
        val sellingPrice = product?.salePrice ?: product?.price
        binding.contentContainer.apply {
            listPriceTV.text = if (product?.salePrice != null)
                String.format(getString(R.string.egp), product.price) else null
            chooseColorTitleTV.isVisible = true
            sellingPriceTV.text =
                String.format(requireContext().getString(R.string.egp), sellingPrice)
            titleTV.text = product?.getTitle(mViewModel.isArabic())
            shopTitleTV.text = product?.shop?.getName(mViewModel.isArabic())
            setImageFromUrl(logoIV, product?.shop?.logo?.source)
        }
    }

    override fun onImageSelected(mediaList: List<Media>, selectedIndex: Int) {
        findNavController().navigate(
            ProductDetailsFragmentDirections.actionProductDetailsFragmentToImageViewerDialog(
                mediaList.toTypedArray(), selectedIndex = selectedIndex
            )
        )
    }

}