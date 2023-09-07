package univ.suwon.sulasang.core

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.stereotype.Component
import univ.suwon.sulasang.common.common.util.StartEndDateConverter
import univ.suwon.sulasang.domain.core.staffdiet.StaffDiet
import univ.suwon.sulasang.domain.core.staffdiet.StaffDietRepository
import univ.suwon.sulasang.domain.core.studentdiet.StudentDiet
import univ.suwon.sulasang.domain.embbeded.Company
import univ.suwon.sulasang.domain.enumerated.Type.Companion.convertByString
import java.time.LocalDate

@Component
class CrawlerCore(
    private val studentDietRepository: univ.suwon.sulasang.domain.core.studentdiet.StudentDietRepository,
    private val staffDietRepository: StaffDietRepository,
) {

    fun execute() {
        val doc = Jsoup.connect(URL).get()
        val mergedLocalDate = extractLocalDate(doc)
        val studentTable = extractStudentDietTable(doc)
        val staffTable = extractStaffDietTable(doc)
        val studentDietData = extractStudentDietData(studentTable)
        val staffDietData = extractStaffDietData(staffTable)

        for (studentDietDatum in studentDietData) {
            studentDietRepository.save(
                StudentDiet(
                    type = convertByString(studentDietDatum[TYPE]!!),
                    company = Company(studentDietDatum[COMPANY]!!),
                    mondayMenu = studentDietDatum[MONDAY],
                    tuesdayMenu = studentDietDatum[TUESDAY],
                    wednesdayMenu = studentDietDatum[WEDNESDAY],
                    thursdayMenu = studentDietDatum[THURSDAY],
                    fridayMenu = studentDietDatum[FRIDAY],
                    startDate = mergedLocalDate.first,
                    endDate = mergedLocalDate.second
                )
            )
        }

        for (staffDietDatum in staffDietData) {
            staffDietRepository.save(
                StaffDiet(
                    type = convertByString(staffDietDatum[TYPE]!!),
                    mondayMenu = staffDietDatum[MONDAY],
                    tuesdayMenu = staffDietDatum[TUESDAY],
                    wednesdayMenu = staffDietDatum[WEDNESDAY],
                    thursdayMenu = staffDietDatum[THURSDAY],
                    fridayMenu = staffDietDatum[FRIDAY],
                    startDate = mergedLocalDate.first,
                    endDate = mergedLocalDate.second
                )
            )
        }
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

    companion object {
        private const val URL = "https://www.suwon.ac.kr/index.html?menuno=762"
        private const val TABLE_DOM = "table"
        private const val ROW_DOM = "tr"
        private const val COLUMN_DOM = "td"
        private const val LOCAL_DATE_DOM = "fl tp10"
        private const val TYPE = "type"
        private const val COMPANY = "company"
        private const val MONDAY = "monday"
        private const val TUESDAY = "tuesday"
        private const val WEDNESDAY = "wednesday"
        private const val THURSDAY = "thursday"
        private const val FRIDAY = "friday"
    }
}
