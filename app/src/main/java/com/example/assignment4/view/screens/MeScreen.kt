package com.example.assignment4.view.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.assignment4.R
import com.example.assignment4.data.entities.DairyEntry
import com.example.assignment4.data.entities.User
import com.example.assignment4.data.fakeDairy
import com.example.assignment4.data.fakeDairyList
import com.example.assignment4.data.fakeMonthlyImageList
import com.example.assignment4.data.fakeUser
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.ui.theme.Routes
import com.example.assignment4.view.app_composables.*
import com.example.assignment4.view.reusables.AppButton
import com.example.assignment4.view.reusables.AppTopBar
import com.example.assignment4.view.reusables.HorizontalDivider
import com.example.assignment4.view.reusables.ProfileInfoSection
import com.example.testing.reusables.CircularIconButton

/**
 * MeScreen is the parent screen for:
 * screen-19, -20, -21, -22.
 *
 * Screen-19 displays monthly image grids
 * Screen-20 displays user settings
 * Screen-21 displays a list of dairy entries
 * Screen-22 displays details of a dairy entry
 * **/

@ExperimentalFoundationApi
@Composable
fun MeScreen(logOut: () -> Unit) {
    var currentScreenContent by remember { mutableStateOf(Routes.ME_SCREEN_19) }
    var routeStack by remember { mutableStateOf(mutableListOf<String>()) }

    var changeScreenContent: (toScreen: String) -> Unit = {

        // Temporary Fix...
        // If current page is the last page, do not add to the stack
        // prevent circularity
        if (currentScreenContent !== Routes.ME_SCREEN_22)
            routeStack.add(currentScreenContent)

        currentScreenContent = it
    }

    Screen(
        appTopBar = {
            AppTopBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = "me",
                leftIcon = {
                    if (currentScreenContent != Routes.ME_SCREEN_19)
                        CircularIconButton(
                            imageResource = R.drawable.arrow_back,
                        ) {
                            if (routeStack.isNotEmpty())
                                changeScreenContent(routeStack.removeLast())
                        }
                },
                rightIcon = {
                    if (currentScreenContent != Routes.ME_SCREEN_19)
                        CircularIconButton(
                            imageResource = R.drawable.create,
                        ) {}
                }
            )
        }
    ) {
        // screen content...
        Box(modifier = Modifier.padding(horizontal = 0.dp)) {
            when (currentScreenContent) {
                Routes.ME_SCREEN_19 -> MeScreen_19(fakeUser) {
                    changeScreenContent(it)
                }
                Routes.ME_SCREEN_20 -> MeScreen_20(user = fakeUser, logOut = logOut)
                Routes.ME_SCREEN_21 -> MeScreen_21(fakeDairyList) {
                    changeScreenContent(it)
                }
                Routes.ME_SCREEN_22 -> MeScreen_22(fakeDairy)
            }
        }
    }
}

@Composable
private fun MeScreen_19(
    user: User,
    modifier: Modifier = Modifier,
    changeScreenContent: (toScreen: String) -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = Dm.marginMedium)
    ) {
        ProfileInfoSection(
            user = user,
            iconResource = R.drawable.chevron_right,
            modifier = Modifier.padding(top = Dm.marginSmall)
        ) {
            changeScreenContent(Routes.ME_SCREEN_20)
        }

        MonthlyImageList(
            monthlyImageList = fakeMonthlyImageList,
            changeScreenContent = changeScreenContent,
        )
    }
}

@Composable
private fun MeScreen_20(
    user: User,
    logOut: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        ProfileInfoSection(
            user = user,
            modifier = Modifier
                .padding(top = Dm.marginSmall)
                .padding(horizontal = Dm.marginSmall)
        )

        // User Settings
        UserSettingSection(logOut)
    }
}

