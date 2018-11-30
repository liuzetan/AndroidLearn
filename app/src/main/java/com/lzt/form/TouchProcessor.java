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
    private static float DEFAULT_MAX_ZOOM = 2.0f;
    private static float DEFAULT_MIN_ZOOM = 1.0f;

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
    private float zoom = 1.0f;
    private float tmpZoom = 1.0f;
    private boolean isZooming = false;

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

    public float getZoom() {
        return zoom;
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
            tmpTranslateY = translateY;
            tmpTranslateX = translateX;
            scroller.fling(0, 0, (int) velocityX, (int) velocityY, -50000, 50000
                    , -50000, 50000);
            isFling = true;
            startFling();
        }
        return true;
    }

    private float lastPos;
    private TimeInterpolator interpolator = new DecelerateInterpolator();

    private float tmpTranslateY;
    private float tmpTranslateX;

    private void startFling() {
        int scrollX = (int) (scroller.getFinalX() * flingRate);
        int scrollY = (int) (scroller.getFinalY()* flingRate);
        int duration = 1000;
        if (Math.abs(scrollX) < Math.abs(scrollY)) {
            final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, scrollY);
            valueAnimator.setInterpolator(interpolator);
            lastPos = translateY;
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (isFling) {
                        int y = (int) animation.getAnimatedValue();
                        float newY = tmpTranslateY - y;
                        float distanceY = newY - lastPos;
                        lastPos = (int) newY;
                        flingY(distanceY);
                        notifyTableChanged();
                    } else {
                        animation.cancel();
                    }
                }
            });
            duration = (int) (Math.abs(scroller.getFinalY()) * flingRate) / 2;
            valueAnimator.setDuration(duration > 1000 ? 1000 : duration);
            valueAnimator.start();
        } else {
            final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, scrollX);
            valueAnimator.setInterpolator(interpolator);
            lastPos = translateX;
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (isFling) {
                        int x = (int) animation.getAnimatedValue();
                        float newX = tmpTranslateX - x;
                        float distanceX = newX - lastPos;
                        lastPos = (int) newX;
                        flingX(distanceX);
                        notifyTableChanged();
                    } else {
                        animation.cancel();
                    }
                }
            });
            duration = (int) (Math.abs(scroller.getFinalY()) * flingRate) / 2;
            valueAnimator.setDuration(duration > 1000 ? 1000 : duration);
            valueAnimator.start();
        }
    }
    private void flingX(float distance) {
        translateX += distance;
        if (translateX < 0) {
            translateX = 0;
        } else if (translateX > contentSize[0] - visibleSize[0]) {
            translateX = contentSize[0] - visibleSize[0];
        }
    }

    private void flingY(float distanceY) {
        translateY += distanceY;
        if (translateY < 0) {
            translateY = 0;
        } else if (translateY > contentSize[1] - visibleSize[1]) {
            translateY = contentSize[1] - visibleSize[1];
        }
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float oldZoom = zoom;
        boolean isScaleEnd = false;
        float scale = detector.getScaleFactor();
        if (scale > 1 && zoom == DEFAULT_MAX_ZOOM) {
            return true;
        }
        if (scale < 1 && zoom == DEFAULT_MIN_ZOOM) {
            return true;
        }
        this.zoom = tmpZoom * scale;
        if (zoom >= DEFAULT_MAX_ZOOM) {
            this.zoom = DEFAULT_MAX_ZOOM;
            isScaleEnd = true;
        } else if (this.zoom <= DEFAULT_MIN_ZOOM) {
            this.zoom = DEFAULT_MIN_ZOOM;
            isScaleEnd = true;
        }
        float factor = zoom / oldZoom;
        translateX = (int) (translateX * factor);
        translateY = (int) (translateY * factor);
        notifyTableChanged();
        return isScaleEnd;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        tmpZoom = this.zoom;
        isZooming = true;
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        isZooming = false;
    }
}
