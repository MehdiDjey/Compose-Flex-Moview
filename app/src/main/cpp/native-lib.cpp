#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_druide_flexmovies_common_Keys_apiKey(JNIEnv *env, jobject thiz) {
    // you need to set your api key here
    std::string api_key = "c9856d0cb57c3f14bf75bdc6c063b8f3";
    return env->NewStringUTF(api_key.c_str());
}