package com.example.assignment4.view.app_composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.assignment4.data.entities.DairyEntry
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.view.reusables.AppButton
import com.example.assignment4.view.reusables.ImageGrid
import com.example.assignment4.view.reusables.ImageRow

/**
 * A dairy entry container used in Screen-22
 **/

@ExperimentalFoundationApi
@Composable
fun DairyEntryCard(
    dairyEntry: DairyEntry,
    onDirectionPress: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Date Label
            Text(
                text = dairyEntry.date,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = MaterialTheme.typography.h4.fontWeight,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )

            // Location Label
            Text(
                text = dairyEntry.location,
                fontSize = MaterialTheme.typography.body1.fontSize,
                fontWeight = MaterialTheme.typography.body1.fontWeight,
                modifier = Modifier.weight(2f)
            )

            AppButton(
                modifier = Modifier.weight(1.2f),
                width = Dm.smallLongButtonWidth,
                height = Dm.smallLongButtonHeight,
                label = "direction",
                buttonColor = Color.White,
                labelFontSize = MaterialTheme.typography.body1.fontSize
            ) {
                onDirectionPress()
            }
        }

        ImageRow(
            imageUrls = dairyEntry.imageUrls,
            imageWidth = Dm.gridImageWidthMedium,
            aspectRatio = 1f,
            modifier = Modifier.padding(vertical = Dm.marginSmall)
        )

        // Description text
        Text(
            text = dairyEntry.description,
            fontSize = MaterialTheme.typography.body1.fontSize,
            lineHeight = MaterialTheme.typography.body1.fontSize * 1.5,
            modifier = Modifier.padding(vertical = Dm.marginMedium)
        )
    }
}