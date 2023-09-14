package univ.suwon.sulasang.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import univ.suwon.sulasang.common.interceptor.ApiStatisticsInterceptor
import univ.suwon.sulasang.domain.core.apilog.service.ApiStatisticsLogger

@Configuration
class WebMvcConfig(
    private val apiStatisticsLogger: ApiStatisticsLogger
) : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("/sulasang/**")
            .addResourceLocations("classpath:/static/")
    }

    override fun addInterceptors(interceptorRegistry: InterceptorRegistry) {
        interceptorRegistry.addInterceptor(ApiStatisticsInterceptor(apiStatisticsLogger))
            .addPathPatterns("/v1/*")
    }
}