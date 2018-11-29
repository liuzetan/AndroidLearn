package com.lzt.form;

import android.graphics.Canvas;
import android.graphics.Rect;

public class FormProvider {

    private int[] contentSize = new int[2];

    public void draw(Canvas canvas, FormData formData, float zoom, float translateX, float translateY, float right, float bottom) {
        if (formData == null) {
            return;
        }
        measure(formData, zoom);
        for (Column column : formData.getColumnList()) {
            column.draw(canvas, zoom, translateX, translateY, right, bottom);
        }
    }

    public int[] getContentSize() {
        return contentSize;
    }

    private void measure(FormData formData, float zoom) {
        int left = 0;
        for (Column column : formData.getColumnList()) {
            left += column.calculatePosition(left, zoom);
        }
        contentSize[0] = left;
        int hNum = formData.getColumnList().get(0).getData().size();
        int curTop = 0;
        for (int i = 0; i < hNum; ++i) {
            int h = 0;
            for (Column column : formData.getColumnList()) {
                Rect rect = column.getPositionList().get(i);
                h = Math.max(h, rect.height());
            }
            for (Column column : formData.getColumnList()) {
                Rect rect = column.getPositionList().get(i);
                rect.top = curTop;
                rect.bottom = curTop + h;
            }
            curTop += h;
        }
        contentSize[1] = curTop;
    }
}
