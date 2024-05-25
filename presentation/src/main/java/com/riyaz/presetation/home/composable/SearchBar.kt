package com.riyaz.presetation.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchIcon: ImageVector,
    placeholder: String = "",
    onTextChange: ((String) -> Unit)? = null
){
    val searchText = remember {
        mutableStateOf("Search Your City")
    }
    Row(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(listOf(Color.White, Color.Gray)),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            modifier = Modifier.padding(end = 16.dp),
            imageVector = searchIcon,
            contentDescription = "Search",
            tint = Color.Black
        )

        TextField(
            modifier = Modifier.background(Color.Transparent),
            value = searchText.value,
            onValueChange = {
                searchText.value = it
                onTextChange?.invoke(it)
            },
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Color.Black
            ),
            supportingText = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black
                    ))
            },
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )
    }
}