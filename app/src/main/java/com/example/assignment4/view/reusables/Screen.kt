package com.example.assignment4.view.app_composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.assignment4.ui.theme.Dm

@ExperimentalFoundationApi
@Composable
fun Screen(
    appTopBar: @Composable () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primary,
    modifier: Modifier = Modifier,
    appContent: @Composable () -> Unit = {},
) {
    Column(
        modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(bottom = Dm.appBarHeight)
    ) {
        appTopBar()
        appContent()
    }
}