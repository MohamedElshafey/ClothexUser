package com.clothex.user.voucher.add_text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentAddTextVoucherBinding

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class AddTextVoucherFragment : Fragment() {
    lateinit var binding: FragmentAddTextVoucherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTextVoucherBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTV.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.voucherButton.setOnClickListener {
            findNavController().navigate(AddTextVoucherFragmentDirections.actionAddTextVoucherBottomSheetToVoucherMessageFragment())
        }
    }

}