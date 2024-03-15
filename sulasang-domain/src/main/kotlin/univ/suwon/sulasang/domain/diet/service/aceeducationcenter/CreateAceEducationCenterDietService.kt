package univ.suwon.sulasang.domain.diet.service.aceeducationcenter

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.diet.Diet
import univ.suwon.sulasang.domain.diet.persistence.DietRepository
import univ.suwon.sulasang.domain.diet.persistence.embbeded.Company
import univ.suwon.sulasang.domain.diet.persistence.embbeded.DayOfWeeks
import univ.suwon.sulasang.domain.diet.persistence.enumerated.MealType
import univ.suwon.sulasang.domain.diet.persistence.enumerated.RestaurantType
import java.time.LocalDate

@Transactional
@Service
class CreateAceEducationCenterDietService(
    private val dietRepository: DietRepository
) {

    fun saveAceEducationCenterStudentDiet(
        day: LocalDate,
        dayOfWeeks: DayOfWeeks,
        mainMenu: String,
        commonMenu: String,
        studentDietDatum: Map<String, String>
    ) {
        dietRepository.save(
            Diet(
                day = day,
                dayOfWeek = dayOfWeeks,
                mainMenu = mainMenu,
                commonMenu = commonMenu,
                company = Company(studentDietDatum["company"]!!),
                mealType = MealType.convertByString(studentDietDatum["type"]!!),
                restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
            )
        )
    }

    fun saveAceEducationCenterStaffDiet(
        day: LocalDate,
        dayOfWeeks: DayOfWeeks,
        mainMenu: String,
        staffDietDatum: Map<String, String>
    ) {
        dietRepository.save(
            Diet(
                day = day,
                dayOfWeek = dayOfWeeks,
                mainMenu = mainMenu,
                commonMenu = null,
                company = null,
                mealType = MealType.convertByString(staffDietDatum["type"]!!),
                restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STAFF
            )
        )
    }
}
