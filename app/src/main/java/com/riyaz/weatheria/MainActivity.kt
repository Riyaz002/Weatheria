package com.riyaz.weatheria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.riyaz.presetation.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val notificationPermissionState = rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)

            val locationPermissionState = rememberPermissionState(android.Manifest.permission.ACCESS_COARSE_LOCATION)

            val showHome = rememberUpdatedState(newValue = locationPermissionState.hasPermission)

            val requestPermissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { isGranted ->

            }

            LaunchedEffect(notificationPermissionState, locationPermissionState) {

                if (!notificationPermissionState.hasPermission && notificationPermissionState.shouldShowRationale) {
                    // Show rationale if needed
                } else {
                    requestPermissionLauncher.launch(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.POST_NOTIFICATIONS))
                }
            }

            com.riyaz.presetation.theme.WeatheriaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if(showHome.value) HomeScreen()
                }
            }
        }
    }
}