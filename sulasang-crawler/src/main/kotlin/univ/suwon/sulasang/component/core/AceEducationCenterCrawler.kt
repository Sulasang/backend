package univ.suwon.sulasang.component.core

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.common.common.util.StartEndDateConverter
import univ.suwon.sulasang.component.consts.AceEducationCenterConst.COLUMN_DOM
import univ.suwon.sulasang.component.consts.AceEducationCenterConst.LOCAL_DATE_DOM
import univ.suwon.sulasang.component.consts.AceEducationCenterConst.ROW_DOM
import univ.suwon.sulasang.component.consts.AceEducationCenterConst.TABLE_DOM
import univ.suwon.sulasang.component.consts.AceEducationCenterConst.URL
import univ.suwon.sulasang.domain.diet.persistence.embbeded.DayOfWeeks
import univ.suwon.sulasang.domain.diet.service.aceeducationcenter.CreateAceEducationCenterDietService
import univ.suwon.sulasang.domain.diet.service.aceeducationcenter.CreateAceEducationCenterHolidayDietService
import java.time.LocalDate

@Component
class AceEducationCenterCrawler(
    private val createAceEducationCenterHolidayDietService: CreateAceEducationCenterHolidayDietService,
    private val createAceEducationCenterDietService: CreateAceEducationCenterDietService,
) {

    @Transactional
    fun execute() {
        val doc = Jsoup.connect(URL).get()
        val mergedLocalDate = extractLocalDate(doc)

        saveStudentDietsByDocAndLocalDate(doc, mergedLocalDate)
        saveStaffDietsByDocAndLocalDate(doc, mergedLocalDate)
    }

    private fun saveStudentDietsByDocAndLocalDate(
        doc: Document,
        mergedLocalDate: Pair<LocalDate, LocalDate>
    ) {
        val studentTable = extractStudentDietTable(doc)
        val studentDietData = extractStudentDietData(studentTable)
        saveStudentDiets(mergedLocalDate, studentDietData)
    }

    private fun saveStaffDietsByDocAndLocalDate(
        doc: Document,
        mergedLocalDate: Pair<LocalDate, LocalDate>
    ) {
        val staffTable = extractStaffDietTable(doc)
        val staffDietData = extractStaffDietData(staffTable)
        saveStaffDiets(mergedLocalDate, staffDietData)
    }

    private fun saveStudentDiets(
        mergedLocalDate: Pair<LocalDate, LocalDate>,
        studentDietData: List<Map<String, String>>
    ) {
        for (studentDietDatum in studentDietData) {
            val mondayDate = mergedLocalDate.first

            if (isNotHoliday(studentDietDatum, MONDAY)) {
                val mainMenu = splitMainMenuAndCommonMenu(studentDietDatum[MONDAY]!!).first
                val commonMenu = splitMainMenuAndCommonMenu(studentDietDatum[MONDAY]!!).second

                createAceEducationCenterDietService.saveAceEducationCenterStudentDiet(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStudent(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    studentDietDatum = studentDietDatum
                )
            }

            if (isNotHoliday(studentDietDatum, TUESDAY)) {
                val mainMenu = splitMainMenuAndCommonMenu(studentDietDatum[TUESDAY]!!).first
                val commonMenu = splitMainMenuAndCommonMenu(studentDietDatum[TUESDAY]!!).second

                createAceEducationCenterDietService.saveAceEducationCenterStudentDiet(
                    day = mondayDate.plusDays(1),
                    dayOfWeeks = DayOfWeeks.TUESDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStudent(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    studentDietDatum = studentDietDatum
                )
            }

            if (isNotHoliday(studentDietDatum, WEDNESDAY)) {
                val mainMenu = splitMainMenuAndCommonMenu(studentDietDatum[WEDNESDAY]!!).first
                val commonMenu = splitMainMenuAndCommonMenu(studentDietDatum[WEDNESDAY]!!).second

                createAceEducationCenterDietService.saveAceEducationCenterStudentDiet(
                    day = mondayDate.plusDays(2),
                    dayOfWeeks = DayOfWeeks.WEDNESDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStudent(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    studentDietDatum = studentDietDatum
                )
            }

            if (isNotHoliday(studentDietDatum, THURSDAY)) {
                val mainMenu = splitMainMenuAndCommonMenu(studentDietDatum[THURSDAY]!!).first
                val commonMenu = splitMainMenuAndCommonMenu(studentDietDatum[THURSDAY]!!).second

                createAceEducationCenterDietService.saveAceEducationCenterStudentDiet(
                    day = mondayDate.plusDays(3),
                    dayOfWeeks = DayOfWeeks.THURSDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStudent(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    studentDietDatum = studentDietDatum
                )
            }

            if (isNotHoliday(studentDietDatum, FRIDAY)) {
                val mainMenu = splitMainMenuAndCommonMenu(studentDietDatum[FRIDAY]!!).first
                val commonMenu = splitMainMenuAndCommonMenu(studentDietDatum[FRIDAY]!!).second

                createAceEducationCenterDietService.saveAceEducationCenterStudentDiet(
                    day = mondayDate.plusDays(4),
                    dayOfWeeks = DayOfWeeks.FRIDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStudent(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    studentDietDatum = studentDietDatum
                )
            }
        }
    }

    private fun saveStaffDiets(
        mergedLocalDate: Pair<LocalDate, LocalDate>,
        staffDietData: MutableList<MutableMap<String, String>>
    ) {
        for (staffDietDatum in staffDietData) {
            val mondayDate = mergedLocalDate.first


            if (isNotHoliday(staffDietDatum, MONDAY)) {
                createAceEducationCenterDietService.saveAceEducationCenterStaffDiet(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    mainMenu = staffDietDatum[MONDAY]!!,
                    staffDietDatum = staffDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStaff(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    staffDietDatum = staffDietDatum
                )
            }

            if (isNotHoliday(staffDietDatum, TUESDAY)) {
                createAceEducationCenterDietService.saveAceEducationCenterStaffDiet(
                    day = mondayDate.plusDays(1),
                    dayOfWeeks = DayOfWeeks.TUESDAY,
                    mainMenu = staffDietDatum[TUESDAY]!!,
                    staffDietDatum = staffDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStaff(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.TUESDAY,
                    staffDietDatum = staffDietDatum
                )
            }

            if (isNotHoliday(staffDietDatum, WEDNESDAY)) {
                createAceEducationCenterDietService.saveAceEducationCenterStaffDiet(
                    day = mondayDate.plusDays(2),
                    dayOfWeeks = DayOfWeeks.WEDNESDAY,
                    mainMenu = staffDietDatum[WEDNESDAY]!!,
                    staffDietDatum = staffDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStaff(
                    day = mondayDate.plusDays(2),
                    dayOfWeeks = DayOfWeeks.WEDNESDAY,
                    staffDietDatum = staffDietDatum
                )
            }

            if (isNotHoliday(staffDietDatum, THURSDAY)) {
                createAceEducationCenterDietService.saveAceEducationCenterStaffDiet(
                    day = mondayDate.plusDays(3),
                    dayOfWeeks = DayOfWeeks.THURSDAY,
                    mainMenu = staffDietDatum[THURSDAY]!!,
                    staffDietDatum = staffDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStaff(
                    day = mondayDate.plusDays(3),
                    dayOfWeeks = DayOfWeeks.THURSDAY,
                    staffDietDatum = staffDietDatum
                )
            }

            if (isNotHoliday(staffDietDatum, FRIDAY)) {
                createAceEducationCenterDietService.saveAceEducationCenterStaffDiet(
                    day = mondayDate.plusDays(4),
                    dayOfWeeks = DayOfWeeks.FRIDAY,
                    mainMenu = staffDietDatum[FRIDAY]!!,
                    staffDietDatum = staffDietDatum
                )
            } else {
                createAceEducationCenterHolidayDietService.saveHolidayAceEducationCenterStaff(
                    day = mondayDate.plusDays(4),
                    dayOfWeeks = DayOfWeeks.FRIDAY,
                    staffDietDatum = staffDietDatum
                )
            }
        }
    }

    private fun isNotHoliday(dietDatum: Map<String, String>, dayOfWeeks: String) = dietDatum[dayOfWeeks] != EMPTY_MENU

    private fun splitMainMenuAndCommonMenu(menu: String): Pair<String, String> {
        return if (menu == EMPTY_MENU) {
            Pair("", "")
        } else {
            val temp = menu.split(" 백미밥 ")
            val mainMenu = temp[0]
            val commonMenu = temp[1]
            Pair(mainMenu, commonMenu)
        }
    }

    private fun extractLocalDate(doc: Document): Pair<LocalDate, LocalDate> {
        return StartEndDateConverter.extractWeeks(doc.getElementsByClass(LOCAL_DATE_DOM).text().substring(0, 29))
    }

    private fun extractStaffDietTable(doc: Document) = doc.select(TABLE_DOM).last()

    private fun extractStudentDietTable(doc: Document) = doc.select(TABLE_DOM).first()

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
                        val text = if (column.text().length <= 10 && i != 0) "" else column.text()
                        when (i) {
                            0 -> map[TYPE] = text
                            1 -> map[COMPANY] = text
                            2 -> map[MONDAY] = text
                            3 -> map[TUESDAY] = text
                            4 -> map[WEDNESDAY] = text
                            5 -> map[THURSDAY] = text
                            6 -> map[FRIDAY] = text
                        }
                    }
                    studentDietData.add(map)
                }
            }
        }
        return studentDietData
    }

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
                        val text = if (column.text().length <= 10 && i != 0) "" else column.text()
                        when (i) {
                            0 -> map[TYPE] = text
                            1 -> map[MONDAY] = text
                            2 -> map[TUESDAY] = text
                            3 -> map[WEDNESDAY] = text
                            4 -> map[THURSDAY] = text
                            5 -> map[FRIDAY] = text
                        }
                    }
                    staffDietData.add(map)
                }
            }
        }
        return staffDietData
    }

    companion object {
        private const val EMPTY_MENU = ""

        internal const val TYPE = "type"
        internal const val COMPANY = "company"

        private const val MONDAY = "monday"
        private const val TUESDAY = "tuesday"
        private const val WEDNESDAY = "wednesday"
        private const val THURSDAY = "thursday"
        private const val FRIDAY = "friday"
    }
}
