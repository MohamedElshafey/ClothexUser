package com.clothex.user.home.location

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.clothex.data.local.room.entity.SavedLocation
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.databinding.SelectLocationBottomSheetBinding
import com.clothex.user.home.location.adapter.SavedLocationAdapter
import com.clothex.user.home.location.adapter.SavedLocationCallback
import com.clothex.user.utils.KEY_DISMISS
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class SelectLocationBottomSheet : DefaultBottomSheet() {
    lateinit var binding: SelectLocationBottomSheetBinding
    private val viewModel: SelectLocationViewModel by inject()

    private val savedLocationCallback = object : SavedLocationCallback {
        override fun onItemSelected(savedLocation: SavedLocation) {
            viewModel.saveLocation(savedLocation)
            findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY_DISMISS, true)
            dismiss()
        }

        override fun onItemUnSelected(savedLocation: SavedLocation) {
            viewModel.saveLocation(savedLocation)
        }
    }

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
        viewModel.fetchLocations()
        viewModel.locationListLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty())
                binding.savedLocationRV.adapter = SavedLocationAdapter(it, savedLocationCallback)
        }

        /*val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (locationManager != null) {
            val location = getLastLocation(locationManager)
            if (location == null) {
                binding.currentLocationContainer.isGone = true
            } else {
                val address =
                    Geocoder(context).getFromLocation(location.latitude, location.longitude, 1)
                        .firstOrNull()
                if (address != null) {
                    binding.currentLocationContainer.isGone = false
                    with(address) {
                        var title = "Undefined"
                        var subTitle = "Undefined"
                        try {
                            title = address.thoroughfare ?: address.subAdminArea
                            subTitle = address.subAdminArea + " " + address.adminArea
                        } catch (e: Exception) {
                        }
                        binding.currentLocationTV.text = title
                        binding.currentLocationContainer.setOnClickListener {
                            val savedLocation =
                                SavedLocation(title, subTitle, false, latitude, longitude)
                            viewModel.saveLocation(savedLocation)
                        }
                    }
                } else {
                    binding.currentLocationContainer.isGone = true
                }
            }
        }*/

        binding.chooseLocationContainer.setOnClickListener {
            findNavController().navigate(SelectLocationBottomSheetDirections.actionSelectLocationBottomSheetToMapsFragment())
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY_DISMISS, true)
    }
}