#include "Test.h"

#include <string>

using std::string;

// -Wl,--kill-at appears to be critical for mingw otherwise the '@'
// added to the function name by the linker (presumably for windows
// compliance) prevents java from finding the class, resulting in 
// an unsatisfied link error for JNI.
//
// MinGW build options (executed inside Eclipse CDT):
// Compile: gcc -IC:\j2sdk1.4.2_04\include -IC:\j2sdk1.4.2_04\include\win32 -O0 -g3 -Wall -c -fmessage-length=0 -oTest.o ../Test.cpp
// Link: g++ -Wl,--kill-at -shared -o one-jar-dll.dll Test.o    

JNIEXPORT jstring JNICALL Java_test_Test_echo
  (JNIEnv * env, jobject _this, jstring message) {
  	
  	jboolean iscopy;
    const char *str = env->GetStringUTFChars(message, &iscopy);

	string buf;
	
	buf = "echo(" + string(str) + ") (c++ strings)";
    
    if (iscopy) env->ReleaseStringUTFChars(message, str);
    
    return (jstring)env->NewStringUTF(buf.c_str());
    
}

JNIEXPORT jstring JNICALL Java_test_Test_hello
  (JNIEnv *env, jobject _this) {

	return (jstring)env->NewStringUTF("hello from native code!");
  	
}