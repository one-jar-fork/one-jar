package com.simontuffs.onejar.args.test;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;

public class SelfTestOneJarArgs extends TestCase {

    protected static Object test;
    protected static int failures = 0;
    
    public static void main(String args[]) {
        new TestSuite(SelfTestOneJarArgs.class).run(new TestResult());
    }
    
    public SelfTestOneJarArgs() throws Exception {
        if (test == null) {
            // Load Test object from the jar.
            JarClassLoader loader = new JarClassLoader("");
            Boot.setClassLoader(loader);
            // loader.setVerbose(true);
            loader.load(null);
            test = loader.loadClass("com.simontuffs.onejar.args.test.Main").newInstance();
        }
    }
    
    public void testArgs() throws Exception {
        Invoker.invoke(test, "testArgs");
    }
    
}
