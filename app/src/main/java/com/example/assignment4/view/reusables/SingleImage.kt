package com.example.assignment4.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.assignment4.R

@Composable
fun SingleImage(
    imageUrl: String = "",
    imageSize: Dp = 200.dp,
    aspectRatio: Float = 1.8f,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                placeholder(R.drawable.banana_default)
            }),
        contentDescription = null,
        modifier = modifier
            .width(imageSize)
            .aspectRatio(aspectRatio),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
    )
}