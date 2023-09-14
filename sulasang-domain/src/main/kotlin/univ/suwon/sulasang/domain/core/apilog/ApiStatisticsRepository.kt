package univ.suwon.sulasang.domain.core.apilog

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface ApiStatisticsRepository : JpaRepository<ApiStatistics, Long> {

    fun findByTargetDate(targetDate: LocalDate): ApiStatistics?
}