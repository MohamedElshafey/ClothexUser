package com.clothex.user.onboarding.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clothex.user.R
import com.clothex.user.databinding.FragmentOnboardingBinding
import com.clothex.user.home.HomeActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val viewModel: OnBoardingViewModel by inject()
    private val binding get() = _binding!!
    private val onBoardingItemList = listOf(
        OnBoardingModel(
            R.drawable.onboarding_image_1,
            R.string.find_what_you_prefer,
            R.string.lorem_ipsum
        ),
        OnBoardingModel(
            R.drawable.onboarding_image_2,
            R.string.find_what_you_prefer,
            R.string.lorem_ipsum
        ),
        OnBoardingModel(
            R.drawable.onboarding_image_3,
            R.string.find_what_you_prefer,
            R.string.lorem_ipsum
        ),
        OnBoardingModel(
            R.drawable.onboarding_image_4,
            R.string.find_what_you_prefer,
            R.string.lorem_ipsum
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.changeVisitOnBoardingState()
        binding.viewPager.adapter = OnBoardingAdapter(onBoardingItemList) { position ->
            if (position == onBoardingItemList.size - 1) {
//                findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToUserPreferencesFragment())
                HomeActivity.start(requireContext())
                activity?.finish()
            } else {
                binding.viewPager.setCurrentItem(position + 1, true)
            }
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position -> }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}