package com.simontuffs.onejar.guice.test;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;

public class SelfTestOneJarGuice extends TestCase {

    protected static Object test;
    protected static int failures = 0;
    
    public static void main(String args[]) {
        new TestSuite(SelfTestOneJarGuice.class).run(new TestResult());
    }
    
    public SelfTestOneJarGuice() throws Exception {
        if (test == null) {
            // Load Test object from the jar.
            JarClassLoader loader = new JarClassLoader("");
            Boot.setClassLoader(loader);
            // loader.setVerbose(true);
            loader.load(null);
            test = loader.loadClass("onejar_guice.Main").newInstance();
        }
    }
    
    public void testHello() throws Exception {
        Invoker.invoke(test, "sayHello");
    }
    
}
