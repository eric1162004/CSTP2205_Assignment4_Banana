package com.example.assignment4.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@Composable
fun AuthenticationController() {
    var isAuthenticated by remember { mutableStateOf(false) }

    val login: () -> Unit = {
        isAuthenticated = !isAuthenticated
    }

    val loginOut: () -> Unit = {
        isAuthenticated = !isAuthenticated
    }

    if (isAuthenticated)
        MainScreen(loginOut)
    else
        AuthScreen(login)
}