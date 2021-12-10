package com.clothex.user.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentEditProfileBinding

/**
 * Created by Mohamed Elshafey on 10/12/2021.
 */
class EditProfileFragment : Fragment() {

    lateinit var binding: FragmentEditProfileBinding
    private lateinit var mViewModel: EditProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTV.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}