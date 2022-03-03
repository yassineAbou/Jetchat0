package com.example.jetchat0

const val USER_ID = "userId"

sealed class Screen(val route: String) {
    object Conversation: Screen("Conversation")
    object Profile: Screen("Profile/{$USER_ID}") {
        fun passUserId(userId: String) : String {
            return "Profile/$userId"
        }
    }
}
