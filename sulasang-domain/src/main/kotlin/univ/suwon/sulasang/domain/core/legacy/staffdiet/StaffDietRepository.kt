package univ.suwon.sulasang.domain.core.legacy.staffdiet

import org.springframework.data.jpa.repository.JpaRepository

interface StaffDietRepository : JpaRepository<StaffDiet, Long> {
    fun findTopByOrderByIdDesc(): List<StaffDiet>
}