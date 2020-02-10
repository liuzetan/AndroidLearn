package com.lzt.blur;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.testing.androidlearn.R;
import com.lzt.blur.frags.BaseFragment;


public class BitmapActivity extends AppCompatActivity implements View.OnClickListener {
    BaseFragment mJniPixel;
    BaseFragment mJniBitmap;
    BaseFragment mJava;
    BaseFragment mRS;
    BaseFragment mAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        findFragments();

        findViewById(R.id.btn_opt).setOnClickListener(this);
    }

    private void findFragments() {
        FragmentManager manager = getSupportFragmentManager();
        mJniPixel = (BaseFragment) manager.findFragmentById(R.id.frag_jni_pixel);
        mJniBitmap = (BaseFragment) manager.findFragmentById(R.id.frag_jni_bitmap);
        mJava = (BaseFragment) manager.findFragmentById(R.id.frag_java);
        mRS = (BaseFragment) manager.findFragmentById(R.id.frag_rs);
        mAnim = (BaseFragment) manager.findFragmentById(R.id.frag_anim);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            findViewById(R.id.frag_rs).setVisibility(View.GONE);
        }
    }

    private void start() {
        final View root = findViewById(R.id.root);
        root.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                root.getViewTreeObserver().removeOnPreDrawListener(this);
                root.buildDrawingCache();
                Bitmap bmp = root.getDrawingCache();
                setFragmentsBitmap(bmp);
                return true;
            }
        });
    }

    private void setFragmentsBitmap(final Bitmap bmp) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                mJniPixel.setBitmap(bmp);
                mJniBitmap.setBitmap(bmp);
                mJava.setBitmap(bmp);
                mAnim.setBitmap(bmp);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                    mRS.setBitmap(bmp);
            }
        };
        thread.start();
    }

    @Override
    public void onClick(View v) {
        start();
        v.setVisibility(View.GONE);
    }
}
