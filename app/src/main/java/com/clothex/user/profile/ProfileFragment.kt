package com.clothex.user.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentProfileBinding
import com.clothex.user.home.HomeActivity
import com.clothex.user.utils.setAllOnClickListener
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by inject()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUser()
        binding.prefGroup.setAllOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToEditPreferencesFragment())
        }
        binding.voucherGroup.setAllOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToVoucherFragment())
        }
        binding.locationGroup.setAllOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToEditLocationFragment())
        }
        binding.editProfileGroup.setAllOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToEditProfileFragment())
        }
        binding.languageGroup.setAllOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToSelectLanguageBottomSheet())
        }
        binding.logoutGroup.setAllOnClickListener() {
            viewModel.logout()
            requireActivity().finish()
            context?.let { context -> HomeActivity.start(context) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}