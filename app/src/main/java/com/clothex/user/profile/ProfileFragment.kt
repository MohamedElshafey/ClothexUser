package com.clothex.user.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentProfileBinding
import com.clothex.user.utils.setAllOnClickListener

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewModel = profileViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToLoginFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}