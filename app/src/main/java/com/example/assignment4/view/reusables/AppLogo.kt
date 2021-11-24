package com.example.assignment4.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment4.R
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.ui.theme.FontStyle

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.banana_welcome),
            contentDescription = null,
            modifier = Modifier.size(Dm.logoSize)
        )
        Text(
            text = "banana",
            fontSize = FontStyle.LogoFontSize,
            modifier = Modifier.offset(y = (-20).dp)
        )
    }
}