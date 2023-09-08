package univ.suwon.sulasang.domain.core.legacy.staffdiet.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.core.legacy.staffdiet.StaffDiet
import univ.suwon.sulasang.domain.core.legacy.staffdiet.StaffDietRepository

@Service
@Transactional(readOnly = true)
class StaffDietRetrieve(
    private val staffDietRepository: StaffDietRepository,
) {

    fun execute(): List<StaffDiet> {
        return staffDietRepository.findTopByOrderByIdDesc()
    }
}