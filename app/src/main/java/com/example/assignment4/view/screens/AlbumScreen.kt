package com.example.assignment4.view.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.assignment4.R
import com.example.assignment4.data.*
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.ui.theme.Routes
import com.example.assignment4.view.app_composables.AlbumDateImageRow
import com.example.assignment4.view.app_composables.AlbumItemCard
import com.example.assignment4.view.app_composables.AppMap
import com.example.assignment4.view.app_composables.Screen
import com.example.assignment4.view.reusables.AppTopBar
import com.example.assignment4.view.reusables.BananaCard
import com.example.assignment4.view.reusables.ImageGrid
import com.example.assignment4.view.reusables.LoadingContent
import com.example.testing.reusables.CircularIconButton
import com.example.testing.reusables.CircularImageButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun AlbumScreen() {
    var displayOverlayScreen by remember { mutableStateOf(false) }

    var currentScreenContent by remember { mutableStateOf(Routes.ALBUM_SCREEN_10) }

    var changeScreenContent: (toScreen: String) -> Unit = {
        currentScreenContent = it
    }

    var coroutineScope = rememberCoroutineScope()

    Box {
        Screen(
            backgroundColor = Color.White,
            appTopBar = {
                AppTopBar(
                    backgroundColor = Color.White,
                    title = "album",
                    leftIcon = {
                        if (currentScreenContent != Routes.ALBUM_SCREEN_10)
                            CircularIconButton(
                                backgroundColor = MaterialTheme.colors.primary,
                                imageResource = R.drawable.arrow_back
                            ) {
                                changeScreenContent(Routes.ALBUM_SCREEN_10)
                            }
                    },
                    rightIcon = {
                        CircularIconButton(
                            backgroundColor = MaterialTheme.colors.primary,
                            imageResource = R.drawable.icon_add
                        ) {
                            displayOverlayScreen = !displayOverlayScreen
                        }
                    }
                )
            }
        ) {
            // Screen content...
            when (currentScreenContent) {
                Routes.ALBUM_SCREEN_10 -> AlbumScreen_10(
                ) {
                    changeScreenContent(it)
                }
                Routes.ALBUM_SCREEN_14 -> AlbumScreen_14()
                else -> LoadingContent()
            }
        }

        DiaryOverlayScreen(
            displayOverlayScreen = displayOverlayScreen,
            onDismissPressed = {
                displayOverlayScreen = !displayOverlayScreen

                // Issue: images disappear when the overlay screen show up
                // for now, do an artificial refresh screen
                if (currentScreenContent == Routes.ALBUM_SCREEN_10) {
                    coroutineScope.launch(Dispatchers.Main) {
                        currentScreenContent = "Loading"
                        delay(200L)
                        currentScreenContent = Routes.ALBUM_SCREEN_10
                    }
                }
            }
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun AlbumScreen_10(
    changeScreenContent: (toScreen: String) -> Unit
) {
    val imageUrls by remember { mutableStateOf(fakeImageUrls) }

    LazyColumn() {
        repeat(4) {
            item {
                AlbumDateImageRow(
                    date = "Oct ${4 * (it + 1)}, 2021",
                    imageUrls = imageUrls
                ) {
                    changeScreenContent(Routes.ALBUM_SCREEN_14)
                }
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun AlbumScreen_14(
) {
    Column() {
        AlbumItemCard(imageUrl = fakeImageUrls[0])

        Spacer(Modifier.height(Dm.marginMedium))

        Row(
            modifier = Modifier
                .padding(horizontal = Dm.marginMedium)
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                BananaCard(
                    curveLeftExtend = false,
                    topCurveHeight = 40.dp,
                ) {
                    Text(
                        text = "my diary: This implies revolution, not necessarily an armed uprising " +
                                "empire",
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.caption,
                        maxLines = 4,
                        modifier = Modifier
                            .padding(Dm.marginSmall)
                            .clip(MaterialTheme.shapes.small)
                            .background(Color.White)
                            .padding(Dm.marginSmall)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dm.marginSmall),
                    horizontalArrangement = Arrangement.End
                ) {
                    CircularImageButton(
                        imageResource = R.drawable.banana_pencil,
                        backgroundColor = Color.LightGray,
                        modifier = Modifier.offset(y = -25.dp)
                    ) {}
                }
            }

            Spacer(Modifier.width(Dm.marginMedium))

            Box(
                modifier = Modifier.weight(1f)
            ) {
                BananaCard(
                    curveLeftExtend = false,
                    topCurveHeight = 40.dp,
                ) {
                    AppMap(
                        coordinates = fakeLocations,
                        modifier = Modifier
                            .padding(Dm.marginSmall)
                            .clip(MaterialTheme.shapes.small)
                            .aspectRatio(1.7f)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dm.marginSmall),
                    horizontalArrangement = Arrangement.End
                ) {
                    CircularImageButton(
                        imageResource = R.drawable.banana_gallery,
                        backgroundColor = Color.LightGray,
                        modifier = Modifier.offset(y = (-25).dp)
                    ) {}
                }
            }
        }

    }
}

