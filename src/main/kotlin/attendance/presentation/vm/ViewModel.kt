package attendance.presentation.vm

import attendance.domain.model.Week
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class ViewModel(
) {


    fun onCompleteInputPurchasePrice(input: String){

    }

    fun getToday(): String {
        val today = "2024년 ${getCurrentDate()} ${getCurrentDayOfWeek()}요일"
        return today
    }

    private fun getCurrentDate(): String {
        val dateNow: Date = Calendar.getInstance().time
        val format = SimpleDateFormat("M월 dd일", Locale.getDefault()) // 2월 15일

        return format.format(dateNow)
    }

    private fun getCurrentDayOfWeek(): String {
        val currentDate = Date()

        val calendar = Calendar.getInstance()
        calendar.time = currentDate

        val dayOfWeekNumber = calendar[Calendar.DAY_OF_WEEK] -1
        val dayOfWeek = Week.from(dayOfWeekNumber).value
        return dayOfWeek
    }
}