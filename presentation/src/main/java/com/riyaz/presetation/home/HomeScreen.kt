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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.riyaz.domain.usecase.GetForecastUseCase
import com.riyaz.presetation.home.composable.SearchBar
import com.riyaz.presetation.home.model.UIEvent
import com.riyaz.presetation.shared.composable.Title
import com.riyaz.presetation.util.TestTag


@Preview
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    getForecastUseCase: GetForecastUseCase? = null,
    viewModel: HomeViewModel = viewModel()
) {
    val homeState = viewModel.state.collectAsState()

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
        Column {
            Title(
                modifier = Modifier.padding(16.dp).testTag(TestTag.WEATHER_DESCRIPTION.tag),
                text = homeState.value.forecast?.current?.temperature.toString() + homeState.value.forecast?.current?.apparentTemperature,
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
                placeholder = "Search City",
                onTextChange = {
                    viewModel.onEvent(UIEvent.OnSearch(it))
                }
            )
        }
    }
}