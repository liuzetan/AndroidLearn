package com.lzt;

public class JniTest {
    static {
        System.loadLibrary("JniTest");
    }
    public native static String get();
}
