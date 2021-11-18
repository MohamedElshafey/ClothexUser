package com.clothex.user.onboarding.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var profileViewModel: SplashViewModel
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val delaySeconds = 4 * 1000L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
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