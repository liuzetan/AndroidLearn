package com.lzt.form;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.lzt.MyApplication;

public class TouchProcessor implements GestureDetector.OnGestureListener, ScaleGestureDetector.OnScaleGestureListener {
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScalGestureDetector;
    private boolean canZoom = true;
    private boolean isFling = false;
    private float translateX = 0;
    private float translateY = 0;
    private OnFormChangeListener listener;
    private int[] contentSize;
    private int[] visibleSize = new int[2];

    public TouchProcessor(OnFormChangeListener listener) {
        this.listener = listener;
        mGestureDetector = new GestureDetector(this);
        mScalGestureDetector = new ScaleGestureDetector(MyApplication.getmContext(), this);
    }

    public void setContentSize(int[] contentSize) {
        this.contentSize = contentSize;
    }

    public void setVisibleSize(int w, int h) {
        this.visibleSize[0] = w;
        this.visibleSize[1] = h;
    }

    public float getTranslateX() {
        return translateX;
    }

    public float getTranslateY() {
        return translateY;
    }

    public boolean isCanZoom() {
        return canZoom;
    }

    public void setCanZoom(boolean canZoom) {
        this.canZoom = canZoom;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (canZoom) {
            mScalGestureDetector.onTouchEvent(event);
        }
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    private void notifyTableChanged() {
        if (listener != null) {
            listener.onChanged(1, translateX, translateY);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        isFling = false;
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float preX = translateX;
        float preY = translateY;
        translateX += distanceX;
        translateY += distanceY;
        translateX = Math.min(contentSize[0] - visibleSize[0], translateX);
        translateY = Math.min(contentSize[1] - visibleSize[1], translateY);
        translateX = Math.max(0, translateX);
        translateY = Math.max(0, translateY);
        if (translateX != preX || translateY != preY) {
            notifyTableChanged();
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }
}
