package com.lzt.form;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface IColumnFormater {
    public int[] measureCellWidth(String s, int width);

    void draw(Canvas canvas, Object obj, Rect rect, int padding);
}
