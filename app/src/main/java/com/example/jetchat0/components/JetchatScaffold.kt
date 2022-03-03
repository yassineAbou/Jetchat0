package com.example.jetchat0.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import com.example.jetchat0.ui.theme.Jetchat0Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetchatScaffold(
    drawerState: DrawerState = rememberDrawerState(initialValue = Closed),
    onProfileClicked: (String) -> Unit,
    onChatClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    Jetchat0Theme {
        NavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                JetchatDrawer(
                    onProfileClicked = onProfileClicked,
                    onChatClicked = onChatClicked
                )
            },
            content = content
        )
    }

}