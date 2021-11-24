package com.example.assignment4.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.assignment4.R
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.ui.theme.Routes
import com.example.assignment4.view.reusables.*
import com.example.testing.reusables.CircularImageButton

@Composable
fun LoginScreen(
    login: () -> Unit,
    onCreateOnePressed: (toScreen: String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // Log in curve text
        Image(
            painter = painterResource(id = R.drawable.login_curve_text),
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
            LoginScreenLogic(
                login,
                onCreateOnePressed
            )
        }
    }
}

@Composable
private fun LoginScreenLogic(
    login: () -> Unit,
    onCreateOnePressed: (toScreen: String) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRememberMeChecked by remember { mutableStateOf(false) }

    AppIconRow()

    InputFields(
        name, { name = it },
        password, { password = it }
    )

    RememberMeRow(
        isRememberMeChecked = isRememberMeChecked,
        onCheckBoxChange = { isRememberMeChecked = it }
    ) {
        // forget password link pressed
    }

    LoginButtonRow() {
        // sign in user
        login()
    }

    OrSignInWithText()
    SocialIconRow()

    DontHaveAnAccountText() {
        // go to sign up
        onCreateOnePressed(Routes.SIGNUP)
    }
}

@Composable
private fun AppIconRow() {
    CircularImageButton(
        imageResource = R.drawable.banana_welcome,
        buttonSize = 75.dp,
        imagePadding = 5.dp,
        modifier = Modifier.offset(y = -40.dp)
    ) {}
}

@Composable
private fun InputFields(
    name: String,
    onNameChange: (value: String) -> Unit,
    password: String,
    onPasswordChange: (value: String) -> Unit,
) {
    CustomTextField(
        value = name,
        onValueChange = { onNameChange(it) },
        placeHolderText = "name",
        modifier = Modifier.padding(bottom = Dm.inputFieldPadding)
    )

    CustomTextField(
        value = password,
        onValueChange = { onPasswordChange(it) },
        placeHolderText = "password",
        modifier = Modifier.padding(vertical = Dm.inputFieldPadding)
    )
}

@Composable
private fun RememberMeRow(
    isRememberMeChecked: Boolean,
    onCheckBoxChange: (checked: Boolean) -> Unit,
    onForgetPasswordPressed: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dm.marginSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CheckboxField(
            checked = isRememberMeChecked,
            label = "remember me"
        ) {
            onCheckBoxChange(it)
        }

        ClickableLink(
            label = "Forget password?"
        ) {
            onForgetPasswordPressed()
        }
    }
}

@Composable
private fun LoginButtonRow(
    onLoginButtonPress: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dm.marginMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        AppButton(label = "log in") {
            onLoginButtonPress()
        }
    }
}

@Composable
private fun OrSignInWithText() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dm.marginSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(Color.White)
        )

        Text(
            text = "or sign in with",
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(horizontal = Dm.marginMedium)
                .weight(1.5f)
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(Color.White)
        )
    }
}

@Composable
private fun SocialIconRow(
    onFacebookIconPressed: () -> Unit = {},
    onGoogleIconPressed: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dm.marginSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularImageButton(
            imageResource = R.drawable.facebook_icon,
            imagePadding = 0.dp,
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier.padding(horizontal = Dm.marginSmall)
        ) {
            onFacebookIconPressed()
        }

        CircularImageButton(
            imageResource = R.drawable.google_icon,
            imagePadding = 0.dp,
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier.padding(horizontal = Dm.marginSmall)
        ) {
            onGoogleIconPressed()
        }
    }
}

@Composable
private fun DontHaveAnAccountText(
    onCreateOnePressed: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dm.marginSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Don't have an account? ",
            style = MaterialTheme.typography.body2
        )

        ClickableLink(
            label = "Create one",
            color = Color.White,
            style = MaterialTheme.typography.body2
        ) {
            onCreateOnePressed()
        }

        Text(
            text = ".",
            style = MaterialTheme.typography.body2
        )
    }
}
