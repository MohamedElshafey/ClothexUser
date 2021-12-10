package com.clothex.user.voucher.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clothex.user.databinding.FragmentScanVoucherBinding

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
class ScanVoucherFragment : Fragment() {
    lateinit var binding: FragmentScanVoucherBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanVoucherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actionButton.setOnClickListener {

        }
    }
}