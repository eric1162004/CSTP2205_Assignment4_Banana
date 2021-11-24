package com.example.assignment4.view.app_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.assignment4.view.reusables.ImageGrid

@Composable
fun MonthlyImageGrid(
    imageUrls: List<String> = listOf(),
    monthYearLabel: String = "Oct, 2021",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = monthYearLabel,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(2f)
        )

        ImageGrid(
            imageUrls = imageUrls,
            columnSize = 3,
            modifier = Modifier.weight(4f),
        )
    }
}