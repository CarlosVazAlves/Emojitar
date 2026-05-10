package com.carlosalves.emojitar.repositories.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.carlosalves.emojitar.api.ApiService
import com.carlosalves.emojitar.model.Repo
import com.carlosalves.emojitar.repositories.repo.RepoConstants.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoRepository @Inject constructor(private val api: ApiService) {
    fun getRepos(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { RepoPagingSource(api) }
        ).flow
    }
}