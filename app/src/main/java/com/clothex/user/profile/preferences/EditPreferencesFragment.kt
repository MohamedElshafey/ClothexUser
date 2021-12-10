package com.clothex.user.profile.preferences

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.FragmentEditUserPreferenceBinding
import com.clothex.user.utils.addChip
import com.clothex.user.utils.darken
import com.clothex.user.utils.setShapeColor


class EditPreferencesFragment : Fragment() {

    private lateinit var userPreferencesViewModel: EditPreferencesViewModel
    private var _binding: FragmentEditUserPreferenceBinding? = null
    private val binding get() = _binding!!
    private var isCanContinue = false
    private val typeList = listOf("Men", "Women", "Kids")
    private val subTypeList = listOf("Sports", "Casual", "Semi-formal", "Smart casual", "Classic")
    private val sizeList = listOf("XS", "S", "M", "L", "XL")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userPreferencesViewModel = ViewModelProvider(this)[EditPreferencesViewModel::class.java]
        _binding = FragmentEditUserPreferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeList.forEach {
            binding.typeChipGroup.addChip(layoutInflater, it)
        }

        subTypeList.forEach {
            binding.subTypeChipGroup.addChip(layoutInflater, it)
        }

        sizeList.forEach {
            binding.sizeChipGroup.addChip(layoutInflater, it)
        }

        val buttonColor = ContextCompat.getColor(requireContext(), R.color.island_aqua)

        binding.saveButton.setShapeColor(buttonColor.darken)
        binding.saveButton.setTextColor(Color.WHITE.darken)

        binding.typeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            isCanContinue = true
            binding.saveButton.setShapeColor(buttonColor)
            binding.saveButton.setTextColor(Color.WHITE)
        }

        binding.saveButton.setOnClickListener {
//            if (isCanContinue) continueToHome()
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}