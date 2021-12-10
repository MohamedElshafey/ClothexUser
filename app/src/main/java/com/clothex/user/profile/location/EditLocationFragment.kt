package com.clothex.user.profile.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.data.savedLocations
import com.clothex.user.databinding.FragmentEditLocationBinding
import com.clothex.user.utils.setAllOnClickListener

/**
 * Created by Mohamed Elshafey on 10/12/2021.
 */
class EditLocationFragment : Fragment() {

    lateinit var binding: FragmentEditLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTV.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.locationsRV.adapter = EditLocationAdapter(ArrayList(savedLocations))
        binding.addNewLocationGroup.setAllOnClickListener {
            findNavController().navigate(EditLocationFragmentDirections.actionEditLocationFragmentToMapsFragment())
        }
    }

}