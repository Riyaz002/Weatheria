package com.riyaz.data.location

class LocationPermissionDeniedException: Exception() {
    override val message: String
        get() = "Location permission is not granted"
}