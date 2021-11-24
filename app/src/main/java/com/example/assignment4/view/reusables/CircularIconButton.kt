package com.example.testing.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.assignment4.ui.theme.Dm

@Composable
fun CircularIconButton(
    imageResource: Int,
    buttonSize: Dp = Dm.buttonDefaultSize,
    iconTint: Color = Color.Black,
    iconPadding: Dp = 5.dp,
    backgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    IconButton(
        onClick = { onClick() },
        modifier = modifier.size(buttonSize)
    ) {
        Icon(
            painter = painterResource(id = imageResource),
            contentDescription = "",
            tint =  iconTint,
            modifier = Modifier
                .size(buttonSize)
                .clip(CircleShape)
                .background(backgroundColor)
                .padding(iconPadding)
        )
    }
}