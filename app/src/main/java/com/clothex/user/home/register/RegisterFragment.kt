package com.clothex.user.home.register

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.sign.SignupBody
import com.clothex.user.R
import com.clothex.user.databinding.FragmentRegisterBinding
import org.koin.android.ext.android.inject
import java.util.regex.Pattern


class RegisterFragment : Fragment() {


    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by inject()

    private val defaultMatcherDelay = 500L
    private val emailMatcherHelper = Handler(Looper.getMainLooper())
    private val emailMatcherRunnable = Runnable {
        if (EMAIL_ADDRESS.matcher(binding.emailTextInputLayout.editText?.text.toString())
                .matches().not()
        ) {
            binding.emailTextInputLayout.error = "Email not matching!"
            binding.emailTextInputLayout.isErrorEnabled = true
        } else {
            binding.emailTextInputLayout.isErrorEnabled = false
        }
    }

    private val phoneNumberHelper = Handler(Looper.getMainLooper())
    private val phoneNumberRunnable = Runnable {
        if (Pattern.compile("([0])([1])([0-9]){9}\$")
                .matcher(binding.phoneNumberTextInputLayout.editText?.text.toString())
                .matches().not()
        ) {
            binding.phoneNumberTextInputLayout.error = "Phone number is not valid"
            binding.phoneNumberTextInputLayout.isErrorEnabled = true
        } else {
            binding.phoneNumberTextInputLayout.isErrorEnabled = false
        }
    }

    private val passwordHelper = Handler(Looper.getMainLooper())
    private val passwordRunnable = Runnable {
        if (Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
                .matcher(binding.passwordTextInputLayout.editText?.text.toString())
                .matches().not()
        ) {
            binding.passwordTextInputLayout.error =
                "must contains 8 characters, at least one letter and one number"
            binding.passwordTextInputLayout.isErrorEnabled = true
        } else {
            binding.passwordTextInputLayout.isErrorEnabled = false
        }
    }

    private val usernameHelper = Handler(Looper.getMainLooper())
    private val usernameRunnable = Runnable {
        if (Pattern.compile("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}\$")
                .matcher(binding.nameTextInputLayout.editText?.text.toString())
                .matches().not()
        ) {
            binding.nameTextInputLayout.error = "Username is not valid!"
            binding.nameTextInputLayout.isErrorEnabled = true
        } else {
            binding.nameTextInputLayout.isErrorEnabled = false
        }
    }

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

        binding.emailTextInputLayout.editText?.doAfterTextChanged {
            it?.let {
                emailMatcherHelper.removeCallbacks(emailMatcherRunnable)
                emailMatcherHelper.postDelayed(emailMatcherRunnable, defaultMatcherDelay)
            }
        }
        binding.phoneNumberTextInputLayout.editText?.doAfterTextChanged {
            it?.let {
                phoneNumberHelper.removeCallbacks(phoneNumberRunnable)
                phoneNumberHelper.postDelayed(phoneNumberRunnable, defaultMatcherDelay)
            }
        }
        binding.passwordTextInputLayout.editText?.doAfterTextChanged {
            it?.let {
                passwordHelper.removeCallbacks(passwordRunnable)
                passwordHelper.postDelayed(passwordRunnable, defaultMatcherDelay)
            }
        }
        binding.nameTextInputLayout.editText?.doAfterTextChanged {
            it?.let {
                usernameHelper.removeCallbacks(usernameRunnable)
                usernameHelper.postDelayed(usernameRunnable, defaultMatcherDelay)
            }
        }
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