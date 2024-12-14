package attendance.presentation.view

import camp.nextstep.edu.missionutils.Console.readLine

class InputView {
    fun readService(): String {
        val input = readLine()
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


}