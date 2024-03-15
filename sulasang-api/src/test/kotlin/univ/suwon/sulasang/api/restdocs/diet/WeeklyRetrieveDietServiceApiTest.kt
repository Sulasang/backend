package univ.suwon.sulasang.api.restdocs.diet

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import univ.suwon.sulasang.api.DietRetrieveApi
import univ.suwon.sulasang.api.restdocs.ApiDocsTestBase
import univ.suwon.sulasang.api.util.PageHeaderSnippet
import univ.suwon.sulasang.api.util.RestDocsUtils
import univ.suwon.sulasang.domain.diet.Diet
import univ.suwon.sulasang.domain.diet.persistence.embbeded.Company
import univ.suwon.sulasang.domain.diet.persistence.embbeded.DayOfWeeks
import univ.suwon.sulasang.domain.diet.persistence.enumerated.MealType
import univ.suwon.sulasang.domain.diet.persistence.enumerated.RestaurantType
import univ.suwon.sulasang.domain.diet.service.RetrieveDietService
import java.time.LocalDate

@WebMvcTest(DietRetrieveApi::class)
class WeeklyRetrieveDietServiceApiTest : ApiDocsTestBase() {

    @MockkBean
    private lateinit var retrieveDietService: RetrieveDietService

    @Test
    fun `주간 식단 조회`() {
        every { retrieveDietService.executeForWeeklyDiet() } returns (
                listOf(
                    Diet(
                        dayOfWeek = DayOfWeeks.MONDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    ),
                    Diet(
                        dayOfWeek = DayOfWeeks.TUESDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    ),
                    Diet(
                        dayOfWeek = DayOfWeeks.WEDNESDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    ),
                    Diet(
                        dayOfWeek = DayOfWeeks.THURSDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    ),
                    Diet(
                        dayOfWeek = DayOfWeeks.FRIDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    )
                ))

        mockMvc.perform(
            RestDocumentationRequestBuilders.get("/v1/diet/weekly")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "주간 식단 조회",
                    RestDocsUtils.getDocumentRequest(),
                    RestDocsUtils.getDocumentResponse(),
                    PageHeaderSnippet.pageHeaderSnippet(),
                    responseFields(
                        fieldWithPath("status").type(JsonFieldType.NUMBER)
                            .description("API HTTP Status 값"),
                        fieldWithPath("result.weeklyDietInfo").type(JsonFieldType.ARRAY)
                            .description("주간 식단 정보 배열").optional(),
                        fieldWithPath("result.weeklyDietInfo[].company").type(JsonFieldType.STRING)
                            .description("회사명"),
                        fieldWithPath("result.weeklyDietInfo[].commonMenu").type(JsonFieldType.ARRAY)
                            .description("공통 반찬 목록"),
                        fieldWithPath("result.weeklyDietInfo[].mainMenu").type(JsonFieldType.ARRAY)
                            .description("메인 반찬 목록"),
                        fieldWithPath("result.weeklyDietInfo[].day").type(JsonFieldType.STRING)
                            .description("날짜"),
                        fieldWithPath("result.weeklyDietInfo[].dayOfWeek").type(JsonFieldType.STRING)
                            .description("요일"),
                        fieldWithPath("result.weeklyDietInfo[].mealType").type(JsonFieldType.STRING)
                            .description("식사 유형"),
                        fieldWithPath("result.weeklyDietInfo[].restaurantType").type(JsonFieldType.STRING)
                            .description("식당 유형")
                    )
                )
            )
    }
}
