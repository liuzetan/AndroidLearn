package com.example.testing.androidlearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnHorizontal;
    private Button btnCustomView;
    private Button btnNestedScroll;
    private Button btnFormView;
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
    }

    private void startActivity(Class c) {
        Intent intent = new Intent(MainActivity.this, c);
        startActivity(intent);
    }
}
