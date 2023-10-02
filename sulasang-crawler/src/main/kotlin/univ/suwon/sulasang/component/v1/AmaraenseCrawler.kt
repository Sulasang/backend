package univ.suwon.sulasang.component.v1

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.core.diet.DietRepository

@Component
class AmaraenseCrawler(
    private val dietRepository: DietRepository,
) {

    @Transactional
    fun execute() {
    }
}