package com.clothex.user.voucher.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentVoucherDetailsBinding

/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
class VoucherDetailsFragment : Fragment() {
    private lateinit var mViewModel: VoucherDetailsViewModel
    private var _binding: FragmentVoucherDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel =
            VoucherDetailsViewModel(VoucherDetailsFragmentArgs.fromBundle(requireArguments()).voucher)
        _binding = FragmentVoucherDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
    }

}