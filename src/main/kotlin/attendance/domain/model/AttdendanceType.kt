package attendance.domain.model

enum class AttendanceType(private val value: String) {
    TARDY("지각"), ABSENCE("결석"), ATTENDANCE("출석"), ;

    override fun toString(): String = value
}