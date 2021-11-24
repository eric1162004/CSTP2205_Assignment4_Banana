package com.example.assignment4.view.app_composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.view.reusables.ImageGrid

/**AlbumDateImageRow only works on full width!!!
Image width calculation depends on screen width.**/
@Composable
fun AlbumDateImageRow(
    date: String = "Oct 4 2021",
    imageCountPerRow: Int = 4,
    imageUrls: List<String> = listOf(),
    modifier: Modifier = Modifier,
    onImagePress: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth by remember { mutableStateOf(configuration.screenWidthDp.dp) }

    Column(
        modifier = modifier
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(Dm.marginSmall)
        )
        ImageGrid(
            imageUrls = imageUrls,
            imageWidth = screenWidth / imageCountPerRow,
            modifier = Modifier.clickable {
                // go to next screen content
                onImagePress()
            }
        )
    }
}