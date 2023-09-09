package univ.suwon.sulasang.domain.diet

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import univ.suwon.sulasang.common.common.response.ResponseForm
import univ.suwon.sulasang.common.common.response.ResponseForm.Companion.success
import univ.suwon.sulasang.domain.core.diet.service.DietRetrieve
import univ.suwon.sulasang.domain.diet.dto.DietRetrieveDateAndTypeResponse
import univ.suwon.sulasang.domain.diet.dto.DietRetrieveWeeklyResponse
import univ.suwon.sulasang.domain.enumerated.MealType
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@RestController
@RequestMapping("/v1/diet")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
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
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) date: Instant,
        @RequestParam type: MealType,
    ): ResponseForm<DietRetrieveDateAndTypeResponse> {
        return success(
            httpStatus = HttpStatus.OK,
            result = DietRetrieveDateAndTypeResponse.of(
                dietRetrieve.executeByDateAndType(
                    date = ZonedDateTime.ofInstant(date, ZoneId.of("UTC")).toLocalDate(),
                    type = type
                )
            )
        )
    }
}