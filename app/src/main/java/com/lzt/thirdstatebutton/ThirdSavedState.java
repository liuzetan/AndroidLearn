package com.lzt.thirdstatebutton;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class ThirdSavedState extends View.BaseSavedState {
    boolean thirdState;
    public ThirdSavedState(Parcel source) {
        super(source);
        thirdState = source.readInt() != 0;
    }

    public ThirdSavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(thirdState ? 1 : 0);
    }

    @Override
    public String toString() {
        return "IndetermSavedState.SavedState{"
                + Integer.toHexString(System.identityHashCode(this))
                + " indeterminate=" + thirdState + "}";
    }
    public static final Creator<ThirdSavedState> CREATOR
            = new Creator<ThirdSavedState>() {
        public ThirdSavedState createFromParcel(Parcel in) {
            return new ThirdSavedState(in);
        }

        public ThirdSavedState[] newArray(int size) {
            return new ThirdSavedState[size];
        }
    };
}
