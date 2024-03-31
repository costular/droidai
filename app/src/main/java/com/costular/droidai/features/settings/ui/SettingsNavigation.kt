package com.costular.droidai.features.settings.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val ROUTE = "settings"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) = navigate(ROUTE, navOptions)

fun NavGraphBuilder.settings(onNavigateUp: () -> Unit) {
    composable(route = ROUTE) {
        SettingsScreen(onNavigateUp)
    }
}
