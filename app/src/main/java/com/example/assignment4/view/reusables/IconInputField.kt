package com.example.assignment4.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment4.R
import com.example.assignment4.ui.theme.Dm

@Composable
fun IconInputField(
    modifier: Modifier = Modifier,
    placeHolderText: String = "placeholder",
    imageResource: Int = R.drawable.banana_default,
    fontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    value: String,
    onValueChange: (newValue: String) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small),
            textStyle = TextStyle(
                fontSize = fontSize
            ),
            value = value,
            leadingIcon = {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = "",
                    modifier = Modifier
                        .size(Dm.IconInputFieldLeadingIconSize)
                        .aspectRatio(1f)
                        .background(backgroundColor)
                )
            },
            onValueChange = { onValueChange(it) },
            placeholder = { Text(placeHolderText) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                textColor = textColor,
                placeholderColor = Color.LightGray,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            )
        )
    }
}