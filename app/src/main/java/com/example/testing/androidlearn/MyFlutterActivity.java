package com.example.testing.androidlearn;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import io.flutter.app.FlutterActivity;
import io.flutter.app.FlutterFragmentActivity;
import io.flutter.facade.Flutter;
import io.flutter.facade.FlutterFragment;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;

public class MyFlutterActivity extends AppCompatActivity {
    private FlutterView flutterView;
    private FlutterFragment flutterFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);
        LinearLayout linearLayout = findViewById(R.id.container);
        flutterView = Flutter.createView(this, getLifecycle(), "route1");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.addView(flutterView, params);

        new MethodChannel(flutterView, "samples.flutter.io/abc").setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                startActivity(new Intent(MyFlutterActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        flutterView.popRoute();
    }
}
