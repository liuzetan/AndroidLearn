#include<jni.h>
#include<stdio.h>
#include "com_lzt_JniTest.h"

JNIEXPORT jstring JNICALL Java_com_lzt_JniTest_get
        (JNIEnv *env, jclass c) {
    return (*env)->NewStringUTF(env, "This is first ndk app");
}

JNIEXPORT jint JNICALL Java_com_lzt_JniTest_add
        (JNIEnv *env, jclass c) {
    jfieldID fieldId_a = (*env)->GetStaticFieldID(env, c, "a", "I");
    jfieldID fieldId_b = (*env)->GetStaticFieldID(env, c, "b", "I");
    jint a = (*env)->GetStaticIntField(env, c, fieldId_a);
    jint b = (*env)->GetStaticIntField(env, c, fieldId_b);
    return a+b;
}