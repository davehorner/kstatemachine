package com.stickman

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun CoroutineScope.singleShotTimer(timeoutMillis: Long, block: suspend () -> Unit) = launch {
    delay(timeoutMillis)
    block()
}

fun tickerFlow(delayMillis: Long) = flow {
    while (true) {
        emit(Unit)
        delay(delayMillis)
    }
}