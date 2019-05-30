package com.kotlin

import android.provider.Settings
import kotlinx.coroutines.*

open class Coroutines : CoroutineScope by MainScope(){
    fun test() = launch {
        var a = async {
            println("launch start")
            delay(10000L)
            Thread.currentThread().name
        }
        println("Hello " + Thread.currentThread().name + a.await())

    }

    fun coroutineScope() = runBlocking {
        launch {
            delay(2000L)
            println("Task from runBlocking")
        }
        async {  }
        println("coroutine scope is over 1")
        kotlinx.coroutines.coroutineScope {
            launch {
                delay(3000L)
                println("Task from nested launch")
            }
            delay(2000)
            println("Task from coroutine scope")
        }
        println("coroutine scope is over 2")
    }
}