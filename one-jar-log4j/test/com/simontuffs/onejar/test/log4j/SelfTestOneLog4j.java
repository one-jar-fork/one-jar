package com.simontuffs.onejar.test.log4j;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;

public class SelfTestOneLog4j extends TestCase {

    protected static Object test;
    protected static int failures = 0;
    
    public static void main(String args[]) {
        new TestSuite(SelfTestOneLog4j.class).run(new TestResult());
    }
    
    public SelfTestOneLog4j() throws Exception {
        if (test == null) {
            // Load Test object from the jar.
            JarClassLoader loader = new JarClassLoader("");
            Boot.setClassLoader(loader);
            // loader.setVerbose(true);
            loader.load(null);
            test = loader.loadClass("com.simontuffs.onejar.test.log4j.Main").newInstance();
        }
    }
    
    public void testlog4j() throws Exception {
        Invoker.invoke(test, "testlog4j");
    }
    
}
