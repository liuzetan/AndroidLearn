package com.lzt;

public class JniTest {
    static {
        System.loadLibrary("JniTest");
    }

    public static int a = 19;
    public static int b = 30;

    public native static String get();
    public native static int add();
}
