package de.appsfactory.myflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

object Model {

    val scope = CoroutineScope(Job())

    private var counter = 0
    val sharedFlow = MutableSharedFlow<Int>(replay = 1)

    val simpleFlow = flowOf(1001, 1002, 1003)

    fun onCounter() {
        scope.launch {
            sharedFlow.emit(++counter)
        }
    }
}