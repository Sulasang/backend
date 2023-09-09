package univ.suwon.sulasang.domain.core.diet

import org.springframework.data.jpa.repository.JpaRepository
import univ.suwon.sulasang.domain.enumerated.MealType
import java.time.LocalDate

interface DietRepository : JpaRepository<Diet, Long> {
    fun findDistinctTop20ByOrderByDay(): List<Diet>
    fun findDistinctAllByDayAndMealType(date: LocalDate, type: MealType): List<Diet>
}