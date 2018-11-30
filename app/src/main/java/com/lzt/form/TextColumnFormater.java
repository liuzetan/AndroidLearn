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
    private TextPaint contentPaint;
    private Paint contentBackPaint;
    private Paint contentLinePaint;

    private TextPaint titlePaint;
    private Paint titleBackPaint;
    private Paint titleLinePaint;

    private int lineWidth = DisplayUtils.dp2px(2);
    private int textSize = DisplayUtils.dp2px(16);
    private int titleSize = DisplayUtils.dp2px(20);

    public TextColumnFormater() {
        contentPaint = new TextPaint();
        contentPaint.setAntiAlias(true);
        contentPaint.setColor(Color.DKGRAY);
        contentPaint.setTextSize(textSize);

        contentBackPaint = new Paint();
        contentBackPaint.setColor(Color.WHITE);

        contentLinePaint = new Paint();
        contentLinePaint.setColor(Color.BLUE);
        contentLinePaint.setStrokeWidth(lineWidth);

        titlePaint = new TextPaint();
        titlePaint.setAntiAlias(true);
        titlePaint.setColor(Color.BLACK);
        titlePaint.setTextSize(titleSize);

        titleBackPaint = new Paint();
        titleBackPaint.setColor(Color.LTGRAY);

        titleLinePaint = new Paint();
        titleLinePaint.setColor(Color.BLUE);
        titleLinePaint.setStrokeWidth(lineWidth);
    }

    @Override
    public int[] measureTitleWidth(String s, int width, float zoom) {
        titlePaint.setTextSize(titleSize);
        StaticLayout staticLayout = new StaticLayout(s, titlePaint, width, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, false);
        if (staticLayout.getLineCount() == 1) {
            return new int[]{(int) titlePaint.measureText(s) + 2, staticLayout.getHeight()};
        } else {
            return new int[]{width, staticLayout.getHeight()};
        }
    }

    @Override
    public void drawTitleCell(Canvas canvas, String name, Rect rect, int padding, float zoom) {
        Rect backRect = new Rect(rect);
        backRect.left += lineWidth/2;
        backRect.top += lineWidth/2;
        backRect.right -= lineWidth/2;
        backRect.bottom -= lineWidth/2;
        canvas.drawRect(backRect, titleBackPaint);
        canvas.drawLine(rect.left, rect.bottom, rect.right, rect.bottom, titleLinePaint);
        canvas.drawLine(rect.right, rect.top, rect.right, rect.bottom, titleLinePaint);

        contentPaint.setTextSize(titleSize * zoom);

        StaticLayout staticLayout = new StaticLayout(name, titlePaint, rect.width() - 2*padding, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, false);
        canvas.save();
        int mindY = (rect.bottom + rect.top) /2;
        canvas.translate(rect.left + padding, mindY - staticLayout.getHeight()/2);
        staticLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public int[] measureCellWidth(String s, int width, float zoom) {
        contentPaint.setTextSize(textSize);
        StaticLayout staticLayout = new StaticLayout(s, contentPaint, width, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, false);
        if (staticLayout.getLineCount() == 1) {
            return new int[]{(int) contentPaint.measureText(s) + 2, staticLayout.getHeight()};
        } else {
            return new int[]{width, staticLayout.getHeight()};
        }
    }

    @Override
    public void drawContentCell(Canvas canvas, Object obj, Rect rect, int padding, float zoom) {
        Rect backRect = new Rect(rect);
        backRect.left += lineWidth/2;
        backRect.top += lineWidth/2;
        backRect.right -= lineWidth/2;
        backRect.bottom -= lineWidth/2;
        canvas.drawRect(backRect, contentBackPaint);
        canvas.drawLine(rect.left, rect.bottom, rect.right, rect.bottom, contentLinePaint);
        canvas.drawLine(rect.right, rect.top, rect.right, rect.bottom, contentLinePaint);

        contentPaint.setTextSize(textSize * zoom);

        StaticLayout staticLayout = new StaticLayout(obj.toString(), contentPaint, rect.width() - 2*padding, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, false);
        canvas.save();
        int mindY = (rect.bottom + rect.top) /2;
        canvas.translate(rect.left + padding, mindY - staticLayout.getHeight()/2);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}
