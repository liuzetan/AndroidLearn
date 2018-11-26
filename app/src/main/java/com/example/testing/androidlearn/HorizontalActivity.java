package com.example.testing.androidlearn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HorizontalActivity extends Activity {
    private ListView rv1;
    private ListView rv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rv1 = findViewById(R.id.rv_1);
        rv2 = findViewById(R.id.rv_2);
        String[] strs1 = {"1", "2","1", "2","1", "2","1", "2","1", "2","1", "2","1", "2"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, strs1);
        rv1.setAdapter(adapter1);

        String[] strs2 = {"A", "B","A", "B","A", "B","A", "B","A", "B","A", "B","A", "B","A", "B"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, strs2);
        rv2.setAdapter(adapter2);
    }
}
