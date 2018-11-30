package com.lzt.form;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.lzt.DisplayUtils;

public class TextColumnFormater implements IColumnFormater {
    private TextPaint paint;
    private Paint backPaint;
    private Paint linePaint;
    private int lineWidth = DisplayUtils.dp2px(2);
    private int textSize = DisplayUtils.dp2px(16);

    public TextColumnFormater() {
        paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setTextSize(textSize);

        backPaint = new Paint();
        backPaint.setColor(Color.LTGRAY);

        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(lineWidth);
    }

    @Override
    public int[] measureCellWidth(String s, int width, float zoom) {
        paint.setTextSize(textSize);
        StaticLayout staticLayout = new StaticLayout(s, paint, width, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, false);
        if (staticLayout.getLineCount() == 1) {
            return new int[]{(int) paint.measureText(s) + 2, staticLayout.getHeight()};
        } else {
            return new int[]{width, staticLayout.getHeight()};
        }
    }

    @Override
    public void draw(Canvas canvas, Object obj, Rect rect, int padding, float zoom) {
        Rect backRect = new Rect(rect);
        backRect.left += lineWidth/2;
        backRect.top += lineWidth/2;
        backRect.right -= lineWidth/2;
        backRect.bottom -= lineWidth/2;
        canvas.drawRect(backRect, backPaint);
        canvas.drawLine(rect.left, rect.bottom, rect.right, rect.bottom, linePaint);
        canvas.drawLine(rect.right, rect.top, rect.right, rect.bottom, linePaint);

        paint.setTextSize(textSize * zoom);

        StaticLayout staticLayout = new StaticLayout(obj.toString(), paint, rect.width() - 2*padding, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, false);
        canvas.save();
        int mindY = (rect.bottom + rect.top) /2;
        canvas.translate(rect.left + padding, mindY - staticLayout.getHeight()/2);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}
