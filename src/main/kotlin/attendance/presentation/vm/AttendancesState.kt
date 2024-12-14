package attendance.presentation.vm

import attendance.domain.Attendances

data class AttendancesState (
    val attendances: List<Attendances>
){
    companion object{
        fun create(): AttendancesState{
            return AttendancesState(
                emptyList()
            )
        }
    }
}