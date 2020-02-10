package com.lzt.blur.frags;


import android.graphics.Bitmap;
import androidx.fragment.app.Fragment;

import com.lzt.BlurImage;


/**
 * A simple {@link Fragment} subclass.
 */
public class JniBitmapFragment extends BaseFragment {

    @Override
    protected void show(long time) {
        super.show(time);
        mText.setText("Jni Bitmap");
    }

    @Override
    protected Bitmap blur(Bitmap bitmap, int radius) {
        return BlurImage.blurNatively(bitmap, radius, true);
    }
}
