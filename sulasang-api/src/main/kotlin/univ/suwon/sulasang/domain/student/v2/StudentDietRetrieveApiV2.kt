package univ.suwon.sulasang.domain.student.v2

import univ.suwon.sulasang.common.common.response.ResponseForm
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import univ.suwon.sulasang.domain.core.studentdiet.service.StudentDietRetrieve
import univ.suwon.sulasang.domain.student.v2.dto.StudentDietRetrieveResponseV2

@RestController
@RequestMapping("/v2/student-diet")
class StudentDietRetrieveApiV2(
    private val studentDietRetrieve: StudentDietRetrieve
) {

    @GetMapping
    fun retrieveStudentDiet(): ResponseForm<StudentDietRetrieveResponseV2> {
        return ResponseForm.success(
            httpStatus = HttpStatus.OK,
            result = StudentDietRetrieveResponseV2.of(
                studentDietRetrieve.execute()
            )
        )
    }
}