package com.lzt.view;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class NestedScrollChildView extends LinearLayout implements NestedScrollingChild2 {
    private NestedScrollingChildHelper nestedScrollingChildHelper;
    private int[] offset = new int[2];
    private int[] consumed = new int[2];
    private int lastY;
    private int showHeight;
    int consumedAll = 0;

    public NestedScrollChildView(Context context) {
        super(context);
    }

    public NestedScrollChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        showHeight = getMeasuredHeight();

        //第二次测量，对稿哦度没有任何限制，那么测量出来的就是完全展示内容所需要的高度
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                consumedAll = 0;
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int y = (int) event.getRawY();
                int dy = y - lastY;
                lastY = y;
                if ( startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_TOUCH)
                        && dispatchNestedPreScroll(0, dy, consumed, offset, ViewCompat.TYPE_TOUCH)) {
                    consumedAll += consumed[1];
                    int remain = dy - consumed[1];
                    if (remain != 0) {
                        Log.e("TAG", "remain = " + remain);
                        scrollBy(0, -remain);
                    }
                } else {
                    scrollBy(0, -dy);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollY() == 0) {
                    if (consumedAll < 0) {
                        dispatchNestedPreScroll(0, -1000, consumed, offset, ViewCompat.TYPE_TOUCH);

                    } else if (consumedAll < 200) {
                        dispatchNestedPreScroll(0, -consumedAll, consumed, offset, ViewCompat.TYPE_TOUCH);
                    } else {

                        dispatchNestedPreScroll(0, 10000, consumed, offset, ViewCompat.TYPE_TOUCH);
                    }
                }
//                if (getScrollY() < 100) {
//                    scrollTo(0, 0);
//                } else {
//                    scrollTo(0, getMeasuredHeight() - showHeight);
//                }
                break;
        }
        return true;
    }

    @Override
    public void scrollTo(int x, int y) {
        int maxY = getMeasuredHeight() - showHeight;
        if (y > maxY) {
            y = maxY;
        }
        if (y < 0) {
            y = 0;
        }
        super.scrollTo(x, y);
    }

    private NestedScrollingChildHelper getNestedScrollingChildHelper() {
        if (nestedScrollingChildHelper == null) {
            nestedScrollingChildHelper = new NestedScrollingChildHelper(this);
            nestedScrollingChildHelper.setNestedScrollingEnabled(true);
        }
        return nestedScrollingChildHelper;
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return getNestedScrollingChildHelper().startNestedScroll(axes, type);
    }

    @Override
    public void stopNestedScroll(int type) {
        getNestedScrollingChildHelper().stopNestedScroll(type);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return getNestedScrollingChildHelper().hasNestedScrollingParent(type);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return getNestedScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return getNestedScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }
}
