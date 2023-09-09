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
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import univ.suwon.sulasang.api.restdocs.ApiDocsTestBase
import univ.suwon.sulasang.api.util.PageHeaderSnippet
import univ.suwon.sulasang.api.util.RestDocsUtils
import univ.suwon.sulasang.domain.core.diet.Diet
import univ.suwon.sulasang.domain.core.diet.service.DietRetrieve
import univ.suwon.sulasang.domain.diet.DietRetrieveApi
import univ.suwon.sulasang.domain.embbeded.Company
import univ.suwon.sulasang.domain.enumerated.MealType
import univ.suwon.sulasang.domain.enumerated.RestaurantType
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate

@WebMvcTest(DietRetrieveApi::class)
class DateAndTypeDietRetrieveApiTest : ApiDocsTestBase() {

    @MockkBean
    private lateinit var dietRetrieve: DietRetrieve

    @Test
    fun `주간 식단 조회`() {
        every {
            dietRetrieve.executeByDateAndType(
                date = any(),
                type = any()
            )
        } returns (
                listOf(
                    Diet(
                        dayOfWeek = DayOfWeek.MONDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    ),
                    Diet(
                        dayOfWeek = DayOfWeek.TUESDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    ),
                    Diet(
                        dayOfWeek = DayOfWeek.WEDNESDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    ),
                    Diet(
                        dayOfWeek = DayOfWeek.THURSDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    ),
                    Diet(
                        dayOfWeek = DayOfWeek.FRIDAY,
                        day = LocalDate.now(),
                        mainMenu = "메인반찬1 메인반찬2 메인반찬3",
                        commonMenu = "공통반찬1 공통반찬2 공통반찬3",
                        company = Company("Little Kitchen"),
                        mealType = MealType.LUNCH,
                        restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT
                    )
                ))

        mockMvc.perform(
            RestDocumentationRequestBuilders.get("/v1/diet")
                .param("date", Instant.now().toString())
                .param("type", MealType.LUNCH.toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "날짜, 식사 타입으로 식단 조회",
                    RestDocsUtils.getDocumentRequest(),
                    RestDocsUtils.getDocumentResponse(),
                    RequestDocumentation.queryParameters(
                        RequestDocumentation
                            .parameterWithName("date")
                            .description("2023-09-08T10:55:54.301Z (iSO 표준 형태)"),
                        RequestDocumentation
                            .parameterWithName("type")
                            .description("식단 유형 (MORNING, LUNCH, DINNER)")
                    ),
                    PageHeaderSnippet.pageHeaderSnippet(),
                    responseFields(
                        fieldWithPath("status").type(JsonFieldType.NUMBER)
                            .description("API HTTP Status 값"),
                        fieldWithPath("result.dateAndTypeDietInfo").type(JsonFieldType.ARRAY)
                            .description("날짜와 식사 유형별 식단 정보 배열").optional(),
                        fieldWithPath("result.dateAndTypeDietInfo[].company").type(JsonFieldType.STRING)
                            .description("회사명 : [Little Kitchen, Mom's Cook, Chef Table, \"\"]"),
                        fieldWithPath("result.dateAndTypeDietInfo[].commonMenu").type(JsonFieldType.ARRAY)
                            .description("공통 반찬 목록"),
                        fieldWithPath("result.dateAndTypeDietInfo[].mainMenu").type(JsonFieldType.ARRAY)
                            .description("메인 반찬 목록"),
                        fieldWithPath("result.dateAndTypeDietInfo[].mealType").type(JsonFieldType.STRING)
                            .description("식사 유형 : [MORNING, LUNCH, DINNER]"),
                        fieldWithPath("result.dateAndTypeDietInfo[].restaurantType").type(JsonFieldType.STRING)
                            .description("식당 유형 : [ACE_EDUCATION_CENTER_STUDENT, ACE_EDUCATION_CENTER_STAFF, AMARAENSE_STUDENT, AMARAENSE_STAFF]")
                    )
                )
            )
    }
}