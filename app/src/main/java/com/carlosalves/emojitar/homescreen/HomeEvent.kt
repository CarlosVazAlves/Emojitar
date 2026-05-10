package com.carlosalves.emojitar.homescreen

sealed interface HomeEvent {
    data object AvatarSaved : HomeEvent
    data object AvatarSaveFailed : HomeEvent
}