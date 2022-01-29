package com.clothex.user.voucher.scan.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.databinding.QrBottomSheetBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class QRBottomSheet : DefaultBottomSheet() {

    lateinit var binding: QrBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QrBottomSheetBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitmap = QRBottomSheetArgs.fromBundle(requireArguments()).bitmap
        binding.imageView.setImageBitmap(bitmap)
    }

}