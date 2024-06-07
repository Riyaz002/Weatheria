package com.riyaz.presetation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.riyaz.presetation.search.composable.SearchBar
import com.riyaz.presetation.search.model.UIEvent
import com.riyaz.presetation.shared.composable.GradiantBackground
import com.riyaz.presetation.shared.composable.Title
import com.riyaz.presetation.util.TestTag


@Preview
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: SearchViewModel = viewModel()
    val searchState = viewModel.state.collectAsState()



    //content
    Box(
        modifier.fillMaxSize()
    ) {
        GradiantBackground()
        Column {
            Title(
                modifier = Modifier
                    .padding(16.dp)
                    .testTag(TestTag.WEATHER_DESCRIPTION.tag),
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
                placeholder = "Search City",
                onTextChange = {
                    viewModel.onEvent(UIEvent.OnSearch(it))
                }
            )
        }
    }
}