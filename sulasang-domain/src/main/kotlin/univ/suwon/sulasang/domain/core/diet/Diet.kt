package univ.suwon.sulasang.domain.core.diet

import jakarta.persistence.*
import univ.suwon.sulasang.domain.BaseEntity
import univ.suwon.sulasang.domain.embbeded.Company
import univ.suwon.sulasang.domain.enumerated.MealType
import univ.suwon.sulasang.domain.enumerated.RestaurantType
import java.time.DayOfWeek
import java.time.LocalDate

@Entity
@Table(name = "diet")
class Diet(

    @Column(name = "day_of_week", nullable = false)
    val dayOfWeek: DayOfWeek,

    @Column(name = "day", nullable = false)
    val day: LocalDate,

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