package com.example.testing.androidlearn;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

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
            }
        });

    }

    public <T> void test() {
        ArrayList<? super Number> l = new ArrayList<>();
        l = new ArrayList<Object>();
        l.add(4.5f);

    }
}
