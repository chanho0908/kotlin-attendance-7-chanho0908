package attendance.domain.repository

import attendance.domain.Attendances

interface AttendancesRepository {
    fun getAttendances(): List<Attendances>
}