package com.example.testing.androidlearn;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.lzt.di.BindView;
import com.lzt.di.ContentView;
import com.lzt.di.InjectManager;

@ContentView(R.layout.activity_di)
public class DIActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);
        tv.setText("depend injector");
    }
}
