package com.clothex.user.home.password.reset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.FragmentResetPasswordBinding
import com.clothex.user.dialog.MessageAlertDialog

/**
 * Created by Mohamed Elshafey on 20/12/2021.
 */
class ResetPasswordFragment : Fragment() {
    private lateinit var mViewModel: ResetPasswordViewModel
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = ViewModelProvider(this)[ResetPasswordViewModel::class.java]
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resetPassButton.setOnClickListener {
            MessageAlertDialog.showAlertDialog(
                requireContext(),
                getString(R.string.password_resetted_succssfully_message),
                iconRes = R.drawable.ic_success,
                positiveButtonText = getString(R.string.login),
                positiveOnClickListener = {
                    findNavController().navigate(ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment())
                }
            )
        }
    }

}