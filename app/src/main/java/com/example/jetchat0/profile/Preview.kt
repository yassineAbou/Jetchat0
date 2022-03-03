package com.example.jetchat0.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetchat0.data.colleagueProfile
import com.example.jetchat0.data.meProfile
import com.example.jetchat0.ui.theme.Jetchat0Theme

@Preview(widthDp = 340, name = "340 width - Me")
@Composable
fun ProfilePreview340() {
    Jetchat0Theme {
        ProfileScreen(userData = meProfile)
    }
}

@Preview(widthDp = 480, name = "480 width - Me")
@Composable
fun ProfilePreview480Me() {
    Jetchat0Theme {
        ProfileScreen(userData = meProfile)
    }
}

@Preview(widthDp = 480, name = "480 width - Other")
@Composable
fun ProfilePreview480Other() {
    Jetchat0Theme {
        ProfileScreen(userData = colleagueProfile)
    }
}

@Preview(widthDp = 340, name = "340 width - Me - Dark")
@Composable
fun ProfilePreview340MeDark() {
    Jetchat0Theme(isDarkTheme = true) {
        ProfileScreen(userData = meProfile)
    }
}

@Preview(widthDp = 480, name = "480 width - Me - Dark")
@Composable
fun ProfilePreview480MeDark() {
    Jetchat0Theme(isDarkTheme = true) {
        ProfileScreen(userData = meProfile)
    }
}

@Preview(widthDp = 480, name = "480 width - Other - Dark")
@Composable
fun ProfilePreview480OtherDark() {
    Jetchat0Theme(isDarkTheme = true) {
        ProfileScreen(userData = colleagueProfile)
    }
}

