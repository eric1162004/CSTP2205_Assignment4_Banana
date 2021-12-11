package com.example.assignment4.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.delay

/**
 * The is the root screen for the application.
 * It will first show the welcome screen for 2 seconds,
 * then display the AuthenticationController, which determine
 * the authentication state of the user,
 * if the user is authenticated, the user will be directed to the
 * application main screen, else,
 * the user will be directed to the login or signup screen.
 * **/

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun EntryScreen() {
    var showWelcomeScreen by remember { mutableStateOf(true) }

    // When just launch the application, show WelcomeScreen for 2 secs
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