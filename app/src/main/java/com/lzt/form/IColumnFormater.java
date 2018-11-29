package com.lzt.form;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface IColumnFormater {
    public int[] measureCellWidth(String s, int width, float zoom);

    void draw(Canvas canvas, Object obj, Rect rect, int padding, float zoom);
}
