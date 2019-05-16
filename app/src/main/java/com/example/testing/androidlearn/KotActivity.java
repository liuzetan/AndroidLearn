package com.example.testing.androidlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.kotlin.Exam;

public class KotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kot);
        Button btn = findViewById(R.id.btn);
        TextView tv = findViewById(R.id.tv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv.setText("what a fuck");
//                    }
//                }).start();
                Log.e("System.out", "click");
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Exam exam = new Exam();
                exam.hello(tv);
                Log.e("System.out", "is after");
            }
        });

    }
}
