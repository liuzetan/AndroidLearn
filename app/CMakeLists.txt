cmake_minimum_required(VERSION 3.6)

add_library( # Sets the name of the library.
             JniTest

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/jni/test.c
             src/main/jni/blur.c)

find_library( # Sets the name of the path variable.
                log-lib

                # Specifies the name of the NDK library that
                # you want CMake to locate.
                log )
target_link_libraries(
              JniTest
                jnigraphics
              # Links the target library to the log library
              # included in the NDK.
              ${log-lib} )