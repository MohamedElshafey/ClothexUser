package com.clothex.user.home.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.local.room.entity.SavedLocation
import com.clothex.user.R
import com.clothex.user.databinding.FragmentMapsBinding
import com.clothex.user.dialog.MessageAlertDialog.showAlertDialog
import com.clothex.user.utils.hasPermission
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject


@SuppressLint("MissingPermission")
class MapsFragment : Fragment() {

    private var activityResultLauncher: ActivityResultLauncher<Array<String>>
    private var googleMap: GoogleMap? = null
    lateinit var binding: FragmentMapsBinding
    private val viewModel: MapsViewModel by inject()

    init {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                var allAreGranted = true
                for (b in result.values) {
                    allAreGranted = allAreGranted && b
                }

                if (allAreGranted) {
                    googleMap?.isMyLocationEnabled = true
                }
            }
    }

    private var marker: Marker? = null

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        if (hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION) && hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            googleMap.isMyLocationEnabled = true
            zoomToCurrentLocation()
            googleMap.setOnMapClickListener {
                marker?.remove()
                marker = googleMap.addMarker(MarkerOptions().position(it))
            }
        } else {
            activityResultLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        binding.bottomButton.setOnClickListener {

            marker?.position?.let { latLng ->
                var title = "Undefined"
                var subTitle = "Undefined"
                try {
                    val address =
                        Geocoder(context).getFromLocation(latLng.latitude, latLng.longitude, 1)
                            .first()
                    title = address.thoroughfare ?: address.subAdminArea
                    subTitle = address.subAdminArea + " " + address.adminArea
                } catch (e: Exception) {
                }
                viewModel.saveLocation(
                    SavedLocation(
                        title,
                        subTitle,
                        false,
                        latLng.latitude,
                        latLng.longitude
                    )
                ) { saved ->
                    activity?.runOnUiThread {
                        if (saved)
                            findNavController().navigateUp()
                        else
                            showEditLocationAlert()
                    }


                }

            }
        }

        binding.myLocationIV.setOnClickListener {
            zoomToCurrentLocation()
        }
    }

    private fun showEditLocationAlert() {
        showAlertDialog(
            requireContext(),
            title = "Error saving location!",
            description = "You can't add more than 4 locations, delete one of them before adding new one.",
            positiveButtonText = "Edit locations",
            negativeButtonText = context?.getString(R.string.cancel),
            positiveOnClickListener = {
                findNavController().navigate(MapsFragmentDirections.actionMapsFragmentToEditLocationFragment())
            },
            iconRes = R.drawable.ic_wrong_small
        )
    }

    private fun zoomToCurrentLocation() {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        val criteria = Criteria()

        val location: Location? = locationManager!!.getLastKnownLocation(
            locationManager.getBestProvider(
                criteria,
                false
            )!!
        )
        if (location != null) {
            googleMap?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        location.latitude,
                        location.longitude
                    ), 13f
                )
            )
            val cameraPosition = CameraPosition.Builder()
                .target(
                    LatLng(
                        location.latitude,
                        location.longitude
                    )
                ) // Sets the center of the map to location user
                .zoom(17f) // Sets the zoom
                .bearing(90f) // Sets the orientation of the camera to east
                .tilt(40f) // Sets the tilt of the camera to 30 degrees
                .build() // Creates a CameraPosition from the builder
            googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }
}