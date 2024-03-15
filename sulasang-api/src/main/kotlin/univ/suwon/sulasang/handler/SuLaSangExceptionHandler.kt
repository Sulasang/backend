package univ.suwon.sulasang.handler

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.mashup.shorts.common.util.Slf4j2KotlinLogging.log
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import univ.suwon.sulasang.common.common.exception.SuLaSangBaseException
import univ.suwon.sulasang.common.common.exception.SuLaSangErrorCode
import univ.suwon.sulasang.common.common.exception.SuLaSangErrorCode.E400_BAD_REQUEST
import univ.suwon.sulasang.common.common.exception.SuLaSangErrorCode.E500_INTERNAL_SERVER_ERROR
import univ.suwon.sulasang.common.common.response.ErrorResponse

@RestControllerAdvice
class SuLaSangExceptionHandler {

    @ExceptionHandler(ConstraintViolationException::class)
    private fun handlerConstraintViolationException(
        exception: ConstraintViolationException,
    ): ErrorResponse {
        log.warn(exception.message)
        return ErrorResponse.of(
            suLaSangErrorCode = E400_BAD_REQUEST,
            detailMessage = E400_BAD_REQUEST.errorMessage
        )
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    private fun handleMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException,
    ): ErrorResponse {
        log.warn(exception.message)
        return ErrorResponse.of(
            suLaSangErrorCode = E400_BAD_REQUEST,
            detailMessage = E400_BAD_REQUEST.errorMessage
        )
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    private fun handleMissingServletRequestParameterException(
        exception: MissingServletRequestParameterException,
    ): ErrorResponse {
        log.warn(exception.message)
        return ErrorResponse.of(
            suLaSangErrorCode = E400_BAD_REQUEST,
            detailMessage = E400_BAD_REQUEST.errorMessage
        )
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    private fun httpRequestMethodNotSupportedException(exception: HttpRequestMethodNotSupportedException): ErrorResponse {
        log.warn(exception.message, exception)
        return ErrorResponse.of(SuLaSangErrorCode.E405_METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    private fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ErrorResponse {
        log.warn(e.message)
        if (e.rootCause is MissingKotlinParameterException) {
            val parameterName = (e.rootCause as MissingKotlinParameterException).parameter.name
            return ErrorResponse.of(E400_BAD_REQUEST, "Parameter ($parameterName) is Missing")
        }
        return ErrorResponse.of(E400_BAD_REQUEST)
    }

    @ExceptionHandler(SuLaSangBaseException::class)
    private fun handleBaseException(exception: SuLaSangBaseException): ResponseEntity<ErrorResponse> {
        log.error(exception.resultErrorMessage, exception)
        return ResponseEntity.status(exception.suLaSangErrorCode.httpStatus)
            .body(exception.toErrorResponse())
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    private fun handleInternalServerException(exception: Exception): ErrorResponse {
        log.error(exception.message, exception)
        return ErrorResponse.of(E500_INTERNAL_SERVER_ERROR)
    }
}
