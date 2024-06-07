package com.riyaz.data.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Task
import com.riyaz.domain.util.LocationManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executor

class LocationManagerImpl(val context: Context) :
    LocationManager {
    private val client: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest = LocationRequest.Builder(Priority.PRIORITY_LOW_POWER, 5000)
        .setMinUpdateDistanceMeters(1000F)
        .build()
    @SuppressLint("MissingPermission")
    override fun listenToLocation(): Flow<Location> {
        if (!hasLocationPermission()) throw LocationPermissionDeniedException()
        return callbackFlow {
            object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    p0.lastLocation?.let {
                        launch {
                            send(it)
                        }
                    }
                }
            }.also { callback ->
                client.requestLocationUpdates(locationRequest, callback, Looper.myLooper())
                client.lastLocation.addOnCompleteListener {
                    it.result?.let {
                        launch {
                            send(it)
                        }
                    }
                }
                awaitClose {
                    // No one listens to flow anymore
                    client.removeLocationUpdates(callback)
                }
            }
        }
    }

    override fun hasLocationPermission(): Boolean {
        return context.checkSelfPermission(
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}