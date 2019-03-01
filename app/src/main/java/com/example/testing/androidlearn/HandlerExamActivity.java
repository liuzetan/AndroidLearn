package com.example.testing.androidlearn;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HandlerExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_exam);
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e("TAGGGG", "handleMessage");
            }
        };
        Message msg = new Message();
        msg.what = 123;
        handler.sendMessageAtFrontOfQueue(msg);
    }

    @Override
    protected void onResume() {
        Log.e("TAGGGG", "onResume");
        super.onResume();
    }
}
