package attendance.presentation.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readService(): String {
        val input = Console.readLine()
        require(input.isNotBlank()) { throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.") }
        require(input == "1" ||
                input == "2" ||
                input == "3" ||
                input == "4" ||
                input == "Q"
        ){  throw throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.")}
        if (input == "Q") return input
        input.toIntOrNull() ?: throw throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.")
        return input
    }

    fun readName(): String{
        val input = Console.readLine()
        require(input.isNotBlank()) { throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.") }
        return input
    }

    fun readTime(): Pair<Int, Int> {
        val input = Console.readLine()
        require(input.isNotBlank()) { throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.") }
        require(Regex("^[0-9:]+").matches(input)) {
            throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.")
        }
        val (time, minute) = input.split(":").map { it.toInt() }
        require(time in 1..24 && minute in 0..60){
            throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.")
        }
        return time to minute
    }

    fun readModifyDate(): Int{
        val input = Console.readLine()
        require(input.isNotBlank()) { throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.") }
        input.toIntOrNull() ?: { throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.") }
        val date = input.toInt()
        require(date in 1..31){ throw IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.") }
        return date
    }
}