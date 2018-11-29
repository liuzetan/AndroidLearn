package com.lzt.form;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

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
    private Scroller scroller;
    private float flingRate = 0.5f;

    public TouchProcessor(OnFormChangeListener listener) {
        this.listener = listener;
        mGestureDetector = new GestureDetector(this);
        mScalGestureDetector = new ScaleGestureDetector(MyApplication.getmContext(), this);
        scroller = new Scroller(MyApplication.getmContext());
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
        if (Math.abs(velocityX) >
                ViewConfiguration.get(MyApplication.getmContext()).getScaledMinimumFlingVelocity()) {
            scroller.setFinalX(0);
            scroller.setFinalY(0);
            tempTranslateX = translateX;
            tempTranslateY = translateY;
            scroller.fling(0, 0, (int) velocityX, (int) velocityY, -50000, 50000
                    , -50000, 50000);
            isFling = true;
            startFling();
        }
        return true;
    }

    private float lastY;
    private TimeInterpolator interpolator = new DecelerateInterpolator();

    private float tempTranslateX;
    private float tempTranslateY;

    private void startFling() {
        int scrollY = (int) (scroller.getFinalY()* flingRate);
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, scrollY);
        valueAnimator.setInterpolator(interpolator);
        lastY = translateY;
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (isFling) {
                    int y = (int) animation.getAnimatedValue();
                    float newY = tempTranslateY - y;
                    float distanceY = newY - lastY;
                    lastY = (int) newY;
                    nestedScroll(distanceY);
                    notifyTableChanged();
                } else {
                    animation.cancel();
                }
            }
        });
        int duration = (int) (Math.abs(scroller.getFinalY()) * flingRate) / 2;
        valueAnimator.setDuration(duration > 300 ? 300 : duration);
        valueAnimator.start();
    }

    private void nestedScroll(float distanceY) {
        translateY += distanceY;
        if (translateY < 0) {
            translateY = 0;
        } else if (translateY > contentSize[1] - visibleSize[1]) {
            translateY = contentSize[1] - visibleSize[1];
        }
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
