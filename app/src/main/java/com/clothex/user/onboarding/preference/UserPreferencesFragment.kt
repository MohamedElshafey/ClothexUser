package com.clothex.user.onboarding.preference

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clothex.user.R
import com.clothex.user.databinding.FragmentUserPreferenceBinding
import com.clothex.user.home.HomeActivity
import com.clothex.user.utils.addChip
import com.clothex.user.utils.darken
import com.clothex.user.utils.setShapeColor


class UserPreferencesFragment : Fragment() {

    private lateinit var userPreferencesViewModel: UserPreferencesViewModel
    private var _binding: FragmentUserPreferenceBinding? = null
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
        userPreferencesViewModel = ViewModelProvider(this)[UserPreferencesViewModel::class.java]
        _binding = FragmentUserPreferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.skipTV.setOnClickListener {
            continueToHome()
        }

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

        binding.continueButton.setShapeColor(buttonColor.darken)
        binding.continueButton.setTextColor(Color.WHITE.darken)

        binding.typeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            isCanContinue = true
            binding.continueButton.setShapeColor(buttonColor)
            binding.continueButton.setTextColor(Color.WHITE)
        }

        binding.continueButton.setOnClickListener {
            if (isCanContinue) continueToHome()
        }
    }

    private fun continueToHome() {
        HomeActivity.start(requireContext())
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}