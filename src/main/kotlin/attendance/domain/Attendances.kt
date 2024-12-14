package attendance.domain

import attendance.domain.model.AttendanceType

data class Attendances(
    val nickname: String,
    val date: String,
    val time: String,
    val type: AttendanceType
)