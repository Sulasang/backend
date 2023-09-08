package univ.suwon.sulasang.domain.student.v2.dto

import univ.suwon.sulasang.domain.core.studentdiet.StudentDiet

data class StudentDietRetrieveResponseV2(
    val link: String = "https://www.suwon.ac.kr/index.html?menuno=762#",
    val studentDiets: List<StudentDiet>
) {
    companion object {
        fun of(studentDiets: List<StudentDiet>): StudentDietRetrieveResponseV2 {
            return StudentDietRetrieveResponseV2(
                link = "https://www.suwon.ac.kr/index.html?menuno=762#",
                studentDiets = studentDiets
            )
        }
    }
}