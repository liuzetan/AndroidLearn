package com.lzt.form;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.lzt.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

public class Column {
    public static final int DEFAULT_MAX_WIDTH = DisplayUtils.dp2px(80);
    private String name;
    private List<Object> data;
    private int maxWidth = DEFAULT_MAX_WIDTH;
    private int padding = DisplayUtils.dp2px(4);
    private List<Rect> positionList;
    private IColumnFormater columnFormater;
    int currentTop = 0;
    private int left;

    public Column(String name, List<Object> data) {
        this.name = name;
        this.data = data;
    }

    public IColumnFormater getColumnFormater() {
        return columnFormater;
    }

    public void setColumnFormater(IColumnFormater columnFormater) {
        this.columnFormater = columnFormater;
    }

    public int calculatePosition(int left, float zoom) {
        this.left = left;
        int w = 0;
        if (positionList == null) {
            positionList = new ArrayList<>();
        } else {
            positionList.clear();
        }
        if (columnFormater == null) {
            columnFormater = new TextColumnFormater();
        }
        for (Object obj : data) {
            int[] wh = columnFormater.measureCellWidth(obj.toString(), maxWidth, zoom);
            wh[0] += 2*padding;
            wh[1] += 2*padding;
            wh[0] *= zoom;
            wh[1] *= zoom;
            positionList.add(new Rect(left, currentTop, left + wh[0], currentTop + wh[1]));
            currentTop += wh[1];
            w = Math.max(w, wh[0]);
        }
        int maxW = 0;
        for (Rect r : positionList) {
            maxW = Math.max(maxW, r.width());
        }
        for (Rect r : positionList) {
            r.right = r.left + maxW;
        }
        return w;
    }

    public List<Rect> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Rect> positionList) {
        this.positionList = positionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void draw(Canvas canvas, float zoom, float translateX, float translateY, float right, float bottom) {
        for (int i = 0; i < data.size(); ++i) {
            Object obj = data.get(i);
            Rect rect = positionList.get(i);
            if (rect.right < translateX || rect.bottom < translateY) continue;
            if (rect.left > right || rect.top > bottom) break;
            rect.left -= translateX;
            rect.right -= translateX;
            rect.top -= translateY;
            rect.bottom -= translateY;
            columnFormater.draw(canvas, obj, rect, padding, zoom);
        }
    }
}
