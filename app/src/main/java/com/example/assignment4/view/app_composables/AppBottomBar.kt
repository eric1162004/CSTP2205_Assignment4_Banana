package com.example.assignment4.view.app_composables

import androidx.compose.runtime.Composable
import com.example.assignment4.R
import com.example.assignment4.ui.theme.Routes
import com.example.testing.reusables.BottomBar
import com.example.testing.reusables.BottomBarButton

@Composable
fun AppBottomBar(
    onBottomBarButtonPressed: (id: String) -> Unit = {}
) {
    BottomBar {
        BottomBarButton(
            label = "album",
            imageResource = R.drawable.banana_gallery
        ){
            onBottomBarButtonPressed(Routes.ALBUM)
        }
        BottomBarButton(
            label = "my locations",
            imageResource = R.drawable.banana_pin
        ){
            onBottomBarButtonPressed(Routes.MY_LOCATION)
        }
        BottomBarButton(
            label = "diary",
            imageResource = R.drawable.banana_pencil
        ){
            onBottomBarButtonPressed(Routes.DIARY)
        }
        BottomBarButton(
            label = "me",
            imageResource = R.drawable.banana_default
        ){
            onBottomBarButtonPressed(Routes.ME)
        }
    }
}