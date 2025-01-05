#include <jni.h>

JNIEXPORT jint JNICALL Java_com_lcaohoanq_advanced_io_c_1compiler_Sum_sum
  (JNIEnv *env, jobject obj, jint a, jint b) {
    printf("Hello from C\n");
    return a + b;
}
