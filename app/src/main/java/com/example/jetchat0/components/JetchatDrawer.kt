package com.example.jetchat0.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetchat0.ui.theme.Jetchat0Theme
import com.example.jetchat0.R
import com.example.jetchat0.data.colleagueProfile
import com.example.jetchat0.data.meProfile

@Preview
@Composable
fun DrawerPreview() {
    Jetchat0Theme {
        Surface() {
           Column {
               JetchatDrawer({}, {})
           }
        }
    }
}

@Preview
@Composable
fun DrawerPreviewDark() {
    Jetchat0Theme(isDarkTheme = true) {
        Surface() {
            Column {
                JetchatDrawer({}, {})
            }
        }
    }
}
@Composable
fun ColumnScope.JetchatDrawer(
    onProfileClicked: (String) -> Unit,
    onChatClicked: () -> Unit
) {
    // TODO: Use statusBarsHeight() to add a spacer which pushes the drawer content below the status bar (y-axis)
    DrawerHeader()
    DividerItem()
    DrawerItemHeader("Chats")
    ChatItem("composers", true, onChatClicked)
    ChatItem("droidcon-nyc", false, onChatClicked )
    DividerItem(Modifier.padding(start = 28.dp))
    DrawerItemHeader(text = "Recent Profiles")
    // TODO: Profile fake data
    ProfileItem(text = "Ali Conors (you)", profilePic = meProfile.photo) { onProfileClicked("me") }
    ProfileItem(text = "Taylor Brooks", profilePic = colleagueProfile.photo) { onProfileClicked("Taylor Brooks") }
}

@Composable
private fun DrawerHeader() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = CenterVertically
    ) {
        JetChatIcon(
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Image(painter = painterResource(id = R.drawable.jetchat_logo),
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp)
        )

    }
}

@Composable
private fun DrawerItemHeader(text: String) {
    Box(
        modifier = Modifier
            .heightIn(min = 52.dp)
            .padding(horizontal = 28.dp),
        contentAlignment = CenterStart
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

    }

}

@Composable
private fun ChatItem(text: String, selected: Boolean, onChatClicked: () -> Unit) {
    val background = if (selected) {
        Modifier.background(MaterialTheme.colorScheme.primaryContainer)
    } else {
        Modifier
    }
    Row(
        modifier = Modifier
            .heightIn(65.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .then(background)
            .clickable(onClick = onChatClicked)
    ) {
       val iconTint = if (selected) {
           MaterialTheme.colorScheme.primary
       } else {
           MaterialTheme.colorScheme.onSurfaceVariant
       }
       Icon(
           painter = painterResource(id = R.drawable.ic_jetchat),
           tint = iconTint,
           modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
           contentDescription = null
       )
       Text(
           text = text,
           style = MaterialTheme.typography.bodyMedium,
           color = if (selected) {
               MaterialTheme.colorScheme.primary
           } else {
               MaterialTheme.colorScheme.onSurface
           },
           modifier = Modifier.padding(start = 12.dp)
       )

    }

}

@Composable
private fun ProfileItem(
    text: String,
    @DrawableRes profilePic: Int?,
    onProfileClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = onProfileClicked),
        verticalAlignment = CenterVertically
    ) {
        val paddingSizeModifier = Modifier
            .padding(start = 16.dp, bottom = 16.dp, top = 16.dp)
            .size(24.dp)
        if (profilePic != null) {
            Image(
                painterResource(id = profilePic),
                modifier = paddingSizeModifier.then(Modifier.clip(CircleShape)),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        } else {
            Spacer(modifier = paddingSizeModifier)
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 12.dp)
        )
    }

}

@Composable
fun DividerItem(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier,
        color =  MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )
}
