package univ.suwon.sulasang.domain.core.staffdiet

import org.springframework.data.jpa.repository.JpaRepository

interface StaffDietRepository : JpaRepository<StaffDiet, Long> {
    fun findTopByOrderByIdDesc(): List<StaffDiet>
}