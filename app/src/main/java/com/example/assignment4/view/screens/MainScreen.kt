package com.example.assignment4.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import com.example.assignment4.ui.theme.Routes
import com.example.assignment4.view.app_composables.AppBottomBar
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(logOut: () -> Unit) {
    var currentScreen by remember { mutableStateOf(Routes.ME) }

    // pass this callback to allow child screens to change currentScreen
    var changeScreen: (toScreen: String) -> Unit = {
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
                Routes.ALBUM -> AlbumScreen()
                Routes.DIARY -> DiaryScreen()
                Routes.ME -> MeScreen(logOut)
                Routes.MY_LOCATION -> MapScreen(changeScreen)
            }
        }
    }
}