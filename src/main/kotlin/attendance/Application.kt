package attendance

import attendance.app.DependencyInjector

fun main() {
    val di = DependencyInjector()
    val controller = di.injectViewController()
}
