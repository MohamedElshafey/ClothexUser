package com.clothex.user.onboarding.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentSplashBinding
import com.clothex.user.home.HomeActivity
import org.koin.android.ext.android.inject

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val viewModel: SplashViewModel by inject()
    private val binding get() = _binding!!
    private val delaySeconds = 4 * 1000L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.tokenLiveData.observe(viewLifecycleOwner) {
                viewModel.shouldNavigateToOnBoarding.observe(viewLifecycleOwner) { shouldOpenOnBoarding ->
                    if (shouldOpenOnBoarding) {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
                    } else {
                        HomeActivity.start(requireContext())
                        activity?.finish()
                    }
                }

            }
        }, delaySeconds)
        ObjectAnimator.ofFloat(binding.backgroundImg, "translationY", -200f).apply {
            duration = delaySeconds
            start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}