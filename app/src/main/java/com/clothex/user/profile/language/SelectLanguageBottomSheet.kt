package com.clothex.user.profile.language

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.clothex.user.R
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.databinding.SelectLanguageBottomSheetBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class SelectLanguageBottomSheet : DefaultBottomSheet() {
    lateinit var binding: SelectLanguageBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SelectLanguageBottomSheetBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectEnglishLanguage()
        binding.arabicContainer.setOnClickListener {
            selectArabicLanguage()
        }
        binding.englishContainer.setOnClickListener {
            selectEnglishLanguage()
        }

    }

    fun View.isSelected(selected: Boolean) {
        if (selected) {
            val color = ContextCompat.getColor(
                context,
                R.color.island_aqua_alpha
            )
            setBackgroundColor(color)
        } else {
            setBackgroundColor(Color.WHITE)
        }
    }

    private fun selectArabicLanguage() {
        binding.arabicSelectIV.visibility = VISIBLE
        binding.arabicContainer.isSelected(true)
        binding.englishSelectIV.visibility = GONE
        binding.englishContainer.isSelected(false)

    }

    private fun selectEnglishLanguage() {
        binding.arabicSelectIV.visibility = GONE
        binding.arabicContainer.isSelected(false)
        binding.englishSelectIV.visibility = VISIBLE
        binding.englishContainer.isSelected(true)
    }

}