package univ.suwon.sulasang.component.v1

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.common.common.util.StartEndDateConverter
import univ.suwon.sulasang.component.const.AceEducationCenterConst.COLUMN_DOM
import univ.suwon.sulasang.component.const.AceEducationCenterConst.COMPANY
import univ.suwon.sulasang.component.const.AceEducationCenterConst.FRIDAY
import univ.suwon.sulasang.component.const.AceEducationCenterConst.LOCAL_DATE_DOM
import univ.suwon.sulasang.component.const.AceEducationCenterConst.MONDAY
import univ.suwon.sulasang.component.const.AceEducationCenterConst.ROW_DOM
import univ.suwon.sulasang.component.const.AceEducationCenterConst.TABLE_DOM
import univ.suwon.sulasang.component.const.AceEducationCenterConst.THURSDAY
import univ.suwon.sulasang.component.const.AceEducationCenterConst.TUESDAY
import univ.suwon.sulasang.component.const.AceEducationCenterConst.TYPE
import univ.suwon.sulasang.component.const.AceEducationCenterConst.URL
import univ.suwon.sulasang.component.const.AceEducationCenterConst.WEDNESDAY
import univ.suwon.sulasang.domain.core.diet.Diet
import univ.suwon.sulasang.domain.core.diet.DietRepository
import univ.suwon.sulasang.domain.embbeded.Company
import univ.suwon.sulasang.domain.enumerated.MealType
import univ.suwon.sulasang.domain.enumerated.RestaurantType
import java.time.DayOfWeek
import java.time.LocalDate

