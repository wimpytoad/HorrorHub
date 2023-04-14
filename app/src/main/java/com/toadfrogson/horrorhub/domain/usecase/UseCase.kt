package com.toadfrogson.horrorhub.domain.usecase

interface UseCase<in Params, out UseCaseResult> {
    suspend operator fun invoke(params: Params) : UseCaseResult
}