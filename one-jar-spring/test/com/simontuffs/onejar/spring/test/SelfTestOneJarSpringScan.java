package com.simontuffs.onejar.spring.test;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;

public class SelfTestOneJarSpringScan extends TestCase {

    protected static Object test;
    protected static int failures = 0;
    
    public static void main(String args[]) {
        new TestSuite(SelfTestOneJarSpringScan.class).run(new TestResult());
    }
    
    public SelfTestOneJarSpringScan() throws Exception {
        if (test == null) {
            // Load Test object from the jar.
            JarClassLoader loader = new JarClassLoader("");
            Boot.setClassLoader(loader);
            // loader.setVerbose(true);
            loader.load(null);
            test = loader.loadClass("com.simontuffs.onejar.spring.main.Main").newInstance();
        }
    }
    
    public void testHello() throws Exception {
        Invoker.invoke(test, "transform");
    }
    
}
