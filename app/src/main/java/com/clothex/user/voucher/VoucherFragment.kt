package com.clothex.user.voucher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.data.voucherList
import com.clothex.user.databinding.FragmentVoucherBinding
import com.clothex.user.utils.addDivider

/**
 * Created by Mohamed Elshafey on 10/12/2021.
 */
class VoucherFragment : Fragment() {

    lateinit var binding: FragmentVoucherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoucherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vouchersRV.adapter = VoucherAdapter(voucherList) {
            findNavController().navigate(
                VoucherFragmentDirections.actionVoucherFragmentToVoucherDetailsFragment(it)
            )
        }
        binding.vouchersRV.addDivider()
        binding.titleTV.setOnClickListener { findNavController().navigateUp() }
        binding.voucherButton.setOnClickListener {
            findNavController().navigate(VoucherFragmentDirections.actionVoucherFragmentToAddNewVoucherBottomSheet())
        }
    }


}