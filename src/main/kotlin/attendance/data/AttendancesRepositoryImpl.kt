package attendance.data

import attendance.domain.Attendances
import attendance.domain.repository.AttendancesRepository

class AttendancesRepositoryImpl(
    private val dataSource: AttendancesDataSource
): AttendancesRepository {
    override fun getAttendances(): List<Attendances> {
        return dataSource.getData()
    }
}