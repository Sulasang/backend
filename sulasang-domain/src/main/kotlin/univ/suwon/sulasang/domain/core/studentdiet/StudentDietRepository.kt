package univ.suwon.sulasang.domain.core.studentdiet

import org.springframework.data.jpa.repository.JpaRepository


interface StudentDietRepository : JpaRepository<StudentDiet, Long> {
    fun findTop3ByOrderByIdDesc(): List<StudentDiet>
}