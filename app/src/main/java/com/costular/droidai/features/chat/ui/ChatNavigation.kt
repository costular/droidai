package com.costular.droidai.features.chat.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val CHAT_ROUTE = "chat"

fun NavController.navigateToChat(navOptions: NavOptions) = navigate(CHAT_ROUTE, navOptions)

fun NavGraphBuilder.chatScreen() {
    composable(
        route = CHAT_ROUTE,
    ) {
        ChatScreen()
    }
}
