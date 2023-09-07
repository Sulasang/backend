package univ.suwon.sulasang.domain.staff

import com.mashup.shorts.common.response.ResponseForm
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import univ.suwon.sulasang.domain.core.staffdiet.service.StaffDietRetrieve
import univ.suwon.sulasang.domain.staff.dto.StaffDietRetrieveResponse
import univ.suwon.sulasang.domain.student.dto.StudentDietRetrieveResponse

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