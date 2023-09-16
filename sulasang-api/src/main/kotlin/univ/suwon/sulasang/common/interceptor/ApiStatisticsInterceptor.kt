package univ.suwon.sulasang.common.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import univ.suwon.sulasang.common.annotation.ApiStatistics
import univ.suwon.sulasang.domain.core.apilog.service.ApiStatisticsLogger
import java.time.Duration
import java.time.LocalDateTime

class ApiStatisticsInterceptor(
    private val apiStatisticsLogger: ApiStatisticsLogger,
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        if (handler is HandlerMethod) {
            val method = handler.method
            val apiLoggerAnnotation = AnnotationUtils.findAnnotation(
                method,
                ApiStatistics::class.java
            )
            if (apiLoggerAnnotation != null) {
                val startTime = LocalDateTime.now()
                request.setAttribute("startTime", startTime)
            }
        }
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        if (handler is HandlerMethod) {
            val method = handler.method
            val apiLoggerAnnotation = AnnotationUtils.findAnnotation(
                method,
                ApiStatistics::class.java
            )

            if (apiLoggerAnnotation != null) {
                val endTime = LocalDateTime.now()
                val startTime = request.getAttribute("startTime") as LocalDateTime
                val duration = Duration.between(startTime, endTime).toMillis()

                apiStatisticsLogger.execute(duration)
            }
        }
    }
}