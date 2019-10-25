package com.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.testing.androidlearn.R
import kotlinx.android.synthetic.main.activity_kot.*
import kotlinx.coroutines.*

class KotlinActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kot)
        btn.setOnClickListener {
//            Log.e("System.out", "click")
//            hello(tv)
//            Log.e("System.out", "is after")
//
//            var exam = Exam("abc")

//            var p = ExtendPerson("abcdefg")
//            p.hello()

//            var cor : Coroutines = Coroutines()
//            cor.test()
//
//            println("test end")
//            cor.coroutineScope()
//            println("cor end")

            // 实际运行
//            GlobalScope.launch(Dispatchers.IO) {
//                Log.d("AA", "协程测试 开始执行，线程：${Thread.currentThread().name}")
//                var token = GlobalScope.async(Dispatchers.IO) {
//                    return@async getToken()
//                }.await()
//
//                var response = GlobalScope.async(Dispatchers.IO) {
//                    return@async getResponse(token)
//                }.await()
//
//                setText(response)
//            }
            Coroutines.a = 10
            click()
        }
    }

    private fun click() {
        GlobalScope.launch(Dispatchers.Main) {
            tv.text = GlobalScope.async(Dispatchers.IO) {
                // 比如进行了网络请求
                // 放回了请求后的结构
                delay(10000)
                return@async "main"
            }.await()
        }
    }

    suspend fun getToken(): String {
        Log.d("AA", "getToken start，线程：${Thread.currentThread().name}")
        delay(100)
        Log.d("AA", "getToken end，线程：${Thread.currentThread().name}")
        return "ask"
    }

    suspend fun getResponse(token: String): String {
        Log.d("AA", "getResponse start，线程：${Thread.currentThread().name}")
        delay(200)
        Log.d("AA", "getResponse end，线程：${Thread.currentThread().name}")
        return "response"
    }

    fun setText(response: String) {
        Log.d("AA", "setText 执行，线程：${Thread.currentThread().name}")
    }



    fun hello(tv: TextView)  {
        GlobalScope.launch {
            delay(10000)
            println(Thread.currentThread().name + "world")
            runOnUiThread {
                tv.text = "what a fuck"
            }
        }
        println("hello ")
    }
}


