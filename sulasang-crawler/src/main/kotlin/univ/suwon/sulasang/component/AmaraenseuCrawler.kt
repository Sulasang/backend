package univ.suwon.sulasang.component

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.core.diet.DietRepository

@Component
class AmaraenseCrawler(
    private val dietRepository: DietRepository,
) {

    @Transactional
    @Scheduled(cron = "0 0 9 * * MON")
    fun execute() {
    }
}