package com.toadfrogson.horrorhub.domain.usecase

import com.toadfrogson.horrorhub.domain.usecase.ErrorType.Unknown

data class UseCaseError(val type: ErrorType = Unknown, val message: String = "no idea")


sealed class ErrorType() {
    object NoContent: ErrorType()
    object NoConnection: ErrorType()
    object Unknown: ErrorType()
}
