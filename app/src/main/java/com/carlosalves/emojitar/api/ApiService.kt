package com.carlosalves.emojitar.api

import com.carlosalves.emojitar.api.dtos.AvatarDTO
import com.carlosalves.emojitar.api.dtos.RepoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("emojis")
    suspend fun getEmojis(): Response<Map<String, String>>

    @GET("users/{username}")
    suspend fun getAvatarsByUsername(
        @Path("username") username: String
    ): Response<AvatarDTO>

    @GET("users/google/repos")
    suspend fun getGoogleRepos(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): List<RepoDto>
}