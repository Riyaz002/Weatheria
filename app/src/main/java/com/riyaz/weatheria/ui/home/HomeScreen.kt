package com.riyaz.weatheria.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp
import com.riyaz.weatheria.data.database.WeatheriaDao
import com.riyaz.weatheria.data.remote.WeatherApiService
import com.riyaz.weatheria.data.repository.WeatheriaRepositoryImpl
import com.riyaz.weatheria.ui.util.TestTag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(modifier: Modifier = Modifier, apiService: WeatherApiService) {

    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val scope = rememberCoroutineScope()
        val text = remember {
            mutableStateOf("")
        }
        LaunchedEffect(key1 = true) {
            scope.launch {
                val forecast = WeatheriaRepositoryImpl(
                    Dispatchers.IO,
                    apiService,
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
                text.value = forecast?.current?.weatherDescription.toString()
            }
        }
        Text(
            modifier = Modifier.testTag(TestTag.WEATHER_DESCRIPTION.tag),
            text = text.value,
            fontSize = 28.sp,
        )
    }
}