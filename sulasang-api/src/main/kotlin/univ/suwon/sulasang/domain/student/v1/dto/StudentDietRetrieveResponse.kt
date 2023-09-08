package univ.suwon.sulasang.domain.student.v1.dto

import univ.suwon.sulasang.domain.core.studentdiet.StudentDiet

data class StudentDietRetrieveResponse(
    val link: String = "https://www.suwon.ac.kr/index.html?menuno=762#",
    val days: Days? = null
) {
    companion object {
        fun of(studentDiets: List<StudentDiet>): StudentDietRetrieveResponse {
            return StudentDietRetrieveResponse(
                link = "https://www.suwon.ac.kr/index.html?menuno=762#",
                Days(
                    monday = studentDiets.map { it.mondayMenu },
                    tuesday = studentDiets.map { it.tuesdayMenu },
                    wednesday = studentDiets.map { it.wednesdayMenu },
                    thursday = studentDiets.map { it.thursdayMenu },
                    friday = studentDiets.map { it.fridayMenu },
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