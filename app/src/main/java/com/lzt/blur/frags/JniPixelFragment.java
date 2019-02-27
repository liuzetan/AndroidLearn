package com.lzt.blur.frags;


import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

import com.lzt.BlurImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class JniPixelFragment extends BaseFragment {

    @Override
    protected void show(long time) {
        super.show(time);
        mText.setText("Jni Pixel");
    }

    @Override
    protected Bitmap blur(Bitmap bitmap, int radius) {
        return BlurImage.blurNativelyPixels(bitmap, radius, true);
    }
}
