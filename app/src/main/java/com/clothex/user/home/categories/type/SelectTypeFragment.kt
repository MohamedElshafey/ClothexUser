package com.clothex.user.home.categories.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.FragmentSelectTypeBinding
import com.clothex.user.home.categories.style.DepartmentFactory
import com.clothex.user.home.product.ProductAdapter
import com.clothex.user.utils.addChip
import com.google.android.material.chip.Chip
import org.koin.android.ext.android.inject

class SelectTypeFragment : Fragment() {

    lateinit var binding: FragmentSelectTypeBinding
    private val viewModel: SelectTypeViewModel by inject()
    private val productAdapter = ProductAdapter {
        findNavController().navigate(
            SelectTypeFragmentDirections.actionSelectTypeFragmentToProductDetailsFragment(it.id)
        )
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
            layoutInflater, *typesArray.map { it.title }.toTypedArray(),
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
                val type = typesArray.find { it.title.equals(selectedTypeName) }
                binding.noItemsIV.isVisible = false
                binding.progressBar.isVisible = true
                viewModel.typeId = type?.id
            } else {
                viewModel.typeId = null
            }
        }

        viewModel.productLiveData.observe(viewLifecycleOwner, {
            binding.progressBar.isVisible = false
            productAdapter.reset()
            binding.noItemsIV.isVisible = it.isEmpty()
            productAdapter.append(it)
        })

        binding.searchBar.doAfterTextChanged {
            binding.noItemsIV.isVisible = false
            binding.progressBar.isVisible = true
            viewModel.searchKey = it?.toString()
        }

        binding.productsRV.adapter = productAdapter

    }

    private fun openSearchFragment() {
//        findNavController().navigate(
//            CategoriesFragmentDirections.actionNavigationCategoriesToSearchProductFragment(true)
//        )
    }

}