package com.clothex.user.voucher.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.FragmentVoucherMessageBinding

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
class VoucherMessageFragment : Fragment() {

    lateinit var binding: FragmentVoucherMessageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoucherMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.doneButton.setOnClickListener {
//            findNavController().navigateUp()
//            findNavController().popBackStack(R.id.voucherMessageFragment, true)
//            findNavController().navigate(R.id.voucherFragment)
            findNavController().navigate(VoucherMessageFragmentDirections.actionVoucherMessageFragmentToVoucherFragment())
        }
    }
}