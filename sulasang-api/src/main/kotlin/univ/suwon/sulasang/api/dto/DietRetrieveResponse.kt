package univ.suwon.sulasang.api.dto

import univ.suwon.sulasang.domain.diet.Diet
import univ.suwon.sulasang.domain.diet.persistence.embbeded.DayOfWeeks
import java.time.LocalDate

data class DietRetrieveWeeklyResponse(
    val weeklyDietInfo: List<WeeklyDietInfo>
) {
    companion object {
        fun of(diets: List<Diet>): DietRetrieveWeeklyResponse {
            val formed = diets.map { diet ->
                WeeklyDietInfo(
                    company = diet.restaurantType.name,
                    commonMenu = diet.commonMenu.orEmpty().split(" "),
                    mainMenu = diet.mainMenu.orEmpty().split(" "),
                    day = diet.day,
                    dayOfWeek = diet.dayOfWeek,
                    mealType = diet.mealType.info.orEmpty(),
                    restaurantType = diet.restaurantType.name
                )
            }
            return DietRetrieveWeeklyResponse(formed)
        }
    }
}

data class DietRetrieveDateAndTypeResponse(
    val dateAndTypeDietInfo: List<DateAndTypeDietInfo>
) {
    companion object {
        fun of(diets: List<Diet>): DietRetrieveDateAndTypeResponse {
            val formed = diets.map { diet ->
                DateAndTypeDietInfo(
                    company = diet.restaurantType.name,
                    commonMenu = diet.commonMenu.orEmpty().split(" "),
                    mainMenu = diet.mainMenu.orEmpty().split(" "),
                    mealType = diet.mealType.info.orEmpty(),
                    restaurantType = diet.restaurantType.name
                )
            }
            return DietRetrieveDateAndTypeResponse(formed)
        }
    }
}

data class WeeklyDietInfo(
    val company: String,
    val commonMenu: List<String>,
    val mainMenu: List<String>,
    val day: LocalDate,
    val dayOfWeek: DayOfWeeks,
    val mealType: String,
    val restaurantType: String
)

data class DateAndTypeDietInfo(
    val company: String,
    val commonMenu: List<String>,
    val mainMenu: List<String>,
    val mealType: String,
    val restaurantType: String
)
