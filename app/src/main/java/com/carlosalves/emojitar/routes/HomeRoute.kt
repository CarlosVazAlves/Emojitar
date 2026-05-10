package com.carlosalves.emojitar.routes

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.carlosalves.emojitar.R
import com.carlosalves.emojitar.homescreen.HomeEvent
import com.carlosalves.emojitar.homescreen.HomeScreen
import com.carlosalves.emojitar.homescreen.HomeViewModel
import com.carlosalves.emojitar.routes.Routes.AVATAR_LIST
import com.carlosalves.emojitar.routes.Routes.EMOJI_LIST

@Composable
fun HomeRoute(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel
        .uiState
        .collectAsStateWithLifecycle()

    val context = LocalContext.current
    val successToastText = stringResource(R.string.avatar_stored_successfully)
    val unsuccessToastText = stringResource(R.string.unable_to_store_avatar)

    LaunchedEffect(Unit) {
        viewModel.checkIfAnyAvatarStored()
        viewModel.event.collect { event ->
            val message = when(event) {
                HomeEvent.AvatarSaved -> successToastText
                HomeEvent.AvatarSaveFailed -> unsuccessToastText
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    HomeScreen(
        uiState = uiState,
        onRandomEmojiClick = viewModel::onRandomEmojiClick,
        onGetEmojiClick = viewModel::loadEmojis,
        onAvatarSearch = viewModel::searchAvatar,
        onEmojiListClick = {
            navController.navigate(EMOJI_LIST)
        },
        onAvatarListClick = {
            navController.navigate(AVATAR_LIST)
        }
    )
}