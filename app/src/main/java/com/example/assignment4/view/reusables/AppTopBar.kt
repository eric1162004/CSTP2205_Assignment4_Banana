package com.example.assignment4.view.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.assignment4.ui.theme.Dm

@Composable
fun AppTopBar(
    leftIcon: @Composable () -> Unit = {},
    title: String = "App Title",
    backgroundColor: Color = Color.White,
    rightIcon: @Composable () -> Unit = {},
    height: Dp = Dm.appTopBarHeight,
    paddingVertical: Dp = 0.dp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = Dm.marginLarge)
            .padding(vertical = paddingVertical)
            .height(height),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leftIcon != {}) {
            Box(modifier = Modifier.weight(1f)) {
                leftIcon()
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(5f)
        )

        if (rightIcon != {}) {
            Box(modifier = Modifier.weight(1f)) {
                rightIcon()
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }

}