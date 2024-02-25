package com.example.plogger.ui.jogging

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.plogger.R
import com.example.plogger.databinding.AccessDialogBinding
import com.example.plogger.databinding.ActivityJoggingBinding
import com.example.plogger.databinding.StopJoggingDialogBinding
import com.example.plogger.model.MarkerInfo
import com.example.plogger.ui.util.RequestPermissionUtil
import com.example.plogger.ui.util.Util.setUpMarker
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.StyleSpan
import okio.IOException
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class JoggingActivity : AppCompatActivity(),
    OnMapReadyCallback {
    lateinit var binding: ActivityJoggingBinding
    private lateinit var map: GoogleMap
    val REQUEST_IMAGE_CAPTURE = 1
    var is_routing = true
    var picked_up_trash_num = 0
    var route = mutableListOf<MarkerInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoggingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi(savedInstanceState)
    }

    private fun setUi(savedInstanceState: Bundle?) {
        val dialogBinding = StopJoggingDialogBinding.inflate(layoutInflater)
        val dlg = Dialog(this)

        dialogBinding.apply {
            writeDiaryBtn.setOnClickListener {
                startActivity(Intent(baseContext, WriteDiaryActivity::class.java))
                finish()
            }
            cancelBtn.setOnClickListener {
                dlg.dismiss()
            }
        }
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(dialogBinding.root)

        binding.apply {
            mapView.onCreate(savedInstanceState)
            mapView.getMapAsync(this@JoggingActivity)

            timer.text = "00:00:00"
            kilometer.text = "0.00\nkm"
            velocity.text = "0.00\nkm/h"
            trashNum.text = "0\nPicked Up"

            selectBtn.setOnClickListener {
                if (route.size != 0) {
                    joggingOnBox.visibility = View.VISIBLE
                    cameraBtn.visibility = View.VISIBLE
                    courseBox.visibility = View.INVISIBLE

                    setUpMarker(map, route[route.size-1].latitude, route[route.size-1].longitude, R.drawable.map_marker_icon)
                    is_routing = false
                } else {
                    Toast.makeText(baseContext, "경로를 지정하세요!", Toast.LENGTH_LONG).show()
                }
            }
            cancelBtn.setOnClickListener {
                finish()
            }

            backBtn.setOnClickListener {
                finish()
            }
            stopBtn.setOnClickListener {
                dlg.show()
            }
            cameraBtn.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

                picked_up_trash_num += 1
                trashNum.text = "$picked_up_trash_num\nPicked Up"
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            isMyLocationEnabled = true
            animateCamera(CameraUpdateFactory.zoomTo(15f))

            setOnMapClickListener {
                if (is_routing) {
                    if (route.size != 0) {
                        map.addPolyline(
                            PolylineOptions()
                                .add(
                                    LatLng(
                                        route[route.size - 1].latitude,
                                        route[route.size - 1].longitude
                                    )
                                )
                                .add(LatLng(it.latitude, it.longitude))
                                .addSpan(StyleSpan(Color.BLUE))
                        )
                    }
                    route.add(MarkerInfo(it.latitude, it.longitude))
                }
            }
        }

        moveMyLocation()
    }

    @SuppressLint("MissingPermission")
    private fun moveMyLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { success: Location? ->
                success?.let { location ->
                    map.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                location.latitude,
                                location.longitude
                            ), 15F
                        )
                    )
                }
            }
            .addOnFailureListener { fail ->
                Log.d("myLocationError", fail.localizedMessage)
            }
    }

    override fun onStart() {
        super.onStart()
        RequestPermissionUtil(baseContext).requestLocation()
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
}