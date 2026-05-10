package com.carlosalves.emojitar

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.hilt.android.HiltAndroidApp
import androidx.navigation.compose.rememberNavController
import com.carlosalves.emojitar.routes.EmojiListRoute
import com.carlosalves.emojitar.routes.HomeRoute

@HiltAndroidApp
class EmojitarApplication : Application()

@Composable
fun EmojitarApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeRoute(navController)
        }
        composable("emoji_list") {
            EmojiListRoute()
        }
    }
}