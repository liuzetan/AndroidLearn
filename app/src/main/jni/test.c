#include<jni.h>
#include<stdio.h>
#include "com_lzt_JniTest.h"

JNIEXPORT jstring JNICALL Java_com_lzt_JniTest_get
  (JNIEnv *env, jclass c) {
    return (*env)->NewStringUTF(env, "This is my first ndk app");
  }