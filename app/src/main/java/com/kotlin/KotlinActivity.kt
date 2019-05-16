package com.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.testing.androidlearn.R
import kotlinx.android.synthetic.main.activity_kot.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kot)
        btn.setOnClickListener {
            Log.e("System.out", "click")
            hello(tv)
            Log.e("System.out", "is after")
        }
    }

    fun hello(tv: TextView)  {
        GlobalScope.launch {
            delay(10000)
            println("world")
            runOnUiThread {
                tv.text = "what a fuck"
            }
        }
        println("hello ")
    }
}


