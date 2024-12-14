package attendance.presentation.controller

import attendance.presentation.view.InputView
import attendance.presentation.view.OutputView
import attendance.presentation.vm.ViewModel

class ViewController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val viewModel: ViewModel
) {
    fun run() {
            displayGuideMessage()
            val service = inputService()
            if (service == "Q") return
            if (service == "1") {
                val currentDayOfWeek = viewModel.getCurrentDayOfWeek()
                val date = viewModel.getOnlyCurrentDate()
                if (currentDayOfWeek == "토" || currentDayOfWeek == "일" || date == "25") {
                    val today = viewModel.getCurrentDate()
                    throw IllegalArgumentException(
                        "[ERROR] $today ${viewModel.getCurrentDayOfWeek()}요일은 등교일이 아닙니다."
                    )
                }

                val time = viewModel.getCurrentTime()
                if (time[0].toString() == "0" && time[1].digitToInt() < 8){
                    throw IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.")
                }

                outputView.printMessage("닉네임을 입력해 주세요.")
                val name = inputView.readName()
                viewModel.onCompleteInputName(name)
                outputView.printMessage("등교 시간을 입력해 주세요.")
                val requestTime = inputView.readTime()
                val result = viewModel.onCompeteInputTime(name, requestTime)
                outputView.printMessage(result)
            }
            if (service == "2") {
                outputView.printMessage("출석을 수정하려는 크루의 닉네임을 입력해 주세요.")
                val input = inputView.readName()
                viewModel.onCompleteInputNameModifyAttendances(input)
                outputView.printMessage("수정하려는 날짜(일)를 입력해 주세요.")
                val date = inputView.readModifyDate()
                outputView.printMessage("언제로 변경하겠습니까?")
                val modifyTime = inputView.readTime()
                val result = viewModel.onCompleteInputDateModifyAttendances(input, date, modifyTime)
                outputView.printMessage(result)
            }
            if (service == "3") {

            }
            if (service == "4") {

            }
        run()
    }

    private fun displayGuideMessage() {
        val today = viewModel.getToday()
        outputView.printMessage("오늘은 ${today}입니다. 기능을 선택해 주세요.")
        outputView.printMessage("1. 출석 확인")
        outputView.printMessage("2. 출석 수정")
        outputView.printMessage("3. 크루별 출석 기록 확인")
        outputView.printMessage("4. 제적 위험자 확인")
        outputView.printMessage("Q. 종료")
    }

    private fun inputService(): String {
        val service = inputView.readService()
        return service
    }
}

//오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
//1. 출석 확인
//2. 출석 수정
//3. 크루별 출석 기록 확인
//4. 제적 위험자 확인
//Q. 종료
//1