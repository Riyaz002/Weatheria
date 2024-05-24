package com.riyaz.presetation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riyaz.domain.usecase.GetForecastUseCase
import com.riyaz.presetation.home.composable.SearchBar
import com.riyaz.presetation.shared.composable.Title
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@Preview
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    getForecastUseCase: GetForecastUseCase? = null
) {
    var viewModel: HomeViewModel
    //for blurry background

    LaunchedEffect(key1 = true) {
        viewModel = HomeViewModel()
    }

    Box(
        modifier
            .fillMaxSize()
            .blur(1000.dp)) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(listOf(Color.Blue, Color.Cyan, Color.White))
            ))
    }

    //content
    Box(
        modifier.fillMaxSize()
    ) {
        val scope = rememberCoroutineScope()
        val text = remember {
            mutableStateOf("")
        }
        LaunchedEffect(key1 = true) {
            scope.launch {
                if(getForecastUseCase==null) return@launch
                val forecast = getForecastUseCase(
                    com.riyaz.domain.model.LocationCoordinate(77.102493, 28.704060),
                    hashMapOf<String, String>().also { map ->
                        map["hourly"] = "temperature_2m"
                        "current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,weather_code,wind_speed_10m"
                            .split("&")
                            .forEach {
                                map[it.split("=").first()] = it.split("=").last()
                            }
                    }
                )
                text.value = forecast?.current?.temperature.toString() + forecast?.current?.apparentTemperature
            }
        }
        Column {
            Title(
                modifier = Modifier.padding(16.dp),
                text = "Around your city",
                fontSize = 32.sp,
                style = TextStyle(
                    color = Color.hsl(202F, 0.65F, 0.48F)
                )
            )

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                searchIcon = Icons.Outlined.Search,
                placeholder = "Search City"
            )
        }
    }
}