package univ.suwon.sulasang.domain.core.legacy.studentdiet.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.domain.core.legacy.studentdiet.StudentDiet
import univ.suwon.sulasang.domain.core.legacy.studentdiet.StudentDietRepository

@Service
@Transactional(readOnly = true)
class StudentDietRetrieve(
    private val studentDietRepository: StudentDietRepository,
) {

    fun execute(): List<StudentDiet> {
        return studentDietRepository.findTop3ByOrderByIdDesc()
    }
}