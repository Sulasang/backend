package univ.suwon.sulasang.domain.apilog.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.apilog.ApiStatistics
import univ.suwon.sulasang.domain.apilog.ApiStatisticsRepository
import java.time.LocalDate

@Service
@Transactional
class ApiStatisticsLogger(
    private val apiStatisticsRepository: ApiStatisticsRepository
) {

    fun execute(processTime: Long) {
        val nowDate = LocalDate.now()
        val apiStatistics = apiStatisticsRepository.findByTargetDate(nowDate)

        if (apiStatistics != null) {
            apiStatistics.increaseDayAndTypeRetrieveApiCallCount()
            apiStatistics.calculateProcessTimeAvg(processTime)
            return
        }

        val newApiStatistics = ApiStatistics(
            targetDate = nowDate,
            dayAndTypeRetrieveApiCallCount = 1,
            dayAndTypeRetrieveApiProcessTimeAvg = 0L
        )
        newApiStatistics.calculateProcessTimeAvg(processTime)
        apiStatisticsRepository.save(newApiStatistics)
    }
}
