package univ.suwon.sulasang.executor

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import univ.suwon.sulasang.component.logger.DiscordLogger
import univ.suwon.sulasang.component.core.AmarenseCenterCrawler
import java.time.LocalDateTime

@Component
class AmarenceCenterCrawlerExecutor(
    private val amarenseCenterCrawler: AmarenseCenterCrawler,
    private val discordLogger: DiscordLogger
) {

    @Scheduled(cron = "0 0 18 * * SUN")
    fun executeAmarenseCenter() {
        try {
            amarenseCenterCrawler.execute()
            discordLogger.log(
                msg = "${LocalDateTime.now()} - 아마랜스홀 식단 크롤링을 정상적으로 완료했습니다.",
                success = true
            )
        } catch (e: Exception) {
            discordLogger.log(
                msg = "${LocalDateTime.now()} - 아마랜스홀 식단 크롤링 중 다음과 같은 에러가 발생했습니다.${e.localizedMessage}",
                success = false
            )
        }
    }
}
