package com.example.assignment4.view.reusables

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun ClickableLink(
    label: String = "link",
    color: Color = Color.Gray,
    style: TextStyle = MaterialTheme.typography.caption,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Text(
        text = label,
        modifier = modifier.clickable { onClick() },
        style = style,
        color = color,
    )
}