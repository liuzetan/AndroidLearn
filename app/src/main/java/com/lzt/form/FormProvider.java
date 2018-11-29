package com.lzt.form;

import android.graphics.Canvas;
import android.graphics.Rect;

public class FormProvider {

    public void draw(Canvas canvas, FormData formData) {
        if (formData == null) {
            return;
        }
        measure(formData);
        for (Column column : formData.getColumnList()) {
            column.draw(canvas);
        }
    }

    private void measure(FormData formData) {
        int left = 0;
        for (Column column : formData.getColumnList()) {
            left += column.calculatePosition(left);
        }
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
    }
}
