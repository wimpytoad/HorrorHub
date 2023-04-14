package com.toadfrogson.horrorhub.domain.api.apiResponse

data class ApiResponse<T>(
    val success: Boolean = false,
    val data: T?,
    val errorResponse: ErrorResponse?
)