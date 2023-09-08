package univ.suwon.sulasang.domain.diet.dto

import univ.suwon.sulasang.domain.core.diet.Diet
import java.time.DayOfWeek
import java.time.LocalDate

data class DietRetrieveResponse(
    val dietInfo: List<DietInfo>
) {
    companion object {
        fun of(diets: List<Diet>): DietRetrieveResponse {
            val formed = diets.map { diet ->
                DietInfo(
                    company = diet.company?.companyName.orEmpty(),
                    commonMenu = diet.commonMenu.orEmpty().split(" "),
                    mainMenu = diet.mainMenu.orEmpty().split(" "),
                    day = diet.day,
                    dayOfWeek = diet.dayOfWeek,
                    mealType = diet.mealType.info.orEmpty(),
                    restaurantType = diet.restaurantType.name
                )
            }
            return DietRetrieveResponse(formed)
        }
    }
}

data class DietInfo(
    val company: String,
    val commonMenu: List<String>,
    val mainMenu: List<String>,
    val day: LocalDate,
    val dayOfWeek: DayOfWeek,
    val mealType: String,
    val restaurantType: String
)
