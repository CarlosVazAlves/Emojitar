package com.carlosalves.emojitar

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.hilt.android.HiltAndroidApp
import androidx.navigation.compose.rememberNavController
import com.carlosalves.emojitar.routes.AvatarListRoute
import com.carlosalves.emojitar.routes.EmojiListRoute
import com.carlosalves.emojitar.routes.HomeRoute
import com.carlosalves.emojitar.routes.RepoRoute
import com.carlosalves.emojitar.routes.Routes.AVATAR_LIST
import com.carlosalves.emojitar.routes.Routes.EMOJI_LIST
import com.carlosalves.emojitar.routes.Routes.HOME
import com.carlosalves.emojitar.routes.Routes.REPO

@HiltAndroidApp
class EmojitarApplication : Application()

@Composable
fun EmojitarApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HOME
    ) {
        composable(HOME) {
            HomeRoute(navController)
        }
        composable(EMOJI_LIST) {
            EmojiListRoute()
        }
        composable(AVATAR_LIST) {
            AvatarListRoute()
        }
        composable(REPO) {
            RepoRoute()
        }
    }
}