package attendance.presentation.vm

import attendance.domain.Attendances
import java.text.SimpleDateFormat
import java.util.Date

data class AttendancesState (
    val history: List<Attendances>
){

    fun findHistoryForModifyDate(nickName: String, date: Int): Attendances {
        val history = history.find { it.nickname == nickName &&
                it.date.split("-").last().toInt() == date
        } ?: throw IllegalArgumentException("[ERROR] 기록을 찾을 수 없습니다.")
        return history
    }

    fun compareTime(nickName: String){
        history.find { it.nickname == nickName }?.let { student ->
            val dateFormat = "MM-dd"
            val date = Date(System.currentTimeMillis())
            val simpleDateFormat = SimpleDateFormat(dateFormat)

            val simpleDate: String = simpleDateFormat.format(date)

            if(student.time == simpleDate){
                throw IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.")
            }
        }
    }

    companion object{
        fun create(): AttendancesState{
            return AttendancesState(
                emptyList()
            )
        }
    }
}