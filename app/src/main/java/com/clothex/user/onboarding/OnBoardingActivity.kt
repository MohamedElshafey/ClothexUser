package com.clothex.user.onboarding

import android.os.Bundle
import com.clothex.user.base.BaseLanguageActivity
import com.clothex.user.databinding.ActivityOnboardingBinding

class OnBoardingActivity : BaseLanguageActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}