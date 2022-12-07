package com.example.lab_4.ui.theme.compose.navigation

sealed class ScreenDestinations(val route: String) {
    object Home : ScreenDestinations("home_screen")
    object News : ScreenDestinations("news_screen")
    object Settings : ScreenDestinations("settings_screen")
    object Todo : ScreenDestinations("todo_screen")
}