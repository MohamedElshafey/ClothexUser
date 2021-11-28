package com.clothex.user.home.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.data.savedLocations
import com.clothex.user.databinding.SelectLocationBottomSheetBinding
import com.clothex.user.home.location.adapter.SavedLocationAdapter

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class SelectLocationBottomSheet : DefaultBottomSheet() {
    lateinit var binding: SelectLocationBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SelectLocationBottomSheetBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.currentLocationTV.text = "30 Abbas El Akkad, Nasr City"
        binding.savedLocationRV.adapter = SavedLocationAdapter(savedLocations)
        binding.chooseLocationContainer.setOnClickListener {
            findNavController().navigate(SelectLocationBottomSheetDirections.actionSelectLocationBottomSheetToMapsFragment())
        }
    }

}