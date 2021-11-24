package com.example.assignment4.view.reusables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.example.assignment4.ui.theme.OverlayScreenBackgroundColor

@Composable
fun OverlayScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .zIndex(999f),
        color = OverlayScreenBackgroundColor
    ) {
        content()
    }
}