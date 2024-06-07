package com.riyaz.presetation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.riyaz.presetation.home.model.HomePageUIState
import com.riyaz.presetation.shared.composable.GradiantBackground
import com.riyaz.presetation.shared.composable.Progress

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var uiState: HomePageUIState by remember {
        mutableStateOf(HomePageUIState.Loading)
    }
    val forecast = rememberUpdatedState(newValue = when(uiState){
        is HomePageUIState.Loading -> null
        is HomePageUIState.Loaded -> {
            (uiState as HomePageUIState.Loaded).forecast
        }
    })

    LaunchedEffect(key1 = true) {
        viewModel.uiState.collect{
            uiState = it
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.loadPage()
    }


    GradiantBackground()
    Column(modifier = modifier) {
        if(uiState is HomePageUIState.Loading) Progress()
        else{
            Text(
                modifier = Modifier.padding(top = 73.dp, start = 16.dp),
                text = buildAnnotatedString {
                    forecast.value?.current?.let {
                        append(it.temperature.toString())
                        withStyle(
                            style = SpanStyle(
                                fontSize = 56.sp
                            )
                        ){
                            append(forecast.value?.currentUnit?.temperature)
                        }
                    }
                },
                style = TextStyle(
                    fontSize = 56.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
