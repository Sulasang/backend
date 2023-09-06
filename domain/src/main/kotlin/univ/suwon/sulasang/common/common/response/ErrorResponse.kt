package univ.suwon.sulasang.common.common.response

import univ.suwon.sulasang.common.common.exception.SuLaSangErrorCode

data class ErrorResponse(
    val status: Int,
    val error: Error
) {
    companion object {
        fun of(suLaSangErrorCode: SuLaSangErrorCode, detailMessage: String ?= null): ErrorResponse {
            return ErrorResponse(
                status = suLaSangErrorCode.httpStatus.value(),
                error = Error(
                    code = suLaSangErrorCode.errorCode,
                    detailMessage = detailMessage
                )
            )
        }
    }
}

data class Error(
    val code: String,
    val detailMessage: String?
)
