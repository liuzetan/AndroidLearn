package com.example.testing.androidlearn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzt.blur.BitmapActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnHorizontal;
    private Button btnCustomView;
    private Button btnNestedScroll;
    private Button btnFormView;
    private Button btnThirdState;
    private Button btnCircleImage;
    private Button btnJni;
    private Button btnBitmap;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHorizontal = findViewById(R.id.btn_horizontal);
        btnHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HorizontalActivity.class);
            }
        });

        btnCustomView = findViewById(R.id.btn_custom);
        btnCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CustomActivity.class);
            }
        });

        btnNestedScroll = findViewById(R.id.btn_nestscroll);
        btnNestedScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NestedScrollActivity.class);
            }
        });

        btnFormView = findViewById(R.id.btn_formview);
        btnFormView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FormActivity.class);
            }
        });

        btnThirdState = findViewById(R.id.btn_thirdstatecheckbox);
        btnThirdState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ThirdStateCheckBoxActivity.class);
            }
        });

        btnCircleImage = findViewById(R.id.btn_circleimage);
        btnCircleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CircleImageActivity.class);
            }
        });

        btnJni = findViewById(R.id.btn_jni);
        btnJni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(JniActivity.class);
            }
        });

        btnBitmap = findViewById(R.id.btn_bitmap);
        btnBitmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BitmapActivity.class);
            }
        });
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
//        handler.sendMessage(msg);
        handler.sendMessageAtFrontOfQueue(msg);
    }

    @Override
    protected void onResume() {
        Log.e("TAGGGG", "onResume");
        super.onResume();
    }

    private void startActivity(Class c) {
        Intent intent = new Intent(MainActivity.this, c);
        startActivity(intent);
    }
}
