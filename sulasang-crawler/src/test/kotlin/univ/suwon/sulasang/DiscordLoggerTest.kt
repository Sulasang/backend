package univ.suwon.sulasang

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import univ.suwon.sulasang.component.logger.DiscordLogger

@ActiveProfiles("prod")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DiscordLoggerTest {

    @Autowired
    private lateinit var discordLogger: DiscordLogger

    @Test
    fun test() {
        discordLogger.log("성공 메세지 발송", true)
        discordLogger.log("실패 메세지 발송", false)
    }
}
