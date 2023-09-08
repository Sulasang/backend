package univ.suwon.sulasang.domain.diet

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import univ.suwon.sulasang.common.common.response.ResponseForm
import univ.suwon.sulasang.common.common.response.ResponseForm.Companion.success
import univ.suwon.sulasang.domain.core.diet.service.DietRetrieve
import univ.suwon.sulasang.domain.diet.dto.DietRetrieveDateAndTypeResponse
import univ.suwon.sulasang.domain.diet.dto.DietRetrieveWeeklyResponse
import univ.suwon.sulasang.domain.enumerated.MealType
import java.time.LocalDate

@RestController
@RequestMapping("/v1/diet")
class DietRetrieveApi(
    private val dietRetrieve: DietRetrieve,
) {

    @GetMapping("/weekly")
    fun retrieveWeeklyDiet(): ResponseForm<DietRetrieveWeeklyResponse> {
        return success(
            httpStatus = HttpStatus.OK,
            result = DietRetrieveWeeklyResponse.of(dietRetrieve.executeForWeeklyDiet())
        )
    }

    @GetMapping
    fun retrieveDateDiet(
        @RequestParam date: String,
        @RequestParam type: MealType,
    ): ResponseForm<DietRetrieveDateAndTypeResponse> {
        return success(
            httpStatus = HttpStatus.OK,
            result = DietRetrieveDateAndTypeResponse.of(
                dietRetrieve.executeByDateAndType(
                    date = LocalDate.parse(date),
                    type = type
                )
            )
        )
    }
}