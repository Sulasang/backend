package univ.suwon.sulasang.domain.legacy.staff

import univ.suwon.sulasang.common.common.response.ResponseForm
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import univ.suwon.sulasang.domain.core.legacy.staffdiet.service.StaffDietRetrieve
import univ.suwon.sulasang.domain.legacy.staff.dto.StaffDietRetrieveResponse

@RestController
@RequestMapping("/v1/staff-diet")
class StaffDietRetrieveApi(
    private val staffDietRetrieve: StaffDietRetrieve
) {

    @GetMapping
    fun retrieveStaffDiet(): ResponseForm<StaffDietRetrieveResponse> {
        return ResponseForm.success(
            httpStatus = HttpStatus.OK,
            result = StaffDietRetrieveResponse.of(
                staffDietRetrieve.execute()
            )
        )
    }
}