package attendance.domain.model

enum class Week(val value: String, val order: Int) {
    MonDay("월", 1),
    TUESDAY("화", 2),
    WEDNESDAY("수", 3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6),
    SUNDAY("일", 7);

    companion object {
        fun weeklyList() = listOf(
            MonDay.value,
            TUESDAY.value,
            WEDNESDAY.value,
            THURSDAY.value,
            FRIDAY.value,
            SATURDAY.value,
            SUNDAY.value
        )

        fun from(day: String): Week {
            return when(day){
                MonDay.value -> MonDay
                TUESDAY.value -> TUESDAY
                WEDNESDAY.value -> WEDNESDAY
                THURSDAY.value -> THURSDAY
                FRIDAY.value -> FRIDAY
                SATURDAY.value -> SATURDAY
                else -> SUNDAY
            }
        }

        fun from(day: Int): Week {
            return when(day){
                MonDay.order -> MonDay
                TUESDAY.order -> TUESDAY
                WEDNESDAY.order -> WEDNESDAY
                THURSDAY.order -> THURSDAY
                FRIDAY.order -> FRIDAY
                SATURDAY.order -> SATURDAY
                else -> SUNDAY
            }
        }
    }
}