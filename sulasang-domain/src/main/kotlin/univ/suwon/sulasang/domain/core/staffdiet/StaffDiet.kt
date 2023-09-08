package univ.suwon.sulasang.domain.core.staffdiet

import jakarta.persistence.*
import univ.suwon.sulasang.domain.BaseEntity
import univ.suwon.sulasang.domain.enumerated.MealType
import java.time.LocalDate

@Entity
@Table(name = "staff_diet")
class StaffDiet(
    @Enumerated(EnumType.STRING)
    val mealType: MealType?,

    @Column
    val mondayMenu: String? = "",

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