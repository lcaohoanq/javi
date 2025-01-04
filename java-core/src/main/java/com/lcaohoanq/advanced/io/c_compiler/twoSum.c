#include <jni.h>
#include <stdlib.h>

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
JNIEXPORT jintArray JNICALL Java_com_lcaohoanq_advanced_io_c_1compiler_TwoSum_twoSum
  (JNIEnv *env, jobject obj, jintArray nums, jint numsSize, jint target) {
    
    // Get the nums array from Java
    jint *numsArr = (*env)->GetIntArrayElements(env, nums, 0);
    
    // Prepare result array
    jintArray result = (*env)->NewIntArray(env, 2);
    
    // Iterate through the array to find the two sum solution
    for (int i = 0; i < numsSize; i++) {
        for (int j = i + 1; j < numsSize; j++) {
            if (numsArr[i] + numsArr[j] == target) {
                jint indices[2] = {i, j};
                (*env)->SetIntArrayRegion(env, result, 0, 2, indices);
                (*env)->ReleaseIntArrayElements(env, nums, numsArr, 0);
                return result;
            }
        }
    }
    
    // In case no solution found (though the prompt guarantees there is one)
    (*env)->ReleaseIntArrayElements(env, nums, numsArr, 0);
    return result;
}
