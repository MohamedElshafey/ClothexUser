package com.clothex.user.home.register

import android.graphics.Color
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
import com.clothex.data.domain.model.sign.SignupBody
import com.clothex.user.R
import com.clothex.user.databinding.FragmentRegisterBinding
import org.koin.android.ext.android.inject


class RegisterFragment : Fragment() {


    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signInMsg = getString(R.string.sign_in_msg)
        val spannableStringBuilder = SpannableStringBuilder(signInMsg)
        val startIndex = signInMsg.indexOf(".")
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(Color.parseColor("#666666")),
            startIndex + 1,
            signInMsg.length,
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
            signInMsg.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        binding.signInTV.text = spannableStringBuilder
        binding.signInTV.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.bottomButton.setOnClickListener {
            val email = binding.emailTextInputLayout.editText?.text?.toString()
            val username = binding.nameTextInputLayout.editText?.text?.toString()
            val phoneNumber = binding.phoneNumberTextInputLayout.editText?.text?.toString()
            val password = binding.passwordTextInputLayout.editText?.text?.toString()
            if (email.isNullOrEmpty().not() && username.isNullOrEmpty().not() &&
                phoneNumber.isNullOrEmpty().not() && password.isNullOrEmpty().not()
            ) {
                val signupBody = SignupBody(
                    email = email!!,
                    password = password!!,
                    phone_number = phoneNumber!!,
                    username = username!!
                )
                viewModel.signup(signupBody) {
                    val simpleResponse = it.getOrNull()
                    if (simpleResponse != null) {
                        if (simpleResponse.success) {
                            Toast.makeText(
                                requireContext(),
                                "You signed up successfully, now please Login!",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigateUp()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                simpleResponse.message,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Network error!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}