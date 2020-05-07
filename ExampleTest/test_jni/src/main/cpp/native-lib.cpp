//
// Created by Administrator on 2020-05-06.
//
#include <jni.h>
#include <string>
#include <syslog.h>
#include <android/log.h>
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, "LOG_TAG", __VA_ARGS__)
#include "include/wfOsd2.h"



char * Jstring2CStr( JNIEnv * env, jstring jstr )
{
    char * rtn = NULL;
    jclass clsstring = env->FindClass("java/lang/String");
    jstring strencode = env->NewStringUTF("GB2312");
    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr= (jbyteArray)env->CallObjectMethod(jstr,mid,strencode);
    jsize alen = env->GetArrayLength(barr);
    jbyte * ba = env->GetByteArrayElements(barr,JNI_FALSE);
    if(alen > 0)
    {
        rtn = (char*)malloc(alen+1); //new char[alen+1];
        memcpy(rtn,ba,alen);
        rtn[alen]=0;
    }
    env->ReleaseByteArrayElements(barr,ba,0);

    return rtn;
}


/**
 * jclass FindClass(const char* clsName):通过类的名称(类的全名，这时候包名不是用.号，而是用/来区分的)来获取jclass
如: jclass str = env->FindClass("java/lang/String");获取Java中的String对象的class对象。
jclass GetObjectClass(jobject obj):通过对象实例来获取jclass，相当于java中的getClass方法
jclass GetSuperClass(jclass obj):通过jclass可以获取其父类的jclass对象
 */
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_test_1jni_MainActivity_exitSDK(JNIEnv *env, jclass type) {
   return exitWfSDK();

}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_test_1jni_MainActivity_initSDK(JNIEnv *env, jclass type,jstring sicip,jint port) {
    syslog(0,"=====initSDk");
   return initWfSDK(Jstring2CStr(env,sicip), port);
}




extern "C"
JNIEXPORT jint JNICALL
Java_com_example_test_1jni_MainActivity_WFOSD_1setOsdDisplayLineNums(JNIEnv *env, jobject instance,jint lineNum) {

    return WFOSD_setOsdDisplayLineNum(lineNum);
}

extern "C"
JNIEXPORT jint JNICALL        //String szcip,int port,String szcosd,int iosdlen,int ix,int iy, short iColor,String szcCo
Java_com_example_test_1jni_MainActivity_WFOSD_1sets(JNIEnv *env, jobject instance,jstring sicip,jint port,jstring szcosd,jint iosdlen,jint ix,jint iy,jshort icolor,jstring szcco) {

    return WFOSD_set(Jstring2CStr(env,sicip),port,Jstring2CStr(env,szcosd),iosdlen,ix,iy,icolor,Jstring2CStr(env,szcco));
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_test_1jni_MainActivity_WFOSD_1clears(JNIEnv *env, jobject instance,jstring sicip,jint port,jstring szcCode) {

    return WFOSD_clear(Jstring2CStr(env,sicip),port,Jstring2CStr(env,szcCode));
}
