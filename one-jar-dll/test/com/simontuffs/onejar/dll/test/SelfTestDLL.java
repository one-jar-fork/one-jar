package com.simontuffs.onejar.dll.test;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;

public class SelfTestDLL extends TestCase {

    protected static Object test;
    protected static int failures = 0;
    
    public static void main(String args[]) {
        new TestSuite(SelfTestDLL.class).run(new TestResult());
    }
    
    public SelfTestDLL() throws Exception {
        if (test == null) {
            // Load Test object from the jar.
            JarClassLoader loader = new JarClassLoader("");
            Boot.setClassLoader(loader);
            // loader.setVerbose(true);
            loader.load(null);
            test = loader.loadClass("test.Test").newInstance();
        }
    }
    
    public void testHello() throws Exception {
        Invoker.invoke(test, "testHello");
    }
    
    public void testEcho() throws Exception {
        Invoker.invoke(test, "testEcho");
    }


}
