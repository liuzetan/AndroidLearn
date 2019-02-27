LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := JniTest
LOCAL_SRC_FILES := test.c blur.c
LOCAL_LDLIBS += -llog -ljnigraphics -lm	#增加这行代码  -l<log库文件>

include $(BUILD_SHARED_LIBRARY)