package com.lzt.form;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface IColumnFormater {
    int[] measureTitleWidth(String s, int width, float zoom);
    void drawTitleCell(Canvas canvas, String name, Rect rect, int padding, float zoom);

    int[] measureCellWidth(String s, int width, float zoom);
    void drawContentCell(Canvas canvas, Object obj, Rect rect, int padding, float zoom);
}
