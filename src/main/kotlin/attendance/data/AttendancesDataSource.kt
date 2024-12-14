package attendance.data

import attendance.domain.Attendances
import attendance.domain.model.AttendanceType
import java.io.File

class AttendancesDataSource {
    private val baseUrl = "src/main/resources/attendances.csv"

    fun getData() = File(baseUrl).useLines { lines ->
        processLines(lines)
    }

    private fun processLines(lines: Sequence<String>) =
        lines.drop(1)
            .map {
                val spliterator = it.split(",")
                val (date, time) = spliterator[1].split(" ", limit = 2)
                Attendances(
                    nickname = spliterator[0],
                    date = date,
                    time = time,
                    AttendanceType.ATTENDANCE
                )
            }
            .toList()


}

