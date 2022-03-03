package com.example.jetchat0.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetchat0.ui.theme.Jetchat0Theme
import com.example.jetchat0.R


@Composable
fun JetchatAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    title: @Composable () -> Unit,
    onNavIconPressed: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}

) {
    val backgroundColors = TopAppBarDefaults.centerAlignedTopAppBarColors()
    val backgroundColor = backgroundColors.containerColor(
        scrollFraction = scrollBehavior?.scrollFraction ?: 0f
    ).value
    val foregroundColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent,
        scrolledContainerColor = Color.Transparent
    )
    Box(modifier = Modifier.background(backgroundColor))  {
        CenterAlignedTopAppBar(
            modifier = modifier,
            actions = actions,
            title = title,
            scrollBehavior = scrollBehavior,
            colors = foregroundColors,
            navigationIcon = {
                JetChatIcon(
                    contentDescription = stringResource(id = R.string.navigation_drawer_open),
                    modifier = Modifier
                        .size(64.dp)
                        .clickable(onClick = onNavIconPressed)
                        .padding(16.dp)
                )
            }
        )
    }
 }


@Preview
@Composable
fun JetchatAppBarPreview() {
    Jetchat0Theme {
        JetchatAppBar(title = { Text("Preview!") })
    }
}

@Preview
@Composable
fun JetchatAppBarPreviewDark() {
    Jetchat0Theme(isDarkTheme = true) {
        JetchatAppBar(title = { Text("Preview!") })
    }
}

