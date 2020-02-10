package com.lzt.form;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lzt.DisplayUtils;

public class FormView extends View implements OnFormChangeListener{
    private int defaultWidth = DisplayUtils.dp2px(300);
    private int defaultHeight = DisplayUtils.dp2px(200);

    private FormData mFormData;
    private FormProvider mFormProvider;
    private TouchProcessor mTouchProcessor;

    public FormView(Context context) {
        this(context, null);
    }

    public FormView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mFormProvider = new FormProvider();
        mTouchProcessor = new TouchProcessor(this);
    }

    public void setFormData(FormData formData) {
        this.mFormData = formData;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWH(widthMeasureSpec, defaultWidth), measureWH(heightMeasureSpec, defaultHeight));
    }

    private int measureWH(int heightMeasureSpec, int defaultSize) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = defaultSize;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = mTouchProcessor.getTranslateX();
        float y = mTouchProcessor.getTranslateY();
        float zoom = mTouchProcessor.getZoom();
        mFormProvider.draw(canvas, mFormData, zoom, x, y, x + getMeasuredWidth(), y + getMeasuredHeight());
        mTouchProcessor.setContentSize(mFormProvider.getContentSize());
        mTouchProcessor.setVisibleSize(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mTouchProcessor.onTouchEvent(event);
    }

    @Override
    public void onChanged(float zoom, float translateX, float translateY) {
        postInvalidate();
    }
}
