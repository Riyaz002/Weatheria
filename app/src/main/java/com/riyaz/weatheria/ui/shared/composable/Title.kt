package com.riyaz.weatheria.ui.shared.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String = "",
    style: TextStyle = TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.ExtraBold
    ),
    fontSize: TextUnit = 22.sp
) {
    Text(
        text = text,
        modifier = modifier,
        style = style.copy(fontSize = fontSize)
    )
}