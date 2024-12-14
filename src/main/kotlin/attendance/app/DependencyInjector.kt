package attendance.app

import attendance.data.AttendancesDataSource
import attendance.data.AttendancesRepositoryImpl
import attendance.domain.repository.AttendancesRepository
import attendance.domain.usecase.GetElapsedTimeUseCase
import attendance.presentation.controller.ViewController
import attendance.presentation.view.InputView
import attendance.presentation.view.OutputView
import attendance.presentation.vm.ViewModel

class DependencyInjector {

    fun injectViewController(): ViewController {
        val inputView = injectInputView()
        val outputView = injectOutPutView()
        val viewModel = injectViewModel()
        return ViewController(inputView, outputView, viewModel)
    }

    private fun injectViewModel(): ViewModel {
        val attendancesRepository = AttendancesRepositoryImpl(
            AttendancesDataSource()
        )
        val getElapsedTimeUseCase = GetElapsedTimeUseCase()
        return ViewModel(
            attendancesRepository,
            getElapsedTimeUseCase
        )
    }

    private fun injectInputView() = InputView()
    private fun injectOutPutView() = OutputView()
}