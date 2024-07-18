package com.riyaz.presetation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.riyaz.presetation.home.model.HomePageUIState
import com.riyaz.presetation.shared.composable.GradiantBackground
import com.riyaz.presetation.shared.composable.Progress
import com.riyaz.domain.util.Icon.Companion.getBitmap
import com.riyaz.presetation.util.TestTag

@Preview
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var isLoading by remember {
        mutableStateOf(false)
    }
    val content = remember {
        mutableStateOf<HomePageUIState.Content?>(null)
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiState.collect{
            when(it){
                is HomePageUIState.Content -> {
                    isLoading = false
                    content.value = it
                }
                HomePageUIState.Loading -> isLoading = true
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.loadPage()
    }


    GradiantBackground()
    Column(modifier = modifier){
        Row(modifier = Modifier.fillMaxWidth()) {
            if(isLoading) Progress()
            else{
                Column{
                    Text(
                        modifier = Modifier
                            .padding(top = 73.dp, start = 16.dp)
                            .testTag(TestTag.WEATHER_DESCRIPTION.tag),
                        text = buildAnnotatedString {
                            content.value?.forecast?.current?.let {
                                append(it.temperature.toString())
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 56.sp
                                    )
                                ){
                                    append(content.value?.forecast?.currentUnit?.temperature)
                                }
                            }
                        },
                        style = TextStyle(
                            fontSize = 56.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(imageVector = Icons.Outlined.LocationOn, contentDescription = "Location")
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .testTag(TestTag.LOCATION.tag),
                            text = buildAnnotatedString {
                                content.value?.location.also {
                                    it?.city.let { city -> append(city) }
                                    it?.country.let { country ->
                                        append(", ")
                                        append(country)
                                    }
                                }
                            },
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    com.riyaz.domain.util.Icon.CLOUDY.getBitmap()?.asImageBitmap()?.let {
                        Image(bitmap = it, contentDescription = "Cloud")
                    }
                }
            }
        }
    }
}
