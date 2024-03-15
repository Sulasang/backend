package univ.suwon.sulasang.domain.diet.service.aceeducationcenter

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
class CreateAceEducationCenterHolidayDietService(
    private val dietRepository: DietRepository
) {

    fun saveHolidayAceEducationCenterStudent(
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
                mealType = MealType.convertByString(studentDietDatum["type"]!!),
                restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                company = null
            )
        )
    }

    fun saveHolidayAceEducationCenterStaff(
        dayOfWeeks: DayOfWeeks,
        day: LocalDate,
        staffDietDatum: Map<String, String>,
    ) {
        dietRepository.save(
            Diet(
                dayOfWeek = dayOfWeeks,
                day = day,
                mainMenu = null,
                commonMenu = null,
                mealType = MealType.convertByString(staffDietDatum["type"]!!),
                restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STAFF,
                company = null
            )
        )
    }
}
