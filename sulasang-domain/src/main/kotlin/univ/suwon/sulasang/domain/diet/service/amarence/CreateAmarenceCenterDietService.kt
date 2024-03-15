package univ.suwon.sulasang.domain.diet.service.amarence

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
class CreateAmarenceCenterDietService(
    private val dietRepository: DietRepository
) {

    fun saveAmarenceCenterStudentDiet(
        day: LocalDate,
        dayOfWeeks: DayOfWeeks,
        mainMenu: String,
        studentDietDatum: Map<String, String>
    ) {
        dietRepository.save(
            Diet(
                day = day,
                dayOfWeek = dayOfWeeks,
                mainMenu = mainMenu,
                commonMenu = "",
                company = Company(studentDietDatum["company"]!!),
                mealType = MealType.convertByString(studentDietDatum["type"]!!),
                restaurantType = RestaurantType.AMARAENSE_STUDENT
            )
        )
    }
}
