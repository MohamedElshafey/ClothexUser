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
import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.user.R
import com.clothex.user.databinding.FragmentLoginBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import org.koin.android.ext.android.inject
import retrofit2.HttpException


class LoginFragment : Fragment() {


    private val TAG: String = "LoginFragment"
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
                    .setServerClientId(getString(R.string.your_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
        loginViewModel.getTokenIfNeeded()
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

        binding.googleSignInButton.setOnClickListener {
            oneTapClient.signOut()
            handleGoogleLogin()
        }

        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Log.d(TAG, "onCancel: ")
                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "onError: $error")
                }

                override fun onSuccess(result: LoginResult) {
                    val request = GraphRequest.newMeRequest(result.accessToken) { obj, response ->
                        val name = obj?.getString("name")
                        val email = obj?.getString("email")
                        val token = result.accessToken
                        if (email?.isNotEmpty() == true && token.token.isNotEmpty())
                            loginViewModel.login(
                                LoginBody(
                                    email = email,
                                    facebookToken = token.token
                                )
                            ) {
                                handleLoginResponse(it)
                            }
                    }
                    val parameters = Bundle()
                    parameters.putString("fields", "id,name,email,gender, birthday")
                    request.parameters = parameters
                    request.executeAsync()
                    Log.d(TAG, "onSuccess: $result")
                }
            })

        binding.facebookSignInButton.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("public_profile"));
        }

//        handleGoogleLogin()

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
            val email = binding.emailTextInputLayout.editText?.text?.toString()
            val password = binding.passwordTextInputLayout.editText?.text?.toString()
            if (email.isNullOrEmpty() || password.isNullOrEmpty()) return@setOnClickListener
            val loginBody = LoginBody(email = email, password = password)
            loginViewModel.login(loginBody) {
                handleLoginResponse(it)
            }
        }
    }

    private fun handleLoginResponse(result: Result<Login>) {
        result.getOrNull()?.let {
            findNavController().navigateUp()
        }
        result.exceptionOrNull()?.let { throwable ->
            val message = if (throwable is HttpException) {
                throwable.message()
            } else {
                throwable.message
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

    private fun handleGoogleLogin() {
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
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            try {
                val credential = oneTapClient.getSignInCredentialFromIntent(data)
                val idToken = credential.googleIdToken
                val email = credential.id
                loginViewModel.login(LoginBody(email = email, googleToken = idToken)) {
                    handleLoginResponse(it)
                }
            } catch (e: ApiException) {
                Log.d(TAG, "onActivityResult:$e ")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}