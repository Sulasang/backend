package univ.suwon.sulasang.domain.diet.persistence

import org.springframework.data.jpa.repository.JpaRepository
import univ.suwon.sulasang.domain.diet.Diet
import univ.suwon.sulasang.domain.diet.persistence.enumerated.MealType
import java.time.LocalDate

interface DietRepository : JpaRepository<Diet, Long> {
    fun findDistinctTop20ByOrderByDay(): List<Diet>
    fun findDistinctAllByDayAndMealType(date: LocalDate, type: MealType): List<Diet>
}
