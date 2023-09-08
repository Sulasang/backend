package univ.suwon.sulasang.domain.diet

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import univ.suwon.sulasang.common.common.response.ResponseForm
import univ.suwon.sulasang.common.common.response.ResponseForm.Companion.success
import univ.suwon.sulasang.domain.core.diet.service.DietRetrieve
import univ.suwon.sulasang.domain.diet.dto.DietRetrieveResponse

@RestController
@RequestMapping("/v1/diet")
class DietRetrieveApi(
    private val dietRetrieve: DietRetrieve,
) {

    @GetMapping
    fun retrieveDietTop4(): ResponseForm<DietRetrieveResponse> {
        return success(
            httpStatus = HttpStatus.OK,
            result = DietRetrieveResponse.of(dietRetrieve.executeForTop16())
        )
    }
}