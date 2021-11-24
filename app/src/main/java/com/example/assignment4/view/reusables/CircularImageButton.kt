package com.example.testing.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.assignment4.ui.theme.Dm

@Composable
fun CircularImageButton(
    imageResource: Int,
    buttonSize: Dp = Dm.buttonDefaultSize,
    backgroundColor: Color = Color.White,
    imagePadding: Dp = Dm.buttonPadding,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "",
        modifier = modifier
            .size(buttonSize)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(imagePadding)
            .clickable {
                onClick()
            }
    )
}