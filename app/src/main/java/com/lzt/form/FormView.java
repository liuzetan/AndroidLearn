package com.lzt.form;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lzt.DisplayUtils;

public class FormView extends View {
    private int defaultWidth = DisplayUtils.dp2px(300);
    private int defaultHeight = DisplayUtils.dp2px(200);

    private FormData mFormData;
    private FormProvider mFormProvider;

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
        mFormProvider.draw(canvas, mFormData);
    }
}
