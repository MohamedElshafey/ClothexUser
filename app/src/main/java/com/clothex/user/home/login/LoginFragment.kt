package com.clothex.user.home.login

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.user.R
import com.clothex.user.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject


class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.setFirstTimeOpen()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.forgetPasswordTV.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        val signUpMsg = getString(R.string.sign_up_msg)
        val spannableStringBuilder = SpannableStringBuilder(signUpMsg)
        val startIndex = signUpMsg.indexOf(".")
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(Color.parseColor("#666666")),
            startIndex + 1,
            signUpMsg.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        val myTypeface = Typeface.create(
            ResourcesCompat.getFont(
                requireContext(),
                R.font.ibm_plex_sans_arabic_bold
            ), Typeface.BOLD
        )
        spannableStringBuilder.setSpan(
            myTypeface,
            startIndex + 1,
            signUpMsg.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        binding.signUpTV.text = spannableStringBuilder
        binding.signUpTV.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        binding.forgetPasswordTV.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment())
        }
        binding.skipButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.bottomButton.setOnClickListener {
            val phoneNumber = binding.phoneNumberTextInputLayout.editText?.text?.toString()
            val password = binding.passwordTextInputLayout.editText?.text?.toString()
            if (phoneNumber.isNullOrEmpty() || password.isNullOrEmpty()) return@setOnClickListener
            val loginBody = LoginBody(phoneNumber = phoneNumber, password = password)
            loginViewModel.login(loginBody) {
                val login: Login? = it.getOrNull()
                if (login != null) {
                    findNavController().navigateUp()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Can't login, please try again!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}