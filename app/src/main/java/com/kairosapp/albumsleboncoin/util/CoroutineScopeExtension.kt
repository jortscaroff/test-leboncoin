package com.kairosapp.albumsleboncoin.util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchCoroutine(
    block: suspend CoroutineScope.() -> Unit,
    onError: (suspend CoroutineScope.(Throwable) -> Unit)? = null
): Job {
    return launch {
        try {
            block()
        } catch (e: Throwable) {
            onError?.invoke(this, e)
        }
    }
}