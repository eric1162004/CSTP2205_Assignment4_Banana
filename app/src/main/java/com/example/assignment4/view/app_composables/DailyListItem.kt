package com.example.assignment4.view.app_composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.assignment4.R
import com.example.assignment4.data.entities.DairyEntry
import com.example.assignment4.ui.theme.Dm

@ExperimentalFoundationApi
@Composable
fun DailyListItem(
    dairyEntry: DairyEntry,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Date Label
            Text(
                text = dairyEntry.date,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Start,
            )

            // Location Label
            Text(
                text = dairyEntry.location,
                style = MaterialTheme.typography.caption
            )

        }

        Column(
            modifier = Modifier.weight(2f),
        ) {
            LazyRow(){
                items(items = dairyEntry.imageUrls){ item ->
                    Image(
                        painter = rememberImagePainter(
                            data = item,
                            builder = {
                                placeholder(R.drawable.banana_default)
                            }),
                        contentDescription = null,
                        modifier = Modifier
                            .width(80.dp)
                            .aspectRatio(1f)
                            .padding(0.5.dp),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                    )
                }
            }

            // Location Description
            Text(
                text = dairyEntry.description,
                lineHeight = MaterialTheme.typography.body1.fontSize * 1.5,
                maxLines = 5,
                modifier = Modifier.padding(vertical = Dm.marginMedium),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}