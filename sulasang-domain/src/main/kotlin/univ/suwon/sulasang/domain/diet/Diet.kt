package univ.suwon.sulasang.domain.diet

import jakarta.persistence.*
import univ.suwon.sulasang.domain.BaseEntity
import univ.suwon.sulasang.domain.diet.persistence.embbeded.Company
import univ.suwon.sulasang.domain.diet.persistence.embbeded.DayOfWeeks
import univ.suwon.sulasang.domain.diet.persistence.enumerated.MealType
import univ.suwon.sulasang.domain.diet.persistence.enumerated.RestaurantType
import java.time.LocalDate

@Entity
@Table(name = "diet")
class Diet(

    @Column(name = "day", nullable = false)
    val day: LocalDate,

    @Enumerated(EnumType.STRING)
    val dayOfWeek: DayOfWeeks,

    @Column(name = "main_menu", nullable = true)
    val mainMenu: String?,

    @Column(name = "common_menu", nullable = true)
    val commonMenu: String?,

    @Embedded
    val company: Company?,

    @Enumerated(EnumType.STRING)
    val mealType: MealType,

    @Enumerated(EnumType.STRING)
    val restaurantType: RestaurantType

) : BaseEntity()
