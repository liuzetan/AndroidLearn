package com.example.testing.androidlearn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.lzt.thirdstatebutton.ThreeStateCheckbox;

public class ThirdStateCheckBoxActivity extends AppCompatActivity implements ThreeStateCheckbox.OnStateChangedListener{
    ThreeStateCheckbox cb1;
    ThreeStateCheckbox cb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_state_check_box);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb1.setButtonDrawable(null);
        cb2.setButtonDrawable(null);
        cb1.setmOnStateChangedListener(this);
        cb2.setmOnStateChangedListener(this);
    }

    @Override
    public void onStateChanged(ThreeStateCheckbox checkBox, @Nullable Boolean newState) {
        ViewGroup parent = (ViewGroup) checkBox.getParent();
        int index = 0;
        for (int i = 0; i < parent.getChildCount(); ++i) {
            if (parent.getChildAt(i) == checkBox) {
                index = i;
            } else {
                if (parent.getChildAt(i) instanceof ThreeStateCheckbox) {
//                    ((ThreeStateCheckbox) parent.getChildAt(i)).setThirdState(true, false);
                }
            }
        }
    }
}
