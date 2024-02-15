package com.example.plogger.ui.jogging

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.plogger.R
import com.example.plogger.databinding.FragmentJoggingBinding
import com.example.plogger.ui.util.RequestPermissionUtil
import com.example.plogger.ui.util.Util.setUpMarker
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class JoggingFragment : Fragment(),
    OnMyLocationButtonClickListener,
    OnMyLocationClickListener,
    OnMapReadyCallback {
    lateinit var binding: FragmentJoggingBinding
    private lateinit var map: GoogleMap
    private var currentMarker: Marker? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentJoggingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi(savedInstanceState)
    }

    private fun setUi(savedInstanceState: Bundle?) {
        binding.apply {
            mapView.onCreate(savedInstanceState)
            mapView.getMapAsync(this@JoggingFragment)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireActivity())
        map = googleMap

        map.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            isMyLocationEnabled = true
            animateCamera(CameraUpdateFactory.zoomTo(15f))
            setOnMyLocationButtonClickListener(this@JoggingFragment)
            setOnMyLocationClickListener(this@JoggingFragment)
        }

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { success: Location? ->
                success?.let { location ->
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 15F))
                }
            }
            .addOnFailureListener { fail ->
                Log.d("myLocationError", fail.localizedMessage)
            }

        setUpMarker(map, 37.554891, 126.970814, "처음 위치", R.drawable.map_marker_icon)
    }

    override fun onStart() {
        super.onStart()
        RequestPermissionUtil(this.requireContext()).requestLocation()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this.requireContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this.requireContext(), "Current location:$location", Toast.LENGTH_LONG).show()
    }
}