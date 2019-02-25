#include<jni.h>
#include<stdio.h>
#include "com_lzt_JniTest.h"
#include <string.h>

#include <android/log.h>
#define LOG_TAG "System.out.c"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)


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

JNIEXPORT jint JNICALL Java_com_lzt_JniTest_minus
        (JNIEnv *env, jclass c) {
    jfieldID fieldId_a = (*env)->GetStaticFieldID(env, c, "a", "I");
    jfieldID fieldId_b = (*env)->GetStaticFieldID(env, c, "b", "I");
    jint a = (*env)->GetStaticIntField(env, c, fieldId_a);
    jint b = (*env)->GetStaticIntField(env, c, fieldId_b);
    return a-b;
}

JNIEXPORT void JNICALL Java_com_lzt_JniTest_test
        (JNIEnv *env, jobject obj, jint n) {
    jclass cla = (*env)->GetObjectClass(env, obj);
    jmethodID method = (*env)->GetMethodID(env, cla, "forNative", "(ILjava/util/Date;[I)I");
    jclass cla_date = (*env)->FindClass(env, "java/util/Date");
    jmethodID method_init_date = (*env)->GetMethodID(env, cla_date, "<init>", "()V");
    jobject now = (*env)->NewObject(env, cla_date, method_init_date);
    jint result = (*env)->CallIntMethod(env, obj, method, n, now, NULL);
    LOGD("result = %d", result);
}

char *reverse(char *str)
{
    if( !str ) {
        return NULL;
    }

    int len = strlen(str);
    char temp;
    for( int i = 0; i < len / 2; i++ )
    {
        // 交换前后两个相应位置的字符
        temp = *(str + i);
        *(str + i) = *(str + len - 1 - i);
        *(str + len - 1 - i) = temp;
    }

    return str;
}

jstring stoJstring(JNIEnv* env, const char* pat)
{
    jclass strClass = (*env)->FindClass(env, "java/lang/String");
    jmethodID ctorID = (*env)->GetMethodID(env, strClass, "<init>", "([BLjava/lang/String;)V");
    jbyteArray bytes = (*env)->NewByteArray(env, strlen(pat));
    (*env)->SetByteArrayRegion(env, bytes, 0, strlen(pat), (jbyte*)pat);
    jstring encoding = (*env)->NewStringUTF(env, "utf-8");
    return (jstring)(*env)->NewObject(env, strClass, ctorID, bytes, encoding);
}

JNIEXPORT jstring JNICALL Java_com_lzt_JniTest_reverse
        (JNIEnv *env, jobject obj, jstring jstr) {
    //1
    char* char1 = (char*)(*env)->GetStringUTFChars(env, jstr, NULL);
    char1 = reverse(char1);
    jstring res = stoJstring(env, char1);
    (*env)->ReleaseStringUTFChars(env, jstr, char1);
    return res;
}