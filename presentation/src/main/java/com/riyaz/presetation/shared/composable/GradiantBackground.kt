package com.riyaz.presetation.shared.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradiantBackground(modifier: Modifier = Modifier.fillMaxSize()) {
    Box(modifier = modifier.blur(1000.dp).background(brush = Brush.linearGradient(listOf(Color.Black, Color.DarkGray, Color.DarkGray ))))
}