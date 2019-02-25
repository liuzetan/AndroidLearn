package com.lzt;

import android.util.Log;

import java.util.Arrays;
import java.util.Date;

public class JniTest {
    static {
        System.loadLibrary("JniTest");
    }

    public static int a = 19;
    public static int b = 30;

    public int forNative(int n, Date date, int[] arr) {
        Log.e("TAG", "n = " + n + ", date = " + date + ", arr = " + Arrays.toString(arr));
        return n + a;
    }

    public native static String get();
    public native static int add();
    public native static int minus();

    public native void test(int n);
    public native String reverse(String str);
}
