package com.example.testing.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.assignment4.R
import com.example.assignment4.ui.theme.Dm

@Composable
fun BottomBarButton(
    modifier: Modifier = Modifier,
    label: String = "ButtonLabel",
    imageResource: Int = R.drawable.banana_welcome,
    onPressed: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = Dm.marginSmall)
            .clickable {
                onPressed()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.size(Dm.AppBottomBarButtonImageSize)
        )
        Text(
            text = label,
            fontSize = Dm.AppBottomBarButtonLabelSize,
            fontWeight = FontWeight.W700
        )
    }
}