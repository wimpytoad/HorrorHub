package com.toadfrogson.horrorhub.domain.repo.repoResult

sealed class RepoResult<out T> {
    data class Success<out T>(val data: T) : RepoResult<T>()
    data class Failure(val errorMessage: String) : RepoResult<Nothing>()
}