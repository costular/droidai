package com.costular.droidai.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.costular.droidai.features.chat.ui.CHAT_ROUTE
import com.costular.droidai.features.chat.ui.chatScreen
import com.costular.droidai.features.settings.ui.navigateToSettings
import com.costular.droidai.features.settings.ui.settings

@Composable
fun Home(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = CHAT_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        chatScreen(
            navigateToSettings = navController::navigateToSettings
        )

        settings(
            onNavigateUp = navController::navigateUp,
        )
    }
}
