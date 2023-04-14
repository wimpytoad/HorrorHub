package com.toadfrogson.horrorhub.domain.usecase

interface UseCase<in Params, out UseCaseResult> {
    suspend operator fun invoke(params: Params) : UseCaseResult
}

sealed interface UseCaseResult<out T> {
    data class Success<out T: Any>(val data: T) : UseCaseResult<T>
    data class Failure(private val error: UseCaseError) : UseCaseResult<Nothing> {
        val errorType: ErrorType = error.type
    }
}

