package com.riyaz.weatheria

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.riyaz.weatheria.data.database.WeatheriaDao
import com.riyaz.weatheria.data.remote.OpenMateoApi
import com.riyaz.weatheria.data.remote.model.ForecastDTO
import com.riyaz.weatheria.data.repository.WeatheriaRepositoryImpl
import com.riyaz.weatheria.domain.repository.WeatherRepository
import com.riyaz.weatheria.ui.theme.WeatheriaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var openMeteoApi: OpenMateoApi

    @OptIn(ExperimentalPermissionsApi::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val notificationPermissionState =
                rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)

            val requestPermissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    // Permission granted
                } else {
                    // Handle permission denial
                }
            }

            LaunchedEffect(notificationPermissionState) {
                if (!notificationPermissionState.hasPermission && notificationPermissionState.shouldShowRationale) {
                    // Show rationale if needed
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }

            WeatheriaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(key1 = true) {
                        lifecycleScope.launch {
                            WeatheriaRepositoryImpl(
                                Dispatchers.IO,
                                openMeteoApi,
                                object : WeatheriaDao {}
                            ).getForecast(
                                77.102493, 28.704060,
                                hashMapOf<String, String>().also { map ->
                                    map["hourly"] = "temperature_2m"
                                    "current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,weather_code,wind_speed_10m"
                                        .split("&")
                                        .forEach {
                                            map[it.split("=").first()] = it.split("=").last()
                                        }
                                }
                            )
                        }
                    }
                }
                Greeting(name = "HELOOOW")
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        WeatheriaTheme {
            Greeting("Android")
        }
    }
}