package com.clothex.user.voucher.add_text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.FragmentAddTextVoucherBinding
import com.clothex.user.log.MainLogEvents
import org.koin.android.ext.android.inject
import retrofit2.HttpException

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class AddTextVoucherFragment : Fragment() {
    lateinit var binding: FragmentAddTextVoucherBinding
    private val viewModel: CodeVoucherViewModel by inject()

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
        val voucherId = arguments?.getString("voucherId")
        if (voucherId.isNullOrEmpty().not()) {
            binding.codeInputLayout.editText?.setText(voucherId)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.logScreen(AddTextVoucherFragment::class.java.simpleName)
        viewModel.logEvent(MainLogEvents.AddTextVoucher)
        binding.actionBar.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.voucherButton.setOnClickListener {
            val code = binding.codeInputLayout.editText?.text?.toString()
            if (code.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.fill_voucher_code_message),
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                if (viewModel.isSendVoucher.not()) {
                    viewModel.logEvent(MainLogEvents.AddVoucher(code))
                    viewModel.addVoucher(code = code)
                }
            }
        }


        viewModel.responseLiveData.observe(viewLifecycleOwner) {
            it.getOrNull()?.let {
                if (it.data == true) {
                    findNavController().navigate(AddTextVoucherFragmentDirections.actionAddTextVoucherBottomSheetToVoucherMessageFragment())
                }
                if (it.message.isNullOrEmpty().not())
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
            it.exceptionOrNull()?.let {
                Toast.makeText(requireContext(), (it as HttpException).message(), Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

}