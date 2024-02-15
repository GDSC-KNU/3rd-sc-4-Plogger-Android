package com.example.plogger.ui.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.plogger.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

object Util {

    fun loadImg(context: Context, url: String, view: ImageView) {
        Glide.with(context)
            .load(url)
            .fallback(R.drawable.baseline_delete_24)
            .into(view)

    }
    fun hideKeyboard(activity: Activity) {
        val ims = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ims.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }

    fun setUpMarker(map: GoogleMap, lat: Double, lng: Double, title: String, marker_icon: Int) {
        val positionLatLng = LatLng(lat, lng)
        val markerOptions = MarkerOptions().apply {
            position(positionLatLng)
            title(title)
            icon(BitmapDescriptorFactory.fromResource(marker_icon))
        }

        map.addMarker(markerOptions)?.showInfoWindow()
    }
}