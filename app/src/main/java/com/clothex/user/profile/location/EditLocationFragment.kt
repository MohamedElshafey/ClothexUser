package com.clothex.user.profile.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.local.room.entity.SavedLocation
import com.clothex.user.databinding.FragmentEditLocationBinding
import com.clothex.user.profile.location.adapter.EditLocationAdapter
import com.clothex.user.profile.location.adapter.EditLocationCallback
import com.clothex.user.utils.setAllOnClickListener
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 10/12/2021.
 */
class EditLocationFragment : Fragment() {

    lateinit var binding: FragmentEditLocationBinding
    private val viewModel: EditLocationViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val editLocationCallback = object : EditLocationCallback {
        override fun onItemDeleted(savedLocation: SavedLocation) {
            viewModel.deleteLocation(savedLocation)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchLocations()
        binding.actionBar.setOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.locationListLiveData.observe(viewLifecycleOwner, {
            binding.locationsRV.adapter = EditLocationAdapter(it, editLocationCallback)
        })
        binding.addNewLocationGroup.setAllOnClickListener {
            findNavController().navigate(EditLocationFragmentDirections.actionEditLocationFragmentToMapsFragment())
        }
    }

}