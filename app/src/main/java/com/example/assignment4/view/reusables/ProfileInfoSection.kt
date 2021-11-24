package com.example.assignment4.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.assignment4.R
import com.example.assignment4.data.entities.User
import com.example.assignment4.ui.theme.Dm
import com.example.testing.reusables.CircularIconButton

@Composable
fun ProfileInfoSection(
    user: User,
    iconResource: Int? = null,
    modifier: Modifier = Modifier,
    onIconPress: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1.5f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(
                    data = user.profileImageUrl,
                    builder = {
                        placeholder(R.drawable.banana_default)
                    }),
                contentDescription = null,
                modifier = Modifier
                    .width(Dm.profileImageSize)
                    .aspectRatio(1f)
                    .padding(Dm.marginSmall)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            modifier = Modifier
                .weight(3.1f)
                .padding(start = Dm.marginSmall),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.h6,
            )
        }

        when (iconResource) {
            null -> {
                Spacer(modifier = Modifier.weight(1f))
            }
            else -> {
                CircularIconButton(
                    modifier = Modifier.weight(1f).offset(x = Dm.marginMedium),
                    imageResource = iconResource,
                    iconTint = Color.White,
                    iconPadding = 0.dp,
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    onIconPress()
                }
            }
        }
    }
}