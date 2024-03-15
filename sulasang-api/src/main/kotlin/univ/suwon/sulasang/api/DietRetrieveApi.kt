package univ.suwon.sulasang.api

import org.springframework.cache.annotation.Cacheable
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import univ.suwon.sulasang.annotation.ApiStatistics
import univ.suwon.sulasang.common.common.response.ResponseForm
import univ.suwon.sulasang.common.common.response.ResponseForm.Companion.success
import univ.suwon.sulasang.domain.diet.service.RetrieveDietService
import univ.suwon.sulasang.api.dto.DietRetrieveDateAndTypeResponse
import univ.suwon.sulasang.api.dto.DietRetrieveWeeklyResponse
import univ.suwon.sulasang.domain.diet.persistence.enumerated.MealType
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@RestController
@RequestMapping("/v1/diet")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class DietRetrieveApi(
    private val retrieveDietService: RetrieveDietService,
) {

    @Cacheable(cacheNames = ["weekly-diet"])
    @GetMapping("/weekly")
    fun retrieveWeeklyDiet(): ResponseForm<DietRetrieveWeeklyResponse> {
        return success(
            httpStatus = HttpStatus.OK,
            result = DietRetrieveWeeklyResponse.of(retrieveDietService.executeForWeeklyDiet())
        )
    }

    @ApiStatistics
    @Cacheable(cacheNames = ["date-type-diet"])
    @GetMapping
    fun retrieveDateDiet(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) date: Instant,
        @RequestParam type: MealType,
    ): ResponseForm<DietRetrieveDateAndTypeResponse> {
        return success(
            httpStatus = HttpStatus.OK,
            result = DietRetrieveDateAndTypeResponse.of(
                retrieveDietService.executeByDateAndType(
                    date = ZonedDateTime.ofInstant(date, ZoneId.of("UTC")).toLocalDate(),
                    type = type
                )
            )
        )
    }
}
