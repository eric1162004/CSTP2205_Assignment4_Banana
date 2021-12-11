package com.example.assignment4.view.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.assignment4.R
import com.example.assignment4.data.entities.DairyEntry
import com.example.assignment4.data.fakeDairyList
import com.example.assignment4.data.fakeImageUrls
import com.example.assignment4.data.fakeLocationEntry
import com.example.assignment4.data.fakeLocations
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.ui.theme.FontStyle
import com.example.assignment4.ui.theme.Routes
import com.example.assignment4.ui.theme.ThirdColor
import com.example.assignment4.view.app_composables.AppMap
import com.example.assignment4.view.app_composables.Screen
import com.example.assignment4.view.app_composables.LocationSearchBar
import com.example.assignment4.view.reusables.*
import com.example.testing.reusables.CircularIconButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/**
 * This screen show a map to user.
 * The map is annotated with where the user has been and
 * information about the location.
 * **/

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@Composable
fun MapScreen(
    changeScreen: (toScreen: String) -> Unit
) {
    var displaySearchLocationInfoColumn by remember { mutableStateOf(false) }

    Screen {
        Box {
            // Map Layer
            AppMap(coordinates = fakeLocations) {
                displaySearchLocationInfoColumn = !displaySearchLocationInfoColumn
            }

            // Location Info Layer
            LocationInfoLayer(
                displaySearchLocationInfoColumn = displaySearchLocationInfoColumn,
                changeScreen = changeScreen
            )
        }
    }
}

@Composable
fun LocationInfoLayer(
    displaySearchLocationInfoColumn: Boolean,
    changeScreen: (toScreen: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dm.marginSmall)
            .padding(top = Dm.marginMedium),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LocationSearchBar() {
            // on location search
            changeScreen(Routes.ME)
        }

        if (displaySearchLocationInfoColumn)
            SearchLocationInfoColumn()
    }
}

@Composable
fun SearchLocationInfoColumn() {
    // !!Due to the top icon, need to shift Up by Dimension.bananaCardOffSetY
    BananaCard(contentOffsetY = -Dm.bananaCardOffSetY) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dm.marginMedium)
        ) {
            val scrollState = rememberScrollState()
            var displayMoreInfo by remember { mutableStateOf(false) }

            CircularIconButton(
                imageResource = toggleUpAndDownArrow(displayMoreInfo),
                iconTint = MaterialTheme.colors.primary,
                iconPadding = 0.dp
            ) { displayMoreInfo = !displayMoreInfo }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dm.marginMedium)
                    .verticalScroll(scrollState)
            ) {
                SearchLocationTitleSection(displayMoreInfo = displayMoreInfo)

                if (!displayMoreInfo) {
                    // show only when location has not been saved
                    SaveLocationSection()

                } else {
                    // show only when location is saved
                    SaveLocationImageRow()


                    // show only when location is saved
                    LocationInfoAndDiary()
                }
            }
        }
    }
}

@Composable
fun SearchLocationTitleSection(
    displayMoreInfo: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1.25f)
        ) {
            Text(
                text = "Stanley Park",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Start
            )

            Text(
                text = "Vancouver, BC V6G 1Z4",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = Dm.marginSmall / 2)
            )
        }

        Spacer(modifier = Modifier.weight(.50f))

        if (displayMoreInfo) {
            AppButton(label = "direction", modifier = Modifier.weight(1f)) {}
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun SaveLocationImageRow() {
    ImageRow(
        imageUrls = fakeImageUrls,
        modifier = Modifier.padding(vertical = Dm.marginMedium)
    )
}

@Composable
fun SaveLocationSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(vertical = Dm.marginSmall),
    ) {
        Column(
            modifier = Modifier.weight(1.75f)
        ) {
            SingleImage(
                imageUrl = fakeImageUrls[0],
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppButton(label = "save") {}
            AppButton(label = "direction") {}
        }
    }
}

@Composable
fun LocationInfoAndDiary() {
    val dairyEntries by remember { mutableStateOf(fakeDairyList) }
    var index by remember { mutableStateOf(0) }

    LocationInfoAndDiaryItem(0, fakeLocationEntry)

    dairyEntries.forEach {
        LocationInfoAndDiaryItem(++index, it)
    }
}

@Composable
private fun LocationInfoAndDiaryItem(
    index: Int,
    dairyEntry: DairyEntry
) {
    val offsetValue = 50.dp

    BananaCard(
        backgroundColor =
        if (index % 2 == 0) Color.White
        else ThirdColor,
        curveLeftExtend = false,
        topCurveHeight = 40.dp,
        modifier = Modifier
            .offset(y = if (index == 0) 0.dp
            else -offsetValue * index)
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = offsetValue)
                .padding(top = Dm.marginMedium)
                .padding(horizontal = Dm.marginMedium)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dm.marginSmall),
            ) {
                Text(
                    text = dairyEntry.title,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = dairyEntry.date,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start
                )
            }

            Text(
                text = dairyEntry.description,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun toggleUpAndDownArrow(displayMoreInfo: Boolean) = if (displayMoreInfo)
    R.drawable.arrow_down
else
    R.drawable.arrow_up_transparent



