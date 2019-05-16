package com.kotlin

import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Exam {
    fun hello(tv: TextView)  {
        val job = GlobalScope.launch {
            delay(10000)
            println("world")
        }
        println("hello ")
    }
}