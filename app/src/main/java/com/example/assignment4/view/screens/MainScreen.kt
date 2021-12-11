package com.example.assignment4.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import com.example.assignment4.ui.theme.Routes
import com.example.assignment4.view.app_composables.AppBottomBar
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/**
 * The application main screen.
 * Only show this screen if the user has been authenticated.
 *
 * This screen is the parent screens for:
 * screen-11, screen-9, screen-10 and screen-9
 * **/

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(logOut: () -> Unit) {
    var currentScreen by remember { mutableStateOf(Routes.ME) }
    var previousScreen by remember { mutableStateOf(Routes.ME) }

    // pass this callback to allow child screens to change currentScreen
    var changeScreen: (toScreen: String) -> Unit = {
        previousScreen = currentScreen
        currentScreen = it
    }

    Scaffold(
        bottomBar = {
            AppBottomBar {
                currentScreen = it
            }
        }
    ) {
        Column {
            when (currentScreen) {
                Routes.ALBUM -> AlbumScreen(changeScreen)
                Routes.DIARY -> DiaryScreen(changeScreen)
                Routes.ME -> MeScreen(logOut)
                Routes.MY_LOCATION -> MapScreen(changeScreen)
                Routes.DIARY_WRITING_SCREEN -> DairyWritingScreen(changeScreen, previousScreen)
            }
        }
    }
}