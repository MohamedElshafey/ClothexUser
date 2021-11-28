package com.clothex.user.home.map

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.clothex.user.R
import com.clothex.user.utils.hasPermission
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

@SuppressLint("MissingPermission")
class MapsFragment : Fragment() {

    private var activityResultLauncher: ActivityResultLauncher<Array<String>>
    private var googleMap: GoogleMap? = null

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

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap

        if (hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION) && hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            googleMap.isMyLocationEnabled = true
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
    ): View? = inflater.inflate(R.layout.fragment_maps, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }
}