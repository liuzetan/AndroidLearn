package com.lzt.di;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.example.testing.androidlearn.R;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InjectManager {

    public static void inject(Activity activity) {
        injectContentView(activity);
        injectBindView(activity);
    }

    private static void injectBindView(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                int id = bindView.value();
                try {
                    Method method = aClass.getMethod("findViewById", int.class);
                    Object view = method.invoke(activity, id);
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectContentView(Activity activity) {
        Class<? extends Activity> ac = activity.getClass();
        ContentView contentView = ac.getAnnotation(ContentView.class);
        if (contentView != null) {
            int layoutId = contentView.value();

//            activity.setContentView(layoutId);

            try {
                Method method = ac.getMethod("setContentView", int.class);
                method.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
