package com.lzt.thirdstatebutton;

import android.widget.Checkable;

public interface ThreeCheckAble extends Checkable {
    void setState(Boolean state);
    Boolean getState();
}
