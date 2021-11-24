package com.example.assignment4.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.delay

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun EntryScreen() {
    var showWelcomeScreen by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        delay(2000L)
        showWelcomeScreen = !showWelcomeScreen
    }

    if (showWelcomeScreen) {
        WelcomeScreen()
    } else {
        AuthenticationController()
    }
}