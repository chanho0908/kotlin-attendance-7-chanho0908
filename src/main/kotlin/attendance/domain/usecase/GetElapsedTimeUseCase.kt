package attendance.domain.usecase

import attendance.domain.model.AttendanceType
import java.time.LocalTime
import java.time.Duration

class GetElapsedTimeUseCase {
    operator fun invoke(dayOfWeek: String, inputTime: Int, inputMinute: Int): AttendanceType {
        val time = LocalTime.of(inputTime, inputMinute)

        val baseStarTime = getBaseTime(dayOfWeek)

        val elapsed = Duration.between(time, baseStarTime).toSeconds()
        if (elapsed < -300L && elapsed < -1800L) return AttendanceType.TARDY
        if (elapsed > -1800L) return AttendanceType.ABSENCE
        return AttendanceType.ATTENDANCE
    }

    private fun getBaseTime(dayOfWeek: String): LocalTime {
        if (dayOfWeek == "월") return LocalTime.of(13, 0)
        return LocalTime.of(10, 0)
    }
}