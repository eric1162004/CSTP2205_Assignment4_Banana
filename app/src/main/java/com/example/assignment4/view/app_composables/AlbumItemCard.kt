package com.example.assignment4.view.app_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter
import com.example.assignment4.R
import com.example.assignment4.ui.theme.Dm

@Composable
fun AlbumItemCard(
    location: String = "Stanley Park",
    date: String = "Oct 1, 2020",
    imageUrl: String = "",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dm.marginSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Location
            Text(
                text = location,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Start,
            )

            Text(
                text = date,
                style = MaterialTheme.typography.body1,
            )
        }

        Image(
            painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    placeholder(R.drawable.banana_default)
                }),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.2f)
                .padding(vertical = Dm.marginSmall),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )
    }
}