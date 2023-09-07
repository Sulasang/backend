package univ.suwon.sulasang.domain.core.staffdiet.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.core.staffdiet.StaffDiet
import univ.suwon.sulasang.domain.core.staffdiet.StaffDietRepository

@Service
@Transactional(readOnly = true)
class StaffDietRetrieve(
    private val staffDietRepository: StaffDietRepository,
) {

    fun execute(): List<StaffDiet> {
        return staffDietRepository.findTopByOrderByIdDesc()
    }
}