package com.clothex.user.home.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private lateinit var categoriesViewModel: CategoriesViewModel
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        binding.viewModel = categoriesViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchBar.apply {
            searchET.isClickable = false
            searchET.isFocusable = false
            searchBarContainer.setOnClickListener { openSearchFragment() }
            searchET.setOnClickListener { openSearchFragment() }
            menu.setOnClickListener { openSearchFragment() }
        }

        binding.notificationIV.setOnClickListener {
            findNavController().navigate(CategoriesFragmentDirections.actionNavigationCategoriesToNotificationFragment())
        }

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