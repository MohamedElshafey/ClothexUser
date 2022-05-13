package com.clothex.user.home.register

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.sign.SignupBody
import com.clothex.user.R
import com.clothex.user.databinding.FragmentRegisterBinding
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
import java.util.regex.Pattern


class RegisterFragment : Fragment() {

    private val TAG = RegisterFragment::class.simpleName
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by inject()

    private val defaultMatcherDelay = 500L
    private val emailMatcherHelper = Handler(Looper.getMainLooper())
    private val emailMatcherRunnable = Runnable {
        if (EMAIL_ADDRESS.matcher(binding.emailTextInputLayout.editText?.text.toString())
                .matches().not()
        ) {
            showEmailError()
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
            showPhoneError()
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
            showPasswordError()
        } else {
            binding.passwordTextInputLayout.isErrorEnabled = false
        }
    }

    private val usernameHelper = Handler(Looper.getMainLooper())
    private val usernameRunnable = Runnable {
        if (Pattern.compile("^(?!\\s*\$).+")
                .matcher(binding.nameTextInputLayout.editText?.text.toString())
                .matches().not()
        ) {
            showNameError()
        } else {
            binding.nameTextInputLayout.isErrorEnabled = false
        }
    }

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private val callbackManager: CallbackManager = CallbackManager.Factory.create();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        viewModel.getTokenIfNeeded()
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
        binding.signupButton.setOnClickListener {
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
                viewModel.signup(signupBody) { result ->
                    if (result.isSuccess) {
                        handleSignupResponse(result)
                    } else {
                        Toast.makeText(
                            context,
                            context?.getString(R.string.error_happened_message_title),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                showNameError()
                showPhoneError()
                showEmailError()
                showPasswordError()
            }
        }
        binding.googleSignUpButton.setOnClickListener {
            oneTapClient.signOut()
            handleGoogleLogin()
        }

        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Log.d(TAG, "onCancel: ")
                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "onError: ")
                }

                override fun onSuccess(result: LoginResult) {
                    Log.d(TAG, "onSuccess: ")
                    val request = GraphRequest.newMeRequest(result.accessToken) { obj, response ->
                        val name = obj?.getString("name")
                        val email = obj?.getString("email")
                        val accessToken = result.accessToken
                        if (email?.isNotEmpty() == true && accessToken.token.isNotEmpty())
                            viewModel.signup(
                                SignupBody(
                                    email = email,
                                    username = name ?: "",
                                    facebookToken = accessToken.token
                                )
                            ) {
                                handleSignupResponse(it)
                            }
                    }
                    val parameters = Bundle()
                    parameters.putString("fields", "id,name,email,gender, birthday")
                    request.parameters = parameters
                    request.executeAsync()
                }
            })

        binding.facebookSignUpButton.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"));
        }
    }

    private fun showNameError() {
        binding.nameTextInputLayout.error = getString(R.string.invalid_username)
        binding.nameTextInputLayout.isErrorEnabled = true
    }

    private fun showPhoneError() {
        binding.phoneNumberTextInputLayout.error = getString(R.string.invalid_phone_number)
        binding.phoneNumberTextInputLayout.isErrorEnabled = true
    }

    private fun showEmailError() {
        binding.emailTextInputLayout.error = getString(R.string.invalid_email)
        binding.emailTextInputLayout.isErrorEnabled = true
    }

    private fun showPasswordError() {
        binding.passwordTextInputLayout.error = getString(R.string.invalide_password)
        binding.passwordTextInputLayout.isErrorEnabled = true
    }

    private fun handleSignupResponse(result: Result<SimpleResponse>) {
        result.getOrNull()?.let { simpleResponse ->
            if (simpleResponse.success) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.signed_up_successfully),
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
        }
        result.exceptionOrNull()?.let { throwable ->
            Toast.makeText(
                requireContext(),
                (throwable as HttpException).message(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            try {
                val credential = oneTapClient.getSignInCredentialFromIntent(it.data)
                val idToken = credential.googleIdToken
                val email = credential.id
                val signupBody = SignupBody(
                    email = email,
                    googleToken = idToken,
                    username = credential.displayName ?: ""
                )
                viewModel.signup(signupBody) {
                    handleSignupResponse(it)
                }
            } catch (e: ApiException) {
                Log.d(TAG, ":$e ")
            }
        }

    private fun handleGoogleLogin() {
        oneTapClient.beginSignIn(signInRequest).addOnSuccessListener { result ->
            launcher.launch(IntentSenderRequest.Builder(result.pendingIntent.intentSender).build())
        }.addOnFailureListener { e ->
            e.localizedMessage?.let { Log.d(TAG, it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}