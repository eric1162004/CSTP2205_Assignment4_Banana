package com.example.assignment4.view.app_composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignment4.R
import com.example.assignment4.view.reusables.CustomTextField
import com.example.assignment4.view.reusables.IconInputField
import com.example.testing.reusables.CircularImageButton

@Composable
fun LocationSearchBar(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onSearchTextChanged: (searchText: String) -> Unit = {},
    onSearchIconPressed: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTextField(
            value = searchText,
            placeHolderText = "search location",
            modifier = Modifier.weight(5f),
            leadingImageResource = R.drawable.search_icon
        ) {
            onSearchTextChanged(it)
        }

        Spacer(modifier = Modifier.weight(.5f))

        CircularImageButton(
            imageResource = R.drawable.banana_welcome,
            imagePadding = 5.dp,
            modifier = Modifier.weight(1f)
        ) {
            onSearchIconPressed()
        }
    }
}