package com.example.assignment4.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.assignment4.R
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.ui.theme.Routes
import com.example.assignment4.view.reusables.AppButton
import com.example.assignment4.view.reusables.CustomTextField
import com.example.testing.reusables.CircularIconButton
import com.example.testing.reusables.CircularImageButton

@Composable
fun SignUpScreen(
    onCreateOnePressed: (toScreen: String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // Log in curve text
        Image(
            painter = painterResource(id = R.drawable.signup_curve_text),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .scale(1.05f)
                .weight(.8f)
                .offset(y = 10.dp)
        )

        // Screen Content
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(1.4f)
                .background(MaterialTheme.colors.primary)
                .padding(horizontal = Dm.marginLarge)
        ) {
            SignUpScreenLogic()

            CircularIconButton(
                imageResource = R.drawable.arrow_back,
                iconPadding = 5.dp,
                modifier = Modifier
                    .offset(x = 140.dp, y = -600.dp)
                    .zIndex(1f)
            ) {
                onCreateOnePressed(Routes.LOGIN)
            }
        }
    }
}

@Composable
private fun SignUpScreenLogic(
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AppIconRow()

    InputFields(
        name, { name = it },
        email, { email = it },
        password, { password = it },
        confirmPassword, { confirmPassword = it },
    )

    SignUpButtonRow()
}

@Composable
private fun AppIconRow() {
    CircularImageButton(
        imageResource = R.drawable.banana_welcome,
        buttonSize = 75.dp,
        imagePadding = 5.dp,
        modifier = Modifier.offset(y = (-40).dp)
    ) {}
}

@Composable
private fun InputFields(
    name: String,
    onNameChange: (value: String) -> Unit,
    email: String,
    onEmailChange: (value: String) -> Unit,
    password: String,
    onPasswordChange: (value: String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (value: String) -> Unit,
) {
    // name
    CustomTextField(
        value = name,
        onValueChange = { onNameChange(it) },
        placeHolderText = "name",
        modifier = Modifier.padding(bottom = Dm.inputFieldPadding)
    )

    // email
    CustomTextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        placeHolderText = "email",
        modifier = Modifier.padding(vertical = Dm.inputFieldPadding)
    )

    // password
    CustomTextField(
        value = password,
        onValueChange = { onPasswordChange(it) },
        placeHolderText = "password",
        modifier = Modifier.padding(vertical = Dm.inputFieldPadding)
    )

    // confirm password
    CustomTextField(
        value = confirmPassword,
        onValueChange = { onConfirmPasswordChange(it) },
        placeHolderText = "confirm password",
        modifier = Modifier.padding(vertical = Dm.marginMedium)
    )
}

@Composable
private fun SignUpButtonRow(
    onLoginButtonPress: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dm.marginSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        AppButton(label = "sign up") {
            onLoginButtonPress()
        }
    }
}

