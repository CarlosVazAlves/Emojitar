package com.carlosalves.emojitar.repositories.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.carlosalves.emojitar.api.ApiService
import com.carlosalves.emojitar.api.dtos.RepoDto
import com.carlosalves.emojitar.model.Repo
import com.carlosalves.emojitar.repositories.repo.RepoConstants.Companion.PAGE_SIZE

class RepoPagingSource(private val api: ApiService) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 1
            val repos = api.getGoogleRepos(page, PAGE_SIZE)

            LoadResult.Page(
                data = repos.map(RepoDto::toRepo),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (repos.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)
                ?.nextKey
                ?.minus(1)
        }
    }
}