package com.example.plogger.ui.util

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

class RequestPermissionUtil(private val context: Context) {

    private val REQUEST_LOCATION = 1

    @RequiresApi(Build.VERSION_CODES.Q)
    private val permissionLocationUpApi = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    @TargetApi(Build.VERSION_CODES.P)
    private val permissionLocationDownApi = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    fun requestLocation() {
        if (Build.VERSION.SDK_INT >= 29) {
            if (ActivityCompat.checkSelfPermission(
                context,
                permissionLocationUpApi[0]
            ) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
                    context,
                    permissionLocationUpApi[1]
                ) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    permissionLocationUpApi,
                    REQUEST_LOCATION
                )
            }
        } else {
            if (ActivityCompat.checkSelfPermission(
                context,
                permissionLocationDownApi[0]
            ) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
                    context,
                    permissionLocationDownApi[1]
                ) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    permissionLocationDownApi,
                    REQUEST_LOCATION
                )
            }
        }
    }
}