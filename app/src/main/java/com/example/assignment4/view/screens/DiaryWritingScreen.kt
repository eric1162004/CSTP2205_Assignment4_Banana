package com.example.assignment4.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.assignment4.R
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.view.reusables.AppTopBar
import com.example.testing.reusables.BottomBar
import com.example.testing.reusables.CircularIconButton
import com.example.testing.reusables.CircularImageButton

/**
 * This file contains the DairyWritingScreen.
 * Users write their dairy entries in the screen.
 * **/

@Composable
fun DairyWritingScreen(
    changeScreen :(toScreen: String) -> Unit,
    previousScreen: String
) {
    Scaffold(
        // this BottomBar is special for this screen
        // it contains 3 icons: the pen, image and camara icons
        bottomBar = {
            BottomBar(
                modifier = Modifier.padding(vertical = Dm.marginSmall)
            ) {
                CircularImageButton(
                    imageResource = R.drawable.diary_pen_icon,
                    buttonSize = Dm.buttonDefaultSize + 20.dp
                ) {}
                CircularImageButton(
                    imageResource = R.drawable.diary_image_icon,
                    buttonSize = Dm.buttonDefaultSize + 20.dp
                ) {}
                CircularImageButton(
                    imageResource = R.drawable.diary_camera_icon,
                    buttonSize = Dm.buttonDefaultSize + 20.dp
                ) {}
            }
        },
        topBar = {
            AppTopBar(
                leftIcon = {
                    CircularIconButton(
                        imageResource = R.drawable.arrow_back,
                        buttonSize = Dm.buttonDefaultSize,
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        // go back to whether the user came from
                        changeScreen(previousScreen)
                    }
                },
                title = "diary"
            )
        }
    ) {
        // content for this screen
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .offset(y = -Dm.marginLarge)
                .padding(horizontal = Dm.marginSmall),
        ) {
            // the banana with a pen image
            Image(
                painter = painterResource(id = R.drawable.banana_pencil),
                contentDescription = null,
                modifier = Modifier.size(Dm.buttonDefaultSize * 2)
            )

            // the yellow cloud with caption
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mind_bubble),
                    contentDescription = null,
                    modifier = Modifier
                        .width(Dm.logoSize)
                        .aspectRatio(1.5f)
                )
                Text(
                    text = "what's on your mind?",
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}