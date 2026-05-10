package com.carlosalves.emojitar.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosalves.emojitar.avatarlistscreen.AvatarListScreen
import com.carlosalves.emojitar.avatarlistscreen.AvatarListViewModel

@Composable
fun AvatarListRoute(
    viewModel: AvatarListViewModel = hiltViewModel()
) {

    val uiState by viewModel
        .uiState
        .collectAsStateWithLifecycle()

    AvatarListScreen(
        uiState = uiState,
        onAvatarClick = viewModel::deleteAvatar
    )
}