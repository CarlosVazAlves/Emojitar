package com.carlosalves.emojitar.routes

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.carlosalves.emojitar.reposcreen.RepoScreen
import com.carlosalves.emojitar.reposcreen.RepoViewModel

@Composable
fun RepoRoute(
    viewModel: RepoViewModel = hiltViewModel()
) {
    val repos = viewModel
        .repos
        .collectAsLazyPagingItems()

    RepoScreen(repos)
}