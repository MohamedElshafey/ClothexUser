package com.clothex.user.home.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.department.Department
import com.clothex.user.databinding.FragmentCategoriesBinding
import com.clothex.user.home.categories.style.DepartmentEnum
import com.clothex.user.utils.kidsDepartment
import com.clothex.user.utils.menDepartment
import com.clothex.user.utils.womenDepartment
import org.koin.android.ext.android.inject

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategoriesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    private var departmentsList: List<Department> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmerFrame.startShimmer()
        binding.shimmerFrame.isVisible = true
        viewModel.fetchDepartments()

        binding.searchBar.apply {
            searchET.isClickable = false
            searchET.isFocusable = false
            searchBarContainer.setOnClickListener { openSearchFragment() }
            searchET.setOnClickListener { openSearchFragment() }
            menu.setOnClickListener { openSearchFragment() }
        }

        viewModel.departmentListLiveData.observe(viewLifecycleOwner) { result ->
            result.getOrNull()?.let { list ->
                binding.shimmerFrame.isVisible = false
                val hasMen = list.find { it.title.equals(menDepartment, true) } != null
                val hasWomen = list.find { it.title.equals(womenDepartment, true) } != null
                val hasKids = list.find { it.title.equals(kidsDepartment, true) } != null
                binding.menCard.isVisible = hasMen
                binding.womenCard.isVisible = hasWomen
                binding.kidsCard.isVisible = hasKids
                departmentsList = list
            }
            result.exceptionOrNull()?.let {
                binding.shimmerFrame.isVisible = false
            }
        }

        binding.menCard.setOnClickListener {
            openSelectType(DepartmentEnum.MEN)
        }
        binding.womenCard.setOnClickListener {
            openSelectType(DepartmentEnum.WOMEN)
        }
        binding.kidsCard.setOnClickListener {
            openSelectType(DepartmentEnum.KIDS)
        }
    }

    private fun openSelectType(departmentEnum: DepartmentEnum) {
        val selectedDepartment =
            departmentsList.find { it.title.equals(departmentEnum.value, true) }
        val departmentId = selectedDepartment?.id
        val types = selectedDepartment?.types
        findNavController().navigate(
            CategoriesFragmentDirections.actionNavigationCategoriesToSelectTypeFragment(
                departmentId ?: "",
                departmentEnum,
                types?.toTypedArray() ?: arrayOf()
            )
        )
    }

    private fun openSearchFragment() {
        findNavController().navigate(
            CategoriesFragmentDirections.actionNavigationCategoriesToSearchProductFragment(true)
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}