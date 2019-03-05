package com.example.testing.androidlearn;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lzt.blur.BitmapActivity;

import io.flutter.facade.Flutter;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.ll_container);

        addButton("自定义ViewPager", HorizontalActivity.class);
        addButton("自定义View", CustomActivity.class);
        addButton("NestedScroll", NestedScrollActivity.class);
        addButton("FormView", FormActivity.class);
        addButton("ThirdStateCheckBox", ThirdStateCheckBoxActivity.class);
        addButton("圆形图片", CircleImageActivity.class);
        addButton("进入JNI", JniActivity.class);
        addButton("进入JNI Bitmap", BitmapActivity.class);
        addButton("Handler Exam", HandlerExamActivity.class);
        Button flutterBtn = addButton("Flutter", MainActivity.class);
        flutterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = Flutter.createView(MainActivity.this, getLifecycle(), "/route45");
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 600);
                linearLayout.addView(view, params);
            }
        });
    }

    private Button addButton(String text, final Class cla) {
        Button button = new Button(this);
        button.setText(text);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                dp2px(this, 40));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cla);
            }
        });
        linearLayout.addView(button);
        return button;
    }

    private void startActivity(Class c) {
        Intent intent = new Intent(MainActivity.this, c);
        startActivity(intent);
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
