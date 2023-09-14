package univ.suwon.sulasang.domain.core.apilog

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import univ.suwon.sulasang.domain.BaseEntity
import java.time.LocalDate

@Entity
@Table(name = "api_statistics")
class ApiStatistics(

    @Column(name = "target_date")
    val targetDate: LocalDate,

    @Column(name = "day_and_type_retrieve_api_call_count")
    var dayAndTypeRetrieveApiCallCount: Int,

    @Column(name = "day_and_type_retrieve_api_process_time_avg")
    var dayAndTypeRetrieveApiProcessTimeAvg: Long = 1L
) : BaseEntity() {

    fun increaseDayAndTypeRetrieveApiCallCount() {
        this.dayAndTypeRetrieveApiCallCount++
    }

    fun calculateProcessTimeAvg(processTime: Long) {
        if (this.dayAndTypeRetrieveApiCallCount > 0) {
            val currentProcessTime = this.dayAndTypeRetrieveApiProcessTimeAvg * this.dayAndTypeRetrieveApiCallCount
            val totalProcessTime = currentProcessTime + processTime
            this.dayAndTypeRetrieveApiProcessTimeAvg = totalProcessTime / this.dayAndTypeRetrieveApiCallCount
        }
    }
}