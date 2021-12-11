package com.example.assignment4.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/**
 * This is the place to determine whether the user is authenticated of not.
 * If the user is not authenticated, show the AuthScreen,
 * where user can sign up or register,
 * else, show user the MainScreen of the application.
 * **/

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@Composable
fun AuthenticationController() {
    var isAuthenticated by remember { mutableStateOf(false) }

    // callback for logging in user
    val login: () -> Unit = {
        isAuthenticated = !isAuthenticated
    }

    // callback for logging out user
    val loginOut: () -> Unit = {
        isAuthenticated = !isAuthenticated
    }

    // determine whether the user is authenticated
    if (isAuthenticated)
        MainScreen(loginOut)
    else
        AuthScreen(login)
}