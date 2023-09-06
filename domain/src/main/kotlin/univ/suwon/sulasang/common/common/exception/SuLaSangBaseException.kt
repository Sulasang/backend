package univ.suwon.sulasang.common.common.exception

import univ.suwon.sulasang.common.common.response.Error
import univ.suwon.sulasang.common.common.response.ErrorResponse

class SuLaSangBaseException(
    val suLaSangErrorCode: SuLaSangErrorCode,
    val resultErrorMessage: String,
    override val cause: Throwable?,
) : RuntimeException(
    suLaSangErrorCode.toString(),
    cause
) {

    fun toErrorResponse(): ErrorResponse {
        return ErrorResponse(
            status = suLaSangErrorCode.httpStatus.value(),
            error = Error(
                code = suLaSangErrorCode.errorCode,
                detailMessage = suLaSangErrorCode.errorMessage
            )
        )
    }

    companion object {
        fun from(
            suLaSangErrorCode: SuLaSangErrorCode,
            resultErrorMessage: String,
            cause: Throwable? = null
        ): SuLaSangBaseException {
            return SuLaSangBaseException(
                suLaSangErrorCode = suLaSangErrorCode,
                resultErrorMessage = resultErrorMessage,
                cause = cause
            )
        }
    }
}
