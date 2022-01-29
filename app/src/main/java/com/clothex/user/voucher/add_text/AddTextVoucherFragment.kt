package com.clothex.user.voucher.add_text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentAddTextVoucherBinding
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTV.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.voucherButton.setOnClickListener {
            val code = binding.codeInputLayout.editText?.text?.toString()
            if (code.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please fill the code first!", Toast.LENGTH_LONG)
                    .show()
            } else {
                if (viewModel.isSendVoucher.not())
                    viewModel.addVoucher(code = code)
            }
        }
        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            it.getOrNull()?.let {
                findNavController().navigate(AddTextVoucherFragmentDirections.actionAddTextVoucherBottomSheetToVoucherMessageFragment())
                if (it.message.isNullOrEmpty().not())
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
            it.exceptionOrNull()?.let {
                Toast.makeText(requireContext(), (it as HttpException).message(), Toast.LENGTH_LONG)
                    .show()
            }
        })

    }

}