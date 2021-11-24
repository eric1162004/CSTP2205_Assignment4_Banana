package com.example.assignment4.view.screens

import androidx.compose.runtime.*
import com.example.assignment4.ui.theme.Routes

@Composable
fun AuthScreen(login: () -> Unit) {
    var currentScreenContent by remember { mutableStateOf(Routes.LOGIN) }

    var changeScreenContent: (toScreen: String) -> Unit = {
        currentScreenContent = it
    }

    when (currentScreenContent) {
        Routes.LOGIN -> LoginScreen(login) {
            changeScreenContent(it)
        }
        Routes.SIGNUP -> SignUpScreen {
            changeScreenContent(it)
        }
    }
}