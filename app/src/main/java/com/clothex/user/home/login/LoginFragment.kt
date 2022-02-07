package com.clothex.user.home.login

import android.content.Intent
import android.content.IntentSender
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.user.R
import com.clothex.user.databinding.FragmentLoginBinding
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import org.koin.android.ext.android.inject
import retrofit2.HttpException
import com.google.android.gms.auth.api.credentials.IdentityProviders

import com.google.android.gms.auth.api.credentials.CredentialRequest





class LoginFragment : Fragment() {


    private val TAG: String = "LoginFragmetn"
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by inject()

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.setFirstTimeOpen()
        callbackManager = CallbackManager.Factory.create();
        oneTapClient = Identity.getSignInClient(requireActivity())

        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                    .setSupported(true)
                    .build()
            )
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.your_web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            // Automatically sign in when exactly one credential is retrieved.
//            .setAutoSelectEnabled(true)
            .build()
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

        handleGoogleLogin()

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
                it.getOrNull()?.let {
                    findNavController().navigateUp()
                }
                it.exceptionOrNull()?.let { throwable ->
                    val message = if (throwable is HttpException) {
                        throwable.message()
                    } else {
                        throwable.message
                    }
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun handleGoogleLogin() {
        val request = CredentialRequest.Builder()
            .setAccountTypes(IdentityProviders.GOOGLE)
            .setSupportsPasswordLogin(true)
            .build()
        oneTapClient.beginSignIn(signInRequest).addOnSuccessListener(requireActivity()) { result ->
            try {
                startIntentSenderForResult(
                    result.pendingIntent.intentSender, 1001,
                    null, 0, 0, 0, null
                )
            } catch (e: IntentSender.SendIntentException) {
                Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
            }
        }
            .addOnFailureListener(requireActivity()) { e ->
                // No saved credentials found. Launch the One Tap sign-up flow, or
                // do nothing and continue presenting the signed-out UI.
                Log.d(TAG, e.localizedMessage)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1001) {
            try {
                val credential = oneTapClient.getSignInCredentialFromIntent(data)
                val idToken = credential.googleIdToken
                val username = credential.id
                val password = credential.password
                when {
                    idToken != null -> {
                        // Got an ID token from Google. Use it to authenticate
                        // with your backend.
                        Log.d(TAG, "Got ID token.")
                    }
                    password != null -> {
                        // Got a saved username and password. Use them to authenticate
                        // with your backend.
                        Log.d(TAG, "Got password.")
                    }
                    else -> {
                        // Shouldn't happen.
                        Log.d(TAG, "No ID token or password!")
                    }
                }
            } catch (e: ApiException) {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}