package univ.suwon.sulasang.common.common.response

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseForm<T>(
    val status: Int,

    val result: T? = null,
) {
    companion object {

        fun <T> success(httpStatus: HttpStatus): ResponseForm<T> {
            return ResponseForm(httpStatus.value(), null)
        }

        fun <T> success(httpStatus: HttpStatus, result: T) = ResponseForm(httpStatus.value(), result)
    }
}
