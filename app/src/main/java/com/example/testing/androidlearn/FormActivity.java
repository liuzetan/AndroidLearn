package com.example.testing.androidlearn;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.lzt.form.Column;
import com.lzt.form.FormData;
import com.lzt.form.FormView;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    FormView formView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        formView = findViewById(R.id.forview);
        List<Column> columnList = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            List<Object> strings = new ArrayList<>();
            for (int j = 0; j < 60; ++j) {
                if (j == 3 && i == 2) {
                    strings.add("valuedfgsdfgsdfvervdfsdfgvsdfvsdgdfgsdfgsfbgvfsghthrthrtdrt" + j);
                } else {
                    strings.add("value" + j);
                }
            }
            Column column = new Column("colum" + i, strings);
            columnList.add(column);
        }
        columnList.get(1).setPined(true);
        columnList.get(2).setPined(true);
        columnList.get(2).setName("ccccccccccccccolum2");
        columnList.get(3).setAutoMerge(true);
        for (int i = 2; i < 5; ++i) {
            columnList.get(3).getData().set(i, "mergeed");
        }
        FormData formData = new FormData(columnList);
        formView.setFormData(formData);
    }
}
