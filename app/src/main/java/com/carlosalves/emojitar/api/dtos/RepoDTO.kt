package com.carlosalves.emojitar.api.dtos

import com.carlosalves.emojitar.model.Repo
import com.google.gson.annotations.SerializedName

data class RepoDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("full_name")
    val fullName: String
) {
    fun toRepo() = Repo(id, fullName)
}