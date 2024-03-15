package univ.suwon.sulasang.domain.apilog

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import java.time.LocalDate

interface ApiStatisticsRepository : JpaRepository<ApiStatistics, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByTargetDate(targetDate: LocalDate): ApiStatistics?
}
