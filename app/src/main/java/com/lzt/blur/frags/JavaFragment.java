package com.lzt.blur.frags;


import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

import com.lzt.BlurImage;
import com.lzt.JniTest;

/**
 * A simple {@link Fragment} subclass.
 */
public class JavaFragment extends BaseFragment {

    @Override
    protected void show(long time) {
        super.show(time);
        mText.setText("Java");
    }

    @Override
    protected Bitmap blur(Bitmap bitmap, int radius) {
        return BlurImage.blur(bitmap, radius, true);
    }
}
