package com.clothex.user.home.book.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.databinding.FragmentBookRequestBinding

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
class RequestBookFragment : Fragment() {

    lateinit var binding: FragmentBookRequestBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.doneButton.setOnClickListener {
            findNavController().navigate(RequestBookFragmentDirections.actionRequestBookFragmentToNavigationHome())
        }
        binding.trackOrderButton.setOnClickListener {
            findNavController().navigate(RequestBookFragmentDirections.actionRequestBookFragmentToNavigationMyItems())
        }
    }
}