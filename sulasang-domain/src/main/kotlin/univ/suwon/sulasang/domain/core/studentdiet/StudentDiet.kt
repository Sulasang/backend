package univ.suwon.sulasang.domain.core.studentdiet

import jakarta.persistence.*
import univ.suwon.sulasang.domain.BaseEntity
import univ.suwon.sulasang.domain.embbeded.Company
import univ.suwon.sulasang.domain.enumerated.MealType
import java.time.LocalDate

@Entity
@Table(name = "student_diet")
class StudentDiet(

    @Enumerated(EnumType.STRING)
    val mealType: MealType?,

    @Embedded
    val company: Company?,

    @Column
    val mondayMenu: String = "",

    @Column
    val tuesdayMenu: String? = "",

    @Column
    val wednesdayMenu: String? = "",

    @Column
    val thursdayMenu: String? = "",

    @Column
    val fridayMenu: String? = "",

    @Column
    val startDate: LocalDate,

    @Column
    val endDate: LocalDate,
) : BaseEntity() {
}