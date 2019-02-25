package com.example.testing.androidlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lzt.JniTest;

public class JniActivity extends AppCompatActivity {

    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        tvName = findViewById(R.id.name);
        String name = JniTest.get();
        int n = JniTest.add();
        int m = JniTest.minus();
        JniTest t = new JniTest();
        t.test(13);
        String str = t.reverse("abcdefg");
        tvName.setText(name + "_" + m + "_" + n + "_" + str);
    }
}
