package univ.suwon.sulasang.domain.core.diet.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.core.diet.Diet
import univ.suwon.sulasang.domain.core.diet.DietRepository

@Service
@Transactional(readOnly = true)
class DietRetrieve(
    private val dietRepository: DietRepository
) {

    fun executeForTop16() : List<Diet> {
        return dietRepository.findTop16ByOrderByDay()
    }
}