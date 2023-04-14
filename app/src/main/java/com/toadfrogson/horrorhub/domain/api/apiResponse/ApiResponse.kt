package com.toadfrogson.horrorhub.domain.api.apiResponse

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val errorMessage: String) : ApiResult<Nothing>()
}