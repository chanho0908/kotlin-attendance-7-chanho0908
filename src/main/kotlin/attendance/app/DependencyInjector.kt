package attendance.app

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
        return ViewModel()
    }

    private fun injectInputView() = InputView()
    private fun injectOutPutView() = OutputView()
}