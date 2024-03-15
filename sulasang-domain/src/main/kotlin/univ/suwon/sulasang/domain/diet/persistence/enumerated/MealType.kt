package univ.suwon.sulasang.domain.diet.persistence.enumerated

import univ.suwon.sulasang.common.common.exception.SuLaSangBaseException
import univ.suwon.sulasang.common.common.exception.SuLaSangErrorCode

enum class MealType(
    val info: String?
) {
    MORNING("조식"), LUNCH("중식"), DINNER("석식");

    companion object {
        fun convertByString(value: String): MealType {
            return when (value) {
                "조식" -> MORNING
                "중식" -> LUNCH
                "석식" -> DINNER
                else -> {
                    throw SuLaSangBaseException.from(
                        suLaSangErrorCode = SuLaSangErrorCode.E500_INTERNAL_SERVER_ERROR,
                        resultErrorMessage = "크롤링 중 조식, 중식, 석식을 구분하는 로직에서 예기치 못한 에러가 발생했습니다."
                    )
                }
            }
        }
    }

}
