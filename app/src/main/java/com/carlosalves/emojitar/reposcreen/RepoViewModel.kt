package com.carlosalves.emojitar.reposcreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.carlosalves.emojitar.repositories.repo.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(repository: RepoRepository) : ViewModel() {
    val repos = repository
        .getRepos()
        .cachedIn(viewModelScope)
}