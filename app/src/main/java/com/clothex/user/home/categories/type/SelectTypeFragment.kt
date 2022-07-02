package com.clothex.user.home.categories.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.clothex.user.R
import com.clothex.user.databinding.FragmentSelectTypeBinding
import com.clothex.user.home.categories.style.DepartmentFactory
import com.clothex.user.home.product.ProductPagingAdapter
import com.clothex.user.home.search.filter.FilterProductBottomSheet
import com.clothex.user.home.search.sort.SortProductBottomSheet
import com.clothex.user.log.MainLogEvents
import com.clothex.user.utils.addChip
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SelectTypeFragment : Fragment() {

    lateinit var binding: FragmentSelectTypeBinding
    private val viewModel: SelectTypeViewModel by inject()
    private val productAdapter = ProductPagingAdapter() {
        findNavController().navigate(
            SelectTypeFragmentDirections.actionSelectTypeFragmentToProductDetailsFragment(it.id)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(FilterProductBottomSheet.REQUEST_KEY) { _, _ ->
//            resetProductPagingAdapter()
//            viewModel.reset()
            viewModel.fetchProductPage()
        }
        setFragmentResultListener(SortProductBottomSheet.REQUEST_KEY) { _, _ ->
//            resetProductPagingAdapter()
//            viewModel.reset()
            viewModel.fetchProductPage()
        }
    }

    private fun resetProductPagingAdapter() {
        lifecycleScope.launch {
            productAdapter.reset()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val departmentEnum = SelectTypeFragmentArgs.fromBundle(requireArguments()).departmentEnum
        val typesArray = SelectTypeFragmentArgs.fromBundle(requireArguments()).typeList
        val departmentId = SelectTypeFragmentArgs.fromBundle(requireArguments()).departmentId
        val departmentStyle = DepartmentFactory.getDepartmentStyle(departmentEnum)
        viewModel.departmentId = departmentId
        binding.progressBar.isVisible = true
        binding.typesChipGroup.addChip(
            layoutInflater, requireContext().getString(R.string.all),
            layoutRes = R.layout.item_catergory_chip,
            isChecked = true
        )
        binding.typesChipGroup.addChip(
            layoutInflater, *typesArray.map { it.getTitle(viewModel.isArabic()) }.toTypedArray(),
            layoutRes = R.layout.item_catergory_chip
        )

        binding.departmentCard.apply {
            setTitle(context.getString(departmentStyle.title))
            changeBackground(ContextCompat.getColor(context, departmentStyle.backgroundColor))
            ContextCompat.getDrawable(context, departmentStyle.icon)?.let {
                setIcon(it)
            }
        }
        binding.typesChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = binding.typesChipGroup.findViewById(checkedId) as Chip?
            if (chip != null) {
                val selectedTypeName = chip.text.toString()
                val type = typesArray.find { it.getTitle(viewModel.isArabic()) == selectedTypeName }
                binding.notItemsMessage.isVisible = false
                binding.progressBar.isVisible = true
                viewModel.logEvent(MainLogEvents.SelectType(type?.id, selectedTypeName))
                viewModel.typeId = type?.id
            } else {
                viewModel.typeId = null
            }
        }

        productAdapter.addLoadStateListener { loadState ->
            binding.notItemsMessage.isVisible =
                loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && productAdapter.itemCount < 1
        }

        viewModel.productLiveData.observe(viewLifecycleOwner) { pagingData ->
            binding.progressBar.isVisible = false
            binding.productsRV.scrollToPosition(0)
            binding.productsRV.adapter = null
            productAdapter.submitData(lifecycle, pagingData)
            binding.productsRV.adapter = productAdapter
        }

        binding.searchBar.doAfterTextChanged {
            binding.notItemsMessage.isVisible = false
            binding.progressBar.isVisible = true
            viewModel.searchKey = it?.toString()
        }

        binding.productsRV.adapter = productAdapter
        binding.sortContainer.setOnClickListener {
            findNavController().navigate(R.id.sortProductBottomSheet)
//            findNavController().navigate(SearchBaseFragmentDirections.actionSearchProductFragmentToSortProductBottomSheet())
        }

        binding.filterContainer.setOnClickListener {
//            findNavController().navigate(SearchBaseFragmentDirections.actionSearchProductFragmentToFilterProductBottomSheet())
            findNavController().navigate(R.id.filterProductBottomSheet)
        }
    }

    private fun openSearchFragment() {
//        findNavController().navigate(
//            CategoriesFragmentDirections.actionNavigationCategoriesToSearchProductFragment(true)
//        )
    }

}