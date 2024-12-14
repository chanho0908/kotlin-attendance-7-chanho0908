package attendance.presentation.util

fun Int.toFormatterHour(): String = if (this.toString().length == 1) "0$this" else this.toString()
