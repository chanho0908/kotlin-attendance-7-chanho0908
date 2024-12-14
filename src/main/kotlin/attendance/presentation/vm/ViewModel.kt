package attendance.presentation.vm

import attendance.domain.Attendances
import attendance.domain.model.AttendanceType
import attendance.domain.model.Week
import attendance.domain.repository.AttendancesRepository
import attendance.domain.usecase.GetElapsedTimeUseCase
import attendance.presentation.util.toFormatterHour
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ViewModel(
    private val attendancesRepository: AttendancesRepository,
    private val getElapsedTimeUseCase: GetElapsedTimeUseCase
) {
    private var _state = AttendancesState.create()
    val state get() = _state

    init {
        load()
    }

    private fun load() {
        val history = attendancesRepository.getAttendances()
        _state = _state.copy(history = history)
        _state = AttendancesState(
            history = _state.history.map {
                val weekOfDay = getSpecificDateOfWeek(it.date.split("-").last())
                val (time, minute) = it.time.split(":").map { item -> item.toInt() }
                val type = getElapsedTimeUseCase(weekOfDay, time, minute)
                it.copy(type = type)
            }
        )
    }

    fun onCompleteInputName(input: String) {
        require(_state.history.any { it.nickname == input }) {
            throw IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.")
        }
        _state.compareTime(input)
    }

    fun onCompleteInputNameModifyAttendances(name: String) {
        require(_state.history.any { it.nickname == name }) {
            throw IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.")
        }
    }

    fun onCompleteInputDateModifyAttendances(
        nickName: String,
        date: Int,
        modifyTime: Pair<Int, Int>
    ): String {
        var target = Attendances("", "", "", AttendanceType.ATTENDANCE)
        var beforeWeekOfDay: String
        var modifiedType = AttendanceType.ATTENDANCE
        _state = _state.copy(
            history = _state.history.map {
                if (it.nickname == nickName && it.date.split("-").last().toInt() == date) {
                    val beforeDayOfWeek = it.date.split("-").last()
                    // 변경하기 전 요일
                    beforeWeekOfDay = getSpecificDateOfWeek(beforeDayOfWeek)

                    val modifyHour = modifyTime.first
                    val modifySecond = modifyTime.second
                    modifiedType = getElapsedTimeUseCase(beforeWeekOfDay, modifyHour, modifySecond)
                    target = it
                    Attendances(
                        nickname = nickName,
                        date = "2024-12-$date",
                        time = "${modifyTime.first.toFormatterHour()}:${modifyTime.second.toFormatterHour()}",
                        type = modifiedType
                    )
                } else it
            }
        )

        val beforeTime = target.time.split(":").map { it.toInt() }
        return "12월 ${target.date.split("-").last()}일 " +
                "${beforeTime[0].toFormatterHour()}:${beforeTime[1].toFormatterHour()} (${target.type}) -> " +
                "${modifyTime.first.toFormatterHour()}:${modifyTime.second.toFormatterHour()} ($modifiedType) 수정 완료!"
    }

    fun onCompeteInputTime(name: String, input: Pair<Int, Int>): String {
        val dayOfWeek = getCurrentDayOfWeek()
        val result = getElapsedTimeUseCase(dayOfWeek, input.first, input.second)
        val inputTime = "${input.first.toFormatterHour()}:${input.second.toFormatterHour()}"

        val newHistory = _state.history.toMutableList()
        newHistory.add(
            Attendances(
                nickname = name,
                date = getFullDate(),
                time = inputTime,
                type = result
            )
        )
        _state = _state.copy(history = newHistory)
        return "${getCurrentDate()} ${getCurrentDayOfWeek()}요일 $inputTime ($result)"
    }

    fun getToday(): String {
        val today = "2024년 ${getCurrentDate()} ${getCurrentDayOfWeek()}요일"
        return today
    }

    fun getCurrentDate(): String {
        val dateNow: Date = Calendar.getInstance().time
        val format = SimpleDateFormat("M월 dd일", Locale.getDefault())

        return format.format(dateNow)
    }

    fun getOnlyCurrentDate(): String {
        val dateNow: Date = Calendar.getInstance().time
        val format = SimpleDateFormat("dd", Locale.getDefault())

        return format.format(dateNow)
    }

    fun getCurrentDayOfWeek(): String {
        val currentDate = Date()

        val calendar = Calendar.getInstance()
        calendar.time = currentDate

        val dayOfWeekNumber = calendar[Calendar.DAY_OF_WEEK] - 1
        val dayOfWeek = Week.from(dayOfWeekNumber).value
        return dayOfWeek
    }

    fun getCurrentTime(): String {
        System.currentTimeMillis()
        val dateFormat = "HH"
        val date = Date(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat(dateFormat)

        val simpleDate: String = simpleDateFormat.format(date)
        return simpleDate
    }

    fun getFullDate(): String {
        val dateNow: Date = Calendar.getInstance().time
        val format = SimpleDateFormat("2024-MM-dd", Locale.getDefault())
        return format.format(dateNow) ?: ""
    }

    private fun getSpecificDateOfWeek(dateOfWeek: String): String {
        val localDate = LocalDate.of(2024, 12, dateOfWeek.toInt());
        val dayOfWeek = localDate.dayOfWeek - 1
        val dayOfWeekNumber = dayOfWeek.value
        return Week.from(dayOfWeekNumber).value
    }
}