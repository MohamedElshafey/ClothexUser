package com.clothex.user.voucher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.user.R
import com.clothex.user.databinding.FragmentVoucherBinding
import com.clothex.user.utils.addDivider
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 10/12/2021.
 */
class VoucherFragment : Fragment(), (Voucher) -> Unit {

    lateinit var binding: FragmentVoucherBinding
    private val viewModel: VoucherViewModel by inject()

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
        viewModel.logScreen(VoucherFragment::class.java.simpleName)
        viewModel.checkIfLoginTemporary()
        viewModel.fetchVouchers()
        binding.progressBar.isVisible = true
        viewModel.vouchersLiveData.observe(viewLifecycleOwner) { voucherList ->
            binding.progressBar.isGone = true
            binding.vouchersRV.adapter = VoucherAdapter(voucherList, this, viewModel.isArabic())
        }
        binding.vouchersRV.addDivider()
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
        binding.voucherButton.setOnClickListener {
            viewModel.isLoginTemporaryLiveData.observe(viewLifecycleOwner) { isLoginTemporary ->
                if (isLoginTemporary.not()) {
                    findNavController().navigate(VoucherFragmentDirections.actionVoucherFragmentToAddNewVoucherBottomSheet())
                } else {
                    Toast.makeText(requireContext(), R.string.login_first, Toast.LENGTH_LONG)
                        .show()
                    findNavController().navigate(VoucherFragmentDirections.actionVoucherFragmentToLoginFragment())
                }
            }

        }
    }

    override fun invoke(voucher: Voucher) {
        findNavController().navigate(
            VoucherFragmentDirections.actionVoucherFragmentToVoucherDetailsFragment(voucher)
        )
    }

}