package com.lzt;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public class DisplayUtils {

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, MyApplication.getmContext()
                .getResources().getDisplayMetrics());
    }

    public static int[] getScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = ((WindowManager) MyApplication.getmContext().getSystemService(Context.WINDOW_SERVICE));
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth, screenHeight;
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        return new int[]{screenWidth, screenHeight};
    }
}
