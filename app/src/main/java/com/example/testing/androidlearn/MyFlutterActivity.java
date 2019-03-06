package com.example.testing.androidlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.flutter.app.FlutterActivity;
import io.flutter.facade.Flutter;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MyFlutterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);
        LinearLayout linearLayout = findViewById(R.id.container);
        View view = Flutter.createView(this, getLifecycle(), "route1");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.addView(view, params);
    }
}
