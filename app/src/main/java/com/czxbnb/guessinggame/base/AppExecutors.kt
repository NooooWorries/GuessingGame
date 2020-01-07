package com.czxbnb.guessinggame.base


import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

open class AppExecutors(
    val diskIO: Executor,
    val networkIO: Executor,
    val computeThread: Executor,
    val mainThread: Executor
) {
    constructor() : this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(3),
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1),
        object : Executor {
            val mainThreadHandler = Handler(Looper.getMainLooper())
            override fun execute(command: Runnable) {
                mainThreadHandler.post(command)
            }
        }
    )
}