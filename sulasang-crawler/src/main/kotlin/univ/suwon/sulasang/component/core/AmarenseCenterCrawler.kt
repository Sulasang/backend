package univ.suwon.sulasang.component.core

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import univ.suwon.sulasang.common.common.util.StartEndDateConverter
import univ.suwon.sulasang.component.consts.AceEducationCenterConst
import univ.suwon.sulasang.component.consts.AmarenseCenterConst
import univ.suwon.sulasang.domain.diet.persistence.embbeded.DayOfWeeks
import univ.suwon.sulasang.domain.diet.service.amarence.CreateAmarenceCenterDietService
import univ.suwon.sulasang.domain.diet.service.amarence.CreateAmarenceCenterHolidayDietService
import java.time.LocalDate

@Component
class AmarenseCenterCrawler(
    private val createAmarenceCenterDietService: CreateAmarenceCenterDietService,
    private val createAmarenceCenterHolidayDietService: CreateAmarenceCenterHolidayDietService
) {

    @Transactional
    fun execute() {
        val doc = Jsoup.connect(AmarenseCenterConst.URL).get()
        val mergedLocalDate = extractLocalDate(doc)

        saveStudentDietsByDocAndLocalDate(doc, mergedLocalDate)
    }

    private fun saveStudentDietsByDocAndLocalDate(
        doc: Document,
        mergedLocalDate: Pair<LocalDate, LocalDate>
    ) {
        val studentTable = extractStudentDietTable(doc)
        val studentDietData = extractStudentDietData(studentTable)
        saveStudentDiets(mergedLocalDate, studentDietData)
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

                createAmarenceCenterDietService.saveAmarenceCenterStudentDiet(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAmarenceCenterHolidayDietService.saveHolidayAmarenceCenterStudent(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.MONDAY,
                    studentDietDatum = studentDietDatum
                )
            }

            if (isNotHoliday(studentDietDatum, TUESDAY)) {
                val mainMenu = splitMainMenuAndCommonMenu(studentDietDatum[TUESDAY]!!).first
                val commonMenu = splitMainMenuAndCommonMenu(studentDietDatum[TUESDAY]!!).second

                createAmarenceCenterDietService.saveAmarenceCenterStudentDiet(
                    day = mondayDate.plusDays(1),
                    dayOfWeeks = DayOfWeeks.TUESDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAmarenceCenterHolidayDietService.saveHolidayAmarenceCenterStudent(
                    day = mondayDate.plusDays(1),
                    dayOfWeeks = DayOfWeeks.TUESDAY,
                    studentDietDatum = studentDietDatum
                )
            }

            if (isNotHoliday(studentDietDatum, WEDNESDAY)) {
                val mainMenu = splitMainMenuAndCommonMenu(studentDietDatum[WEDNESDAY]!!).first
                val commonMenu = splitMainMenuAndCommonMenu(studentDietDatum[WEDNESDAY]!!).second

                createAmarenceCenterDietService.saveAmarenceCenterStudentDiet(
                    day = mondayDate.plusDays(2),
                    dayOfWeeks = DayOfWeeks.WEDNESDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAmarenceCenterHolidayDietService.saveHolidayAmarenceCenterStudent(
                    day = mondayDate.plusDays(2),
                    dayOfWeeks = DayOfWeeks.WEDNESDAY,
                    studentDietDatum = studentDietDatum
                )
            }

            if (isNotHoliday(studentDietDatum, THURSDAY)) {
                val mainMenu = splitMainMenuAndCommonMenu(studentDietDatum[THURSDAY]!!).first
                val commonMenu = splitMainMenuAndCommonMenu(studentDietDatum[THURSDAY]!!).second

                createAmarenceCenterDietService.saveAmarenceCenterStudentDiet(
                    day = mondayDate.plusDays(3),
                    dayOfWeeks = DayOfWeeks.THURSDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAmarenceCenterHolidayDietService.saveHolidayAmarenceCenterStudent(
                    day = mondayDate.plusDays(3),
                    dayOfWeeks = DayOfWeeks.THURSDAY,
                    studentDietDatum = studentDietDatum
                )
            }

            if (isNotHoliday(studentDietDatum, FRIDAY)) {
                val mainMenu = splitMainMenuAndCommonMenu(studentDietDatum[THURSDAY]!!).first
                val commonMenu = splitMainMenuAndCommonMenu(studentDietDatum[THURSDAY]!!).second

                createAmarenceCenterDietService.saveAmarenceCenterStudentDiet(
                    day = mondayDate.plusDays(4),
                    dayOfWeeks = DayOfWeeks.FRIDAY,
                    mainMenu = mainMenu,
                    commonMenu = commonMenu,
                    studentDietDatum = studentDietDatum
                )
            } else {
                createAmarenceCenterHolidayDietService.saveHolidayAmarenceCenterStudent(
                    day = mondayDate,
                    dayOfWeeks = DayOfWeeks.FRIDAY,
                    studentDietDatum = studentDietDatum
                )
            }
        }
    }

    private fun splitMainMenuAndCommonMenu(menu: String): Pair<String, String> {
        return if (menu == EMPTY_MENU) {
            Pair(EMPTY_MENU, EMPTY_MENU)
        } else {
            val lines = menu.split(" ")

            val mainMenuStartIndex = lines.indexOf("[선택메뉴]") + 1
            val commonMenuStartIndex = lines.indexOf("[공통찬]") + 1

            val mainMenuLines = lines.subList(mainMenuStartIndex, commonMenuStartIndex - 1)
            val commonMenuLines = lines.subList(commonMenuStartIndex, lines.size)

            val mainMenuString = mainMenuLines.joinToString("\n")
            val commonMenuString = commonMenuLines.joinToString("\n")

            Pair(mainMenuString, commonMenuString)
        }
    }

    private fun isNotHoliday(dietDatum: Map<String, String>, dayOfWeeks: String) = dietDatum[dayOfWeeks] != EMPTY_MENU

    private fun extractLocalDate(doc: Document): Pair<LocalDate, LocalDate> {
        return StartEndDateConverter.extractWeeks(
            doc.getElementsByClass(AceEducationCenterConst.LOCAL_DATE_DOM).text().substring(0, 29)
        )
    }

    private fun extractStudentDietTable(doc: Document) = doc.select(AceEducationCenterConst.TABLE_DOM).first()

    private fun extractStudentDietData(
        studentTable: Element?,
    ): MutableList<MutableMap<String, String>> {
        val studentDietData = mutableListOf<MutableMap<String, String>>()
        if (studentTable != null) {
            val rows: Elements = studentTable.select(AceEducationCenterConst.ROW_DOM)

            for (row in rows) {

                val columns: Elements = row.select(AceEducationCenterConst.COLUMN_DOM)
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

