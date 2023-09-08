package univ.suwon.sulasang.domain.core.diet

import org.springframework.data.jpa.repository.JpaRepository

interface DietRepository : JpaRepository<Diet, Long> {
    fun findTop16ByOrderByDay(): List<Diet>
}