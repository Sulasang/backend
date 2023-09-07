package univ.suwon.sulasang.domain.student

import com.mashup.shorts.common.response.ResponseForm
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import univ.suwon.sulasang.domain.core.studentdiet.service.StudentDietRetrieve
import univ.suwon.sulasang.domain.student.dto.StudentDietRetrieveResponse

@RestController
@RequestMapping("/v1/student-diet")
class StudentDietRetrieveApi(
    private val studentDietRetrieve: StudentDietRetrieve
) {

    @GetMapping
    fun retrieveStudentDiet(): ResponseForm<StudentDietRetrieveResponse> {
        return ResponseForm.success(
            httpStatus = HttpStatus.OK,
            result = StudentDietRetrieveResponse.of(
                studentDietRetrieve.execute()
            )
        )
    }
}