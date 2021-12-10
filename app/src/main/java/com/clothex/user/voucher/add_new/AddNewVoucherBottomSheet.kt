package com.clothex.user.voucher.add_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.databinding.AddNewVoucherBottomSheetBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class AddNewVoucherBottomSheet : DefaultBottomSheet() {
    lateinit var binding: AddNewVoucherBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddNewVoucherBottomSheetBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addTextTV.setOnClickListener {
            findNavController().navigate(AddNewVoucherBottomSheetDirections.actionAddNewVoucherBottomSheetToAddTextVoucherBottomSheet())
        }
        binding.scanQRTV.setOnClickListener {
            findNavController().navigate(AddNewVoucherBottomSheetDirections.actionAddNewVoucherBottomSheetToScanVoucherFragment())
        }
    }

}