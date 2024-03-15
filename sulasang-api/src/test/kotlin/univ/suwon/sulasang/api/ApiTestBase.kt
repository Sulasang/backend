package univ.suwon.sulasang.api

import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestPropertySource
import univ.suwon.sulasang.domain.apilog.service.ApiStatisticsLogger

@TestPropertySource(properties = ["spring.config.location=classpath:/"])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
abstract class ApiTestBase {

    @MockBean
    lateinit var apiStatisticsLogger: ApiStatisticsLogger
}
