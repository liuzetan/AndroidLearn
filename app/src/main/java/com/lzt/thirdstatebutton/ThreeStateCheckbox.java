package com.lzt.thirdstatebutton;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import com.example.testing.androidlearn.R;

public class ThreeStateCheckbox extends AppCompatCheckBox implements ThreeCheckAble{
    private boolean isInThirdState;
    private transient boolean mBroadcasting;
    private transient OnStateChangedListener mOnStateChangedListener;

    public interface OnStateChangedListener {
        /**
         * Called when the indeterminate state has changed.
         *
         * @param checkBox The checkbox view whose state has changed.
         * @param newState The new state of checkBox. Value meanings:
         *              null = indeterminate state
         *              true = checked state
         *              false = unchecked state
         */
        void onStateChanged(ThreeStateCheckbox checkBox, @Nullable Boolean newState);
    }
    public ThreeStateCheckbox(Context context) {
        super(context);
        init();
    }

    public ThreeStateCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThreeStateCheckbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setThirdState(true);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (getState() == null) {
            mergeDrawableStates(drawableState, new int[]{R.attr.state_third});
        }
        return drawableState;
    }

    public void setmOnStateChangedListener(OnStateChangedListener mOnStateChangedListener) {
        this.mOnStateChangedListener = mOnStateChangedListener;
    }

    @Override
    public void toggle() {
        if (isInThirdState) {
            setChecked(true);
        } else {
            super.toggle();
        }
    }

    @Override
    public void setChecked(boolean checked) {
        final boolean checkedChanged = isChecked() != checked;
        super.setChecked(checked);
        final boolean wasIndeterminate = isInThirdState();
        setThirdState(false, false);
        if (wasIndeterminate || checkedChanged) {
            notifyStateListener();
        }
    }

    public void setChecked(boolean checked, boolean notify) {
        if (notify) {
            setChecked(checked);
        } else {
            super.setChecked(checked);
            setThirdState(false, false);
        }
    }

    @Override
    public Boolean getState() {
        return isInThirdState ? null : isChecked();
    }

    @Override
    public void setState(Boolean state) {
        if (state != null) {
            setChecked(state);
        } else {
            setThirdState(true);
        }
    }
    public boolean isInThirdState() {
        return isInThirdState;
    }

    public void setThirdState(boolean indeterminate) {
        setThirdState(indeterminate, true);
    }

    public void setThirdState(boolean indeterminate, boolean notify) {
        if (isInThirdState != indeterminate) {
            isInThirdState = indeterminate;
            refreshDrawableState();
            if (notify) {
                notifyStateListener();
            }
        }
    }

    private void notifyStateListener() {
        if (mBroadcasting) {
            return;
        }
        mBroadcasting = true;
        if (mOnStateChangedListener != null) {
            mOnStateChangedListener.onStateChanged(this, getState());
        }
        mBroadcasting = false;
    }
    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        ThirdSavedState ss = new ThirdSavedState(superState);
        ss.thirdState = isInThirdState;

        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        ThirdSavedState ss = (ThirdSavedState) state;

        // This temporarily disallows our callback notification, we will call it below if needed
        mBroadcasting = true;
        super.onRestoreInstanceState(ss.getSuperState());
        mBroadcasting = false;

        isInThirdState = ss.thirdState;
        // Both "indeterminate" and "checked" state are considered "excited" states. "Excited" state
        // is state that is different from the default "unchecked". On view restoration CompoundButton
        // notifies for change if the restored state is non-default. So, we will do the same for our merged state.
        if (isInThirdState || isChecked()) {
            notifyStateListener();
        }
    }

}
