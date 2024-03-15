package univ.suwon.sulasang.domain.diet.service.amarence

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.diet.Diet
import univ.suwon.sulasang.domain.diet.persistence.DietRepository
import univ.suwon.sulasang.domain.diet.persistence.embbeded.DayOfWeeks
import univ.suwon.sulasang.domain.diet.persistence.enumerated.MealType
import univ.suwon.sulasang.domain.diet.persistence.enumerated.RestaurantType
import java.time.LocalDate

@Transactional
@Service
class CreateAmarenceCenterHolidayDietService(
    private val dietRepository: DietRepository
) {

    fun saveHolidayAmarenceCenterStudent(
        dayOfWeeks: DayOfWeeks,
        day: LocalDate,
        studentDietDatum: Map<String, String>,
    ) {
        dietRepository.save(
            Diet(
                dayOfWeek = dayOfWeeks,
                day = day,
                mainMenu = null,
                commonMenu = null,
                company = null,
                mealType = MealType.convertByString(studentDietDatum["type"]!!),
                restaurantType = RestaurantType.AMARAENSE_STUDENT,
            )
        )
    }
}
