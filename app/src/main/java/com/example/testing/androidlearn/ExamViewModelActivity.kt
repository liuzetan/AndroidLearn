package com.example.testing.androidlearn

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import android.os.Message
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.testing.androidlearn.viewmodel.MyViewModel
import com.example.testing.androidlearn.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_exam_view_model.*

class ExamViewModelActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    var handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            viewModel.setContent("test ${msg!!.what}  " + Math.random())
        }
    }

    public fun f1() {
        viewModel.value.observe(this, Observer {
            tv_test.text = it
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_view_model)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(50)).get(MyViewModel::class.java)
        f1()

        Thread(Runnable {
//            var i: Int = 0
            for (i in 1..20) {
                Thread.sleep(2000)
                handler.sendEmptyMessage(i)
            }
        }).start()
    }
}
