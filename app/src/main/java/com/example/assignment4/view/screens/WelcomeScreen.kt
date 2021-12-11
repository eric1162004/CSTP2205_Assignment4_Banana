package com.example.assignment4.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.assignment4.R
import com.example.assignment4.view.reusables.AppLogo

/**
 * The transitional screen when the application is launched.
 * **/
@Composable
fun WelcomeScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        AppLogo(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.welcome_curve_text),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .scale(1.05f)
                .weight(.8f)
        )

        Spacer(
            modifier = Modifier
                .width(1000.dp)
                .weight(.30f)
                .background(MaterialTheme.colors.primary)
        )
    }
}