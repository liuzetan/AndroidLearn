package com.example.testing.androidlearn;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.flutter.facade.Flutter;
import io.flutter.facade.FlutterFragment;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

public class MyFlutterActivity extends AppCompatActivity {
    private FlutterView flutterView;
    private FlutterFragment flutterFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);
        FrameLayout linearLayout = findViewById(R.id.fl);
        flutterView = Flutter.createView(this, getLifecycle(), "route2");
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.addView(flutterView, params);

        NativeViewFactory.registerWith(flutterView.getPluginRegistry());

        MethodChannel methodChannel = new MethodChannel(flutterView, "samples.flutter.io/abc");
        methodChannel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                if (methodCall.method.equals("getRandom")) {
                    result.success((int)(100 * Math.random()));
                } else {
                    startActivity(new Intent(MyFlutterActivity.this, MainActivity.class));
                }
            }
        });

//        new EventChannel(flutterView, "com.xxx").setStreamHandler(new EventChannel.StreamHandler() {
//            @Override
//            public void onListen(Object o, EventChannel.EventSink eventSink) {
//                eventSink.success("msg success");
//            }
//
//            @Override
//            public void onCancel(Object o) {
//
//            }
//        });


        TextView tv = findViewById(R.id.tv);
        Button btn = findViewById(R.id.btn);
        BasicMessageChannel messageChannel = new BasicMessageChannel(flutterView, "CHANNEL", StringCodec.INSTANCE);
        messageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() {
            @Override
            public void onMessage(@Nullable Object o, @NonNull BasicMessageChannel.Reply reply) {
                tv.setText(o.toString());
                reply.reply("get it!!!");
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                methodChannel.invokeMethod("getInputParams", "{'arg1':'来自Native'}", new MethodChannel.Result() {
//                    @Override
//                    public void success(@Nullable Object o) {
//                        tv.setText(o.toString());
//                    }
//
//                    @Override
//                    public void error(String s, @Nullable String s1, @Nullable Object o) {
//
//                    }
//
//                    @Override
//                    public void notImplemented() {
//
//                    }
//                });
//
//                messageChannel.send("Native to Flutter");

                startActivity(new Intent(MyFlutterActivity.this, MyFlutter2Activity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        flutterView.popRoute();
    }
}
