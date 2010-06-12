package com.simontuffs.onejar.inversion.test;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;

public class SelfTestOneJarInversion extends TestCase {

    protected static Object test;
    protected static int failures = 0;
    
    public static void main(String args[]) {
        new TestSuite(SelfTestOneJarInversion.class).run(new TestResult());
    }
    
    public SelfTestOneJarInversion() throws Exception {
        if (test == null) {
            // Load Test object from the jar.
            JarClassLoader loader = new JarClassLoader("");
            Boot.setClassLoader(loader);
            // loader.setVerbose(true);
            loader.load(null);
            test = loader.loadClass("com.simontuffs.onejar.inversion.test.Main").newInstance();
        }
    }
    
    public void testInversion() throws Exception {
        Invoker.invoke(test, "testInversion");
    }
    
}
