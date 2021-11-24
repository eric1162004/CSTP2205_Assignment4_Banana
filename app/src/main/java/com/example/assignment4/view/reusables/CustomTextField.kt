package com.example.assignment4.view.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.assignment4.R
import com.example.testing.reusables.CircularImageButton

@Composable
fun CustomTextField(
    backgroundColor: Color = Color.White,
    fontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
    fontColor: Color = Color.Black,
    leadingImageResource: Int = R.drawable.banana_default,
    modifier: Modifier = Modifier,
    padding: Dp = 14.dp,
    placeHolderText: String = "placeholder",
    value: String,
    onValueChange: (text: String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .background(backgroundColor)
            .padding(padding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // leading icon
        CircularImageButton(
            imageResource = leadingImageResource,
            imagePadding = 0.dp,
            backgroundColor= backgroundColor,
            modifier = Modifier.height(1.dp).weight(1f)
        ) {}

        Spacer(modifier = Modifier.height(1.dp).weight(.35f))

        // text field
        BasicTextField(
            modifier = Modifier.fillMaxWidth().weight(8f),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else fontColor,
                fontSize = fontSize
            ),
            decorationBox = { innerTextField ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeHolderText,
                            color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.LightGray,
                            fontSize = fontSize
                        )
                    }
                }
                innerTextField()
            }
        )

    }
}
