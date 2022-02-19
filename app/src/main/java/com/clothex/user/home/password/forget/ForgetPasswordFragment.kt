package com.clothex.user.home.password.forget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.FragmentForgetPasswordBinding
import com.clothex.user.dialog.MessageAlertDialog

/**
 * Created by Mohamed Elshafey on 20/12/2021.
 */
class ForgetPasswordFragment : Fragment() {
    private lateinit var mViewModel: ForgetPasswordViewModel
    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = ViewModelProvider(this)[ForgetPasswordViewModel::class.java]
        _binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resetPassButton.setOnClickListener {
            MessageAlertDialog.showAlertDialog(
                requireContext(),
                getString(R.string.reset_password_mail_sent),
                binding.emailTextInputLayout.editText?.text.toString(),
                getString(R.string.done),
                null,
                iconRes = R.drawable.ic_mail_sent,
                positiveOnClickListener = {
                    findNavController().navigate(ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToResetPasswordFragment())
                }
            )
        }
    }

}