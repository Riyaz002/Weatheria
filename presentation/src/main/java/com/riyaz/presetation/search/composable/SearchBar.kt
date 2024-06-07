package com.riyaz.presetation.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchIcon: ImageVector = Icons.Outlined.Search,
    placeholder: String = "",
    onTextChange: ((String) -> Unit)? = null
){
    val searchText = remember {
        mutableStateOf("")
    }
    TextField(
            leadingIcon = {
                Icon(
                    imageVector = searchIcon,
                    contentDescription = "Search",
                )
            },
            modifier = modifier.background(Color.Transparent),
            value = searchText.value,
            shape = RoundedCornerShape(20.dp),
            onValueChange = {
                searchText.value = it
                onTextChange?.invoke(it)
            },
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Color.hsl(202f, 0.71f, 0.31f)
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = Color.Blue,
                unfocusedLeadingIconColor = Color.hsl(202f, 0.71f, 0.31f)
            )
    )
}