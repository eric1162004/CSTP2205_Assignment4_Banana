package com.example.assignment4.view.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import be.sigmadelta.calpose.model.CalposeActions
import com.example.assignment4.R
import com.example.assignment4.data.entities.DairyEntry
import com.example.assignment4.data.fakeDairyList
import com.example.assignment4.data.fakeDates
import com.example.assignment4.data.fakeImageUrls
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.ui.theme.Routes
import com.example.assignment4.ui.theme.ThirdColor
import com.example.assignment4.utils.calendar.AppCalendar
import com.example.assignment4.view.app_composables.AlbumDateImageRow
import com.example.assignment4.view.app_composables.Screen
import com.example.assignment4.view.reusables.AppTopBar
import com.example.assignment4.view.reusables.BananaCard
import com.example.assignment4.view.reusables.LoadingContent
import com.example.assignment4.view.reusables.OverlayScreen
import com.example.testing.reusables.CircularIconButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.threeten.bp.YearMonth

/**
 * This file includes the screen-9 and 16.
 * Screen-9 displays a dairy calender.
 * Screen-16 display details about a dairy entry.
 * **/

@ExperimentalFoundationApi
@Composable
fun DiaryScreen(changeScreen :(toScreen: String) -> Unit) {
    var displayOverlayScreen by remember { mutableStateOf(false) }

    var currentScreenContent by remember { mutableStateOf(Routes.DIARY_SCREEN_9) }

    var changeScreenContent: (toScreen: String) -> Unit = {
        currentScreenContent = it
    }

    // Needed this Box to impose the OverlayScreen on top of the screen content
    Box {
        Screen(
            appTopBar = {
                AppTopBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    title = "diary",
                    leftIcon = {
                        if (currentScreenContent != Routes.DIARY_SCREEN_9)
                            CircularIconButton(
                                imageResource = R.drawable.arrow_back
                            ) {
                                changeScreenContent(Routes.DIARY_SCREEN_9)
                            }
                    },
                    rightIcon = {
                        CircularIconButton(imageResource = R.drawable.icon_add) {
                            displayOverlayScreen = !displayOverlayScreen
                        }
                    }
                )
            }
        ) {
            // Content here...
            Column {
                Spacer(Modifier.height(Dm.marginSmall))

                when (currentScreenContent) {
                    Routes.DIARY_SCREEN_9 -> DiaryScreen_9(
                    ) {
                        changeScreenContent(it)
                    }
                    Routes.DIARY_SCREEN_16 -> DiaryScreen_16()
                    else -> LoadingContent()
                }
            }
        }

        // Overlay Screen for this screen
        DiaryOverlayScreen(
            displayOverlayScreen = displayOverlayScreen,
            onDismissPressed = { displayOverlayScreen = !displayOverlayScreen },
            onBookIconPressed = {changeScreen(Routes.DIARY_WRITING_SCREEN)}
        )
    }
}

@Composable
fun DiaryScreen_9(
    changeScreenContent: (toScreen: String) -> Unit,
) {
    var month by remember { mutableStateOf(YearMonth.of(2021, 10)) }
    var dates by remember { mutableStateOf(fakeDates) }

    AppCalendar(
        month = month,
        dates = dates,
        actions = CalposeActions(
            onClickedPreviousMonth = {},
            onClickedNextMonth = {}
        ),
        modifier = Modifier
            .padding(horizontal = Dm.marginMedium)
            .clickable {
                changeScreenContent(Routes.DIARY_SCREEN_16)
            }
    )
}

@Composable
fun DiaryScreen_16(
) {
    val imageUrls by remember { mutableStateOf(fakeImageUrls) }
    var displayImageRow by remember { mutableStateOf(false) }

    // Temporary Fix to Issue: Images do not display
    LaunchedEffect(key1 = true) {
        delay(200L)
        displayImageRow = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (displayImageRow) {
            AlbumDateImageRow(
                date = "Oct 4, 2021",
                imageUrls = imageUrls
            ) {}
        }

        DairyEntriesColumn(
            dairies = fakeDairyList,
            modifier = Modifier
                .zIndex(1f)
                .padding(horizontal = Dm.marginLarge)
                .offset(y = 125.dp)
        )
    }
}

@Composable
fun DairyEntriesColumn(
    dairies: List<DairyEntry> = listOf(),
    modifier: Modifier = Modifier
) {
    val cardHeight = 150.dp
    val offsetHeight = cardHeight - 30.dp

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
            )
        }
        itemsIndexed(dairies) { index, dairy ->
            var spacerHeight by remember { mutableStateOf(0.dp) }

            BananaCard(
                backgroundColor = if (index % 2 == 0) Color.White else ThirdColor,
                topCurveHeight = 20.dp,
                curveLeftExtend = false,
                modifier = Modifier
                    .offset(
                        y = if (index > 0) -((offsetHeight) * index + spacerHeight)
                        else -spacerHeight
                    )
                    .clickable {
                        if (spacerHeight == 0.dp)
                            spacerHeight = 100.dp
                        else
                            spacerHeight = 0.dp
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardHeight)
                        .padding(horizontal = Dm.marginMedium, vertical = Dm.marginSmall)
                ) {
                    Text(
                        text = dairy.title,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Dm.marginSmall)
                    )

                    Card(
                        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = dairy.description,
                            style = MaterialTheme.typography.body1,
                            lineHeight = MaterialTheme.typography.body1.fontSize,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(Dm.marginSmall)
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(spacerHeight)
                    )

                }
            }
        }
    }
}


@Composable
fun DiaryOverlayScreen(
    onDismissPressed: () -> Unit = {},
    onImageIconPressed: () -> Unit = {},
    onBookIconPressed: () -> Unit = {},
    displayOverlayScreen: Boolean = false,
) {
    if (displayOverlayScreen) {
        OverlayScreen {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dm.marginLarge)
                    .padding(end = Dm.marginLarge),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                CircularIconButton(
                    imageResource = R.drawable.close,
                    modifier = Modifier.padding(bottom = Dm.marginSmall)
                ) {
                    onDismissPressed()
                }

                CircularIconButton(
                    imageResource = R.drawable.image,
                    iconTint = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(bottom = Dm.marginSmall)
                ) {
                    onImageIconPressed()
                }

                CircularIconButton(
                    imageResource = R.drawable.book,
                    iconTint = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(bottom = Dm.marginSmall)
                ) {
                    onBookIconPressed()
                }
            }
        }
    }
}