package com.example.assignment4.view.reusables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider(
    color: Color = Color.White,
    height: Dp = 0.5.dp
) {
    Divider(
        color = color,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    )
}