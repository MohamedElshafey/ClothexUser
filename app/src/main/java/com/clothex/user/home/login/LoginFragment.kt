package com.clothex.user.home.login

import android.app.Activity
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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.BaseResponseModel
import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.user.R
import com.clothex.user.databinding.FragmentLoginBinding
import com.clothex.user.dialog.MessageAlertDialog
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import org.koin.android.ext.android.inject
import retrofit2.HttpException


class LoginFragment : Fragment() {

    private val TAG: String = "LoginFragment"
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by inject()
    private val callbackManager: CallbackManager = CallbackManager.Factory.create();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.logScreen(LoginFragment::class.java.simpleName)
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
                .logInWithReadPermissions(this, callbackManager, listOf("public_profile", "email"));
        }

        binding.forgetPasswordTV.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        val signUpMsg = getString(R.string.sign_up_msg)
        val spannableStringBuilder = SpannableStringBuilder(signUpMsg)
        val startIndex = signUpMsg.indexOf(".")
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.infinite_night)),
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
            loginViewModel.setFirstTimeOpen()
            findNavController().navigateUp()
        }
        binding.loginButton.setOnClickListener {
            val email = binding.emailTextInputLayout.editText?.text?.toString()
            val password = binding.passwordTextInputLayout.editText?.text?.toString()
            if (email.isNullOrEmpty() || password.isNullOrEmpty()) return@setOnClickListener
            val loginBody = LoginBody(email = email, password = password)
            loginViewModel.login(loginBody) {
                handleLoginResponse(it)
            }
        }
    }

    private fun handleLoginResponse(result: Result<BaseResponseModel<Login>>) {
        result.getOrNull()?.let { response ->
            response.data?.let {
                loginViewModel.setFirstTimeOpen()
                findNavController().navigateUp()
            }
            response.status?.let { status ->
                if (status == 402) {
                    context?.let { context ->
                        MessageAlertDialog.showAlertDialog(
                            context,
                            title = context.getString(R.string.sign_up_first_title),
                            description = context.getString(R.string.sign_up_first_description),
                            positiveButtonText = context.getString(R.string.sign_up),
                            negativeButtonText = context.getString(R.string.cancel),
                            positiveOnClickListener = {
                                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
                            }
                        )
                    }
                }
            }
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

    private val googleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                    val email = account.email
                    val token = account.idToken
                    loginViewModel.login(LoginBody(email = email ?: "", googleToken = token)) {
                        handleLoginResponse(it)
                    }
                } catch (e: ApiException) {
                    Toast.makeText(
                        context,
                        R.string.error_happened_message_title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    private fun handleGoogleLogin() {
        val googleOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.your_web_client_id))
                .requestEmail()
                .build()
        val googleClient = GoogleSignIn.getClient(requireContext(), googleOptions)
        val intent = googleClient.signInIntent
        googleClient.signOut()
        googleLauncher.launch(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}