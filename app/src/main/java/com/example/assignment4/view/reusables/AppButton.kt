package com.example.assignment4.view.reusables

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.ui.theme.FontStyle

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    labelFontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
    width: Dp = Dm.longButtonDefaultWidth,
    height: Dp = Dm.longButtonDefaultHeight,
    label: String = "Button",
    buttonColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.size(
            width = width,
            height = height
        ),
        onClick = { onClick() },
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
        )
    }
}