package com.clothex.user.home.checkout

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.DialogAddedToMyItemsBinding
import com.clothex.user.my_items.minimal.MinimalItemViewModel

/**
 * Created by Mohamed Elshafey on 05/12/2021.
 */
class AddToMyListDialog : DialogFragment() {

    lateinit var binding: DialogAddedToMyItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Dialog_NoTitle);

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setGravity(Gravity.TOP)
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddedToMyItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val minimalProduct = AddToMyListDialogArgs.fromBundle(requireArguments()).minimalProduct
        binding.colorItem.colorView.apply {
            val backgroundDrawable = background as GradientDrawable
            backgroundDrawable.setColor(Color.parseColor(minimalProduct.colorCode))
            background = backgroundDrawable
        }
        binding.viewModel = MinimalItemViewModel(minimalProduct)
        binding.continueButton.setOnClickListener {
            findNavController().navigate(
                AddToMyListDialogDirections.actionAddToMyListDialogToNavigationHome()
            )
        }
    }
}