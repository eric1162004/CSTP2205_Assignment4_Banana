package com.example.assignment4.view.reusables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxField(
    checked: Boolean,
    label: String = "label",
    labelTextColor: Color = Color.Black,
    spacer: Dp = 10.dp,
    modifier: Modifier = Modifier,
    onCheckedChange: (checked: Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = Color.White,
            modifier = Modifier.clip(MaterialTheme.shapes.small)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { onCheckedChange(it) },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.Black,
                )
            )
        }

        Spacer(modifier = Modifier.width(spacer))

        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            color = labelTextColor
        )
    }
}