package univ.suwon.sulasang.domain.core.diet

import org.springframework.data.jpa.repository.JpaRepository
import univ.suwon.sulasang.domain.enumerated.MealType
import java.time.LocalDate

interface DietRepository : JpaRepository<Diet, Long> {
    fun findTop20ByOrderByDay(): List<Diet>
    fun findAllByDayAndMealType(date: LocalDate, type: MealType): List<Diet>
}