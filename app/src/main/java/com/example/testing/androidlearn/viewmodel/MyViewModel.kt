package com.example.testing.androidlearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel(private val pre:Int) : ViewModel() {
    var value:MutableLiveData<String> = MutableLiveData()

    fun setContent( str:String) {
        value.value = pre.toString() + "abc" + str
    }
}
