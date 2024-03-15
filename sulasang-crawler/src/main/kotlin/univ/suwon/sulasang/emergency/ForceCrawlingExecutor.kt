package univ.suwon.sulasang.emergency

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import univ.suwon.sulasang.component.core.AceEducationCenterCrawler
import univ.suwon.sulasang.component.core.AmarenseCenterCrawler

@RestController
@RequestMapping("/v1/force/update")
class ForceCrawlingExecutor(
    private val aceEducationCenterCrawler: AceEducationCenterCrawler,
    private val amarenseCenterCrawler: AmarenseCenterCrawler
) {

    @GetMapping("/aceeducationcenter")
    fun forceUpdateAceEducationCenterDiet() {
        aceEducationCenterCrawler.execute()
    }

    @GetMapping("/amarensecenter")
    fun forceUpdateAmarenseCenterDiet() {
        amarenseCenterCrawler.execute()
    }
}