@Component
class AceEducationCenterCrawler(
    private val dietRepository: DietRepository,
) {

    @Transactional
    fun execute() {
        val doc = Jsoup.connect(URL).get()
        val mergedLocalDate = extractLocalDate(doc)

        val studentTable = extractStudentDietTable(doc)
        val studentDietData = extractStudentDietData(studentTable)
        saveStudentDiet(mergedLocalDate, studentDietData)

        val staffTable = extractStaffDietTable(doc)
        val staffDietData = extractStaffDietData(staffTable)
        saveStaffDiet(mergedLocalDate, staffDietData)
    }

    private fun saveStudentDiet(
        mergedLocalDate: Pair<LocalDate, LocalDate>,
        studentDietData: MutableList<MutableMap<String, String>>
    ) {
        for (studentDietDatum in studentDietData) {
            val mondayDate = mergedLocalDate.first

            if (studentDietDatum[COMPANY] == "Chef Table") {
                if (studentDietDatum[MONDAY] != null) {
                    insertStudentDietNotContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        MONDAY,
                        studentDietDatum[MONDAY]!!
                    )
                }

                if (studentDietDatum[TUESDAY] != null) {
                    insertStudentDietNotContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        TUESDAY,
                        studentDietDatum[TUESDAY]!!
                    )
                }
                if (studentDietDatum[WEDNESDAY] != null) {
                    insertStudentDietNotContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        WEDNESDAY,
                        studentDietDatum[WEDNESDAY]!!
                    )
                }

                if (studentDietDatum[THURSDAY] != null) {
                    insertStudentDietNotContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        THURSDAY,
                        studentDietDatum[THURSDAY]!!
                    )
                }

                if (studentDietDatum[FRIDAY] != null) {
                    insertStudentDietNotContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        FRIDAY,
                        studentDietDatum[FRIDAY]!!
                    )
                }
            } else {
                if (studentDietDatum[MONDAY] != null) {
                    insertStudentDietContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        MONDAY,
                        splitMainMenuAndCommonMenu(studentDietDatum[MONDAY]!!).first,
                        splitMainMenuAndCommonMenu(studentDietDatum[MONDAY]!!).second
                    )
                }

                if (studentDietDatum[TUESDAY] != null) {
                    insertStudentDietContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        TUESDAY,
                        splitMainMenuAndCommonMenu(studentDietDatum[TUESDAY]!!).first,
                        splitMainMenuAndCommonMenu(studentDietDatum[TUESDAY]!!).second
                    )
                }

                if (studentDietDatum[WEDNESDAY] != null) {
                    insertStudentDietContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        WEDNESDAY,
                        splitMainMenuAndCommonMenu(studentDietDatum[WEDNESDAY]!!).first,
                        splitMainMenuAndCommonMenu(studentDietDatum[WEDNESDAY]!!).second
                    )
                }

                if (studentDietDatum[THURSDAY] != null) {
                    insertStudentDietContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        THURSDAY,
                        splitMainMenuAndCommonMenu(studentDietDatum[THURSDAY]!!).first,
                        splitMainMenuAndCommonMenu(studentDietDatum[THURSDAY]!!).second
                    )
                }

                if (studentDietDatum[FRIDAY] != null) {
                    insertStudentDietContainingCommonMenu(
                        studentDietDatum,
                        mondayDate,
                        FRIDAY,
                        splitMainMenuAndCommonMenu(studentDietDatum[FRIDAY]!!).first,
                        splitMainMenuAndCommonMenu(studentDietDatum[FRIDAY]!!).second
                    )
                }
            }
        }
    }

    private fun insertStudentDietNotContainingCommonMenu(
        studentDietDatum: MutableMap<String, String>,
        mondayDate: LocalDate,
        dayConst: String,
        mainMenu: String,
    ) {

        when (dayConst) {
            MONDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.MONDAY,
                    day = mondayDate,
                    mainMenu = mainMenu,
                    commonMenu = null,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )

            TUESDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.TUESDAY,
                    day = mondayDate.plusDays(1),
                    mainMenu = mainMenu,
                    commonMenu = null,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )

            WEDNESDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.WEDNESDAY,
                    day = mondayDate.plusDays(2),
                    mainMenu = mainMenu,
                    commonMenu = null,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )

            THURSDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.THURSDAY,
                    day = mondayDate.plusDays(3),
                    mainMenu = mainMenu,
                    commonMenu = null,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )

            FRIDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.FRIDAY,
                    day = mondayDate.plusDays(4),
                    mainMenu = mainMenu,
                    commonMenu = null,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )
        }
    }

    private fun insertStudentDietContainingCommonMenu(
        studentDietDatum: MutableMap<String, String>,
        mondayDate: LocalDate,
        dayConst: String,
        mainMenu: String,
        commonMenu: String,
    ) {

        when (dayConst) {
            MONDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.MONDAY,
                    day = mondayDate,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )

            TUESDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.TUESDAY,
                    day = mondayDate.plusDays(1),
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )

            WEDNESDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.WEDNESDAY,
                    day = mondayDate.plusDays(2),
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )

            THURSDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.THURSDAY,
                    day = mondayDate.plusDays(3),
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )

            FRIDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.FRIDAY,
                    day = mondayDate.plusDays(4),
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    mealType = MealType.convertByString(studentDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STUDENT,
                    company = Company(studentDietDatum[COMPANY]!!)
                )
            )
        }
    }

    private fun insertStaffDiet(
        staffDietDatum: MutableMap<String, String>,
        mondayDate: LocalDate,
        dayConst: String,
    ) {

        when (dayConst) {
            MONDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.MONDAY,
                    day = mondayDate,
                    mainMenu = staffDietDatum[MONDAY]!!,
                    commonMenu = null,
                    mealType = MealType.convertByString(staffDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STAFF,
                    company = null
                )
            )

            TUESDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.TUESDAY,
                    day = mondayDate.plusDays(1),
                    mainMenu = staffDietDatum[TUESDAY]!!,
                    commonMenu = null,
                    mealType = MealType.convertByString(staffDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STAFF,
                    company = null
                )
            )

            WEDNESDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.WEDNESDAY,
                    day = mondayDate.plusDays(2),
                    mainMenu = staffDietDatum[WEDNESDAY]!!,
                    commonMenu = null,
                    mealType = MealType.convertByString(staffDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STAFF,
                    company = null
                )
            )

            THURSDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.THURSDAY,
                    day = mondayDate.plusDays(3),
                    mainMenu = staffDietDatum[THURSDAY]!!,
                    commonMenu = null,
                    mealType = MealType.convertByString(staffDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STAFF,
                    company = null
                )
            )

            FRIDAY -> dietRepository.save(
                Diet(
                    dayOfWeek = DayOfWeek.FRIDAY,
                    day = mondayDate.plusDays(4),
                    mainMenu = staffDietDatum[FRIDAY]!!,
                    commonMenu = null,
                    mealType = MealType.convertByString(staffDietDatum[TYPE]!!),
                    restaurantType = RestaurantType.ACE_EDUCATION_CENTER_STAFF,
                    company = null
                )
            )
        }
    }

    private fun saveStaffDiet(
        mergedLocalDate: Pair<LocalDate, LocalDate>,
        staffDietData: MutableList<MutableMap<String, String>>
    ) {
        for (staffDietDataDatum in staffDietData) {
            val mondayDate = mergedLocalDate.first
            if (staffDietDataDatum[MONDAY] != null) {
                insertStaffDiet(
                    staffDietDataDatum,
                    mondayDate,
                    MONDAY,
                )
            }
            if (staffDietDataDatum[TUESDAY] != null) {
                insertStaffDiet(
                    staffDietDataDatum,
                    mondayDate,
                    TUESDAY,
                )
            }
            if (staffDietDataDatum[WEDNESDAY] != null) {
                insertStaffDiet(
                    staffDietDataDatum,
                    mondayDate,
                    WEDNESDAY,
                )
            }
            if (staffDietDataDatum[THURSDAY] != null) {
                insertStaffDiet(
                    staffDietDataDatum,
                    mondayDate,
                    THURSDAY,
                )
            }
            if (staffDietDataDatum[FRIDAY] != null) {
                insertStaffDiet(
                    staffDietDataDatum,
                    mondayDate,
                    FRIDAY,
                )
            }
        }
    }

    private fun splitMainMenuAndCommonMenu(menu: String): Pair<String, String> {
        val temp = menu.split(" (공통메뉴) ")
        val mainMenu = temp[0]
        val commonMenu = temp[1]

        return Pair(mainMenu, commonMenu)
    }

    private fun extractLocalDate(doc: Document): Pair<LocalDate, LocalDate> {
        return StartEndDateConverter.extractWeeks(doc.getElementsByClass(LOCAL_DATE_DOM).text().substring(0, 29))
    }

    private fun extractStaffDietTable(doc: Document) = doc.select(TABLE_DOM).last()

    private fun extractStudentDietTable(doc: Document) = doc.select(TABLE_DOM).first()

    private fun extractStaffDietData(
        staffTable: Element?
    ): MutableList<MutableMap<String, String>> {
        val staffDietData = mutableListOf<MutableMap<String, String>>()
        if (staffTable != null) {
            val rows: Elements = staffTable.select(ROW_DOM)

            for (row in rows) {

                val columns: Elements = row.select(COLUMN_DOM)
                val map = mutableMapOf<String, String>()

                if (columns.isNotEmpty()) {
                    for ((i, column) in columns.withIndex()) {
                        when (i) {
                            0 -> map[TYPE] = column.text()
                            1 -> map[MONDAY] = column.text()
                            2 -> map[TUESDAY] = column.text()
                            3 -> map[WEDNESDAY] = column.text()
                            4 -> map[THURSDAY] = column.text()
                            5 -> map[FRIDAY] = column.text()
                        }
                    }
                    staffDietData.add(map)
                }
            }
        }
        return staffDietData
    }

    private fun extractStudentDietData(
        studentTable: Element?,
    ): MutableList<MutableMap<String, String>> {
        val studentDietData = mutableListOf<MutableMap<String, String>>()
        if (studentTable != null) {
            val rows: Elements = studentTable.select(ROW_DOM)

            for (row in rows) {

                val columns: Elements = row.select(COLUMN_DOM)
                val map = mutableMapOf<String, String>()

                if (columns.isNotEmpty()) {
                    for ((i, column) in columns.withIndex()) {
                        when (i) {
                            0 -> map[TYPE] = column.text()
                            1 -> map[COMPANY] = column.text()
                            2 -> map[MONDAY] = column.text()
                            3 -> map[TUESDAY] = column.text()
                            4 -> map[WEDNESDAY] = column.text()
                            5 -> map[THURSDAY] = column.text()
                            6 -> map[FRIDAY] = column.text()
                        }
                    }
                    studentDietData.add(map)
                }
            }
        }
        return studentDietData
    }


}