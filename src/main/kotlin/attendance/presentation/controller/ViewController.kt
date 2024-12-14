package attendance.presentation.controller

import attendance.presentation.view.InputView
import attendance.presentation.view.OutputView
import attendance.presentation.vm.ViewModel

class ViewController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val viewModel: ViewModel
) {
    fun run(){
        while (true) {
            displayGuideMessage()
            val service = inputService()
            if (service == "Q") break
            if (service == "1"){

            }
            if (service == "2"){

            }
            if (service == "3"){

            }
            if (service == "4") {

            }
        }
    }

    private fun displayGuideMessage(){
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