@Composable
private fun UserSettingSection(logOut: () -> Unit) {
    var checkedState by remember { mutableStateOf(true) }

    HorizontalDivider()
    SettingBarWithRightChevron(label = "Account Security")
    HorizontalDivider()

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
    )

    HorizontalDivider()
    SettingBarWithRightChevron(label = "Notification") {}
    HorizontalDivider()
    SettingBarWithSwitchToggle(
        label = "Privacy",
        checkedState = checkedState
    ) {
        checkedState = it
    }
    HorizontalDivider()
    SettingBarWithRightChevron(label = "Policy") {}
    HorizontalDivider()

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )

    HorizontalDivider()
    SettingBarWithRightChevron(label = "Help") {}
    HorizontalDivider()
    SettingBarWithRightChevron(label = "Feedback") {}
    HorizontalDivider()

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
    )

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dm.marginSmall)
    ) {
        AppButton(
            label = "sign out"
        ) {
            // Sign out
            logOut()
        }
    }
}

@Composable
private fun SettingBarWithRightChevron(
    label: String = "Privacy",
    modifier: Modifier = Modifier,
    onTrailingIconPressed: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .clickable {
                onTrailingIconPressed()
            }
            .padding(start = Dm.marginMedium)
    ) {
        Text(text = label, style = MaterialTheme.typography.h5)

        CircularIconButton(
            imageResource = R.drawable.chevron_right,
            backgroundColor = MaterialTheme.colors.primary,
            iconTint = Color.White,
        ) {}
    }
}

@Composable
private fun SettingBarWithSwitchToggle(
    label: String = "Account Security",
    checkedState: Boolean,
    modifier: Modifier = Modifier,
    onSwitchToggle: (checkState: Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .height(Dm.buttonDefaultSize)
            .padding(start = Dm.marginMedium)
    ) {
        Text(text = label, style = MaterialTheme.typography.h5)

        Switch(
            checked = checkedState,
            onCheckedChange = { onSwitchToggle(it) },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.Black,
                uncheckedTrackAlpha = 1f
            ),
            modifier = Modifier.offset(x = (-18).dp)
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun MeScreen_21(
    dailyList: List<DairyEntry> = listOf(),
    modifier: Modifier = Modifier,
    changeScreenContent: (toScreen: String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = Dm.marginMedium)
            .fillMaxSize()
    ) {
        MeScreenSmallProfileImageBanner(profileImageUrl = fakeUser.profileImageUrl)

        LazyColumn {
            items(items = dailyList) { diaryEntry ->
                DailyListItem(
                    dairyEntry = diaryEntry,
                    modifier = Modifier
                        .padding(vertical = Dm.marginMedium)
                        .clickable {
                            changeScreenContent(Routes.ME_SCREEN_22)
                        })
            }
        }
    }
}

@Composable
private fun MeScreenSmallProfileImageBanner(
    profileImageUrl: String = "",
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dm.marginSmall),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (profileImageUrl.isNotEmpty())
            Image(
                painter = rememberImagePainter(
                    data = profileImageUrl,
                    builder = {
                        placeholder(R.drawable.banana_default)
                    }),
                contentDescription = null,
                modifier = Modifier
                    .width(Dm.profileImageSizeSmall)
                    .aspectRatio(1f)
                    .padding(Dm.marginSmall)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
    }
}

@ExperimentalFoundationApi
@Composable
fun MeScreen_22(
    dairyEntry: DairyEntry = DairyEntry(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dm.marginMedium)
    ) {
        MeScreenSmallProfileImageBanner(profileImageUrl = fakeUser.profileImageUrl)

        LazyColumn {
            item {
                DairyEntryCard(
                    dairyEntry = dairyEntry
                )
            }
        }
    }
}

@Composable
fun MonthlyImageList(
    monthlyImageList: List<List<String>> = listOf(),
    changeScreenContent: (toScreen: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    if (monthlyImageList.isEmpty()) {
        return Text(text = "Empty List...")
    }

    Column(modifier = modifier.verticalScroll(scrollState)) {
        for (imageList in monthlyImageList) {
            MonthlyImageGrid(
                imageUrls = imageList,
                modifier = Modifier
                    .padding(top = Dm.marginLarge)
                    .clickable {
                        changeScreenContent(Routes.ME_SCREEN_21)
                    }
            )
        }
    }
}