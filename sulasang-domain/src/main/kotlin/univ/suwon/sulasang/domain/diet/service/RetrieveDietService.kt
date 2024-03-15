package univ.suwon.sulasang.domain.diet.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.diet.Diet
import univ.suwon.sulasang.domain.diet.persistence.DietRepository
import univ.suwon.sulasang.domain.diet.persistence.enumerated.MealType
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class RetrieveDietService(
    private val dietRepository: DietRepository
) {

    fun executeForWeeklyDiet() : List<Diet> {
        return dietRepository.findDistinctTop20ByOrderByDay()
    }

    fun executeByDateAndType(date: LocalDate, type: MealType) : List<Diet> {
        return dietRepository.findDistinctAllByDayAndMealType(date, type)
    }
}
