package com.clothex.user.home.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.local.room.entity.SavedLocation
import com.clothex.user.R
import com.clothex.user.databinding.FragmentMapsBinding
import com.clothex.user.dialog.MessageAlertDialog.showAlertDialog
import com.clothex.user.utils.getLastLocation
import com.clothex.user.utils.hasPermission
import com.clothex.user.utils.setRotationByLocale
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
                    zoomToCurrentLocation()
                }
            }
    }

    private var marker: Marker? = null

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        if (hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION) && hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            googleMap.isMyLocationEnabled = true
            zoomToCurrentLocation()
        } else {
            activityResultLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }
        googleMap.setOnMapClickListener {
            marker?.remove()
            marker = googleMap.addMarker(MarkerOptions().position(it))
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
        viewModel.logScreen(MapsFragment::class.java.simpleName)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        binding.bottomButton.setOnClickListener {
            val position = marker?.position
            if (position == null) {
                Toast.makeText(
                    context,
                    getString(R.string.no_location_selected_to_save),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                position.let { latLng ->
                    val title: String
                    val subTitle: String
                    try {
                        val address =
                            Geocoder(context).getFromLocation(latLng.latitude, latLng.longitude, 1)
                                .first()
                        title = address.thoroughfare ?: address.subAdminArea
                        subTitle = address.subAdminArea + " " + address.adminArea
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            getString(R.string.network_error_message),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
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
        }

        binding.myLocationIV.setOnClickListener {
            zoomToCurrentLocation()
        }

        viewModel.searchResultLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val address = it[0]
                val latLng = LatLng(address.latitude, address.longitude)
                marker?.remove()
                marker = googleMap?.addMarker(MarkerOptions().position(latLng))
                googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_result_for_places),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.backIV.setRotationByLocale()
        binding.backIV.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(key: String?): Boolean {
                key?.let { viewModel.geocode(key, Geocoder(context)) }
                return true
            }

            override fun onQueryTextChange(key: String?): Boolean {
                return true
            }
        })
    }

    private fun showEditLocationAlert() {
        showAlertDialog(
            requireContext(),
            title = getString(R.string.error_saving_location_title),
            description = getString(R.string.location_count_restriction_message),
            positiveButtonText = getString(R.string.edit_locations),
            negativeButtonText = context?.getString(R.string.cancel),
            positiveOnClickListener = {
                findNavController().navigate(MapsFragmentDirections.actionMapsFragmentToEditLocationFragment())
            },
            iconRes = R.drawable.ic_wrong_small
        )
    }

    private fun zoomToCurrentLocation() {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager? ?: return
        val location = getLastLocation(locationManager)
        if (location != null) {
            val latLng = LatLng(location.latitude, location.longitude)
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
            val cameraPosition = CameraPosition.Builder().target(latLng).zoom(17f).build()
            googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            marker?.remove()
            marker = googleMap?.addMarker(MarkerOptions().position(latLng))
        }
    }
}