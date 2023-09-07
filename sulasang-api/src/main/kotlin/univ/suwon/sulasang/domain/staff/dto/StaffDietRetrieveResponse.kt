package univ.suwon.sulasang.domain.staff.dto

import univ.suwon.sulasang.domain.core.staffdiet.StaffDiet

data class StaffDietRetrieveResponse(
    val link: String,
    val days: Days
) {
    companion object {
        fun of(staffDiets: List<StaffDiet>): StaffDietRetrieveResponse {
            return StaffDietRetrieveResponse(
                link = "https://www.suwon.ac.kr/index.html?menuno=762#",
                Days(
                    monday = staffDiets.map { it.mondayMenu },
                    tuesday = staffDiets.map { it.tuesdayMenu },
                    wednesday = staffDiets.map { it.wednesdayMenu },
                    thursday = staffDiets.map { it.thursdayMenu },
                    friday = staffDiets.map { it.fridayMenu },
                )
            )
        }
    }
}

data class Days(
    val monday: List<String?>,
    val tuesday: List<String?>,
    val wednesday: List<String?>,
    val thursday: List<String?>,
    val friday: List<String?>,
)