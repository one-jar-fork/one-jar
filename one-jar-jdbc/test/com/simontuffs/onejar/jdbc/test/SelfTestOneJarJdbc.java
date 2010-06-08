package com.simontuffs.onejar.jdbc.test;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;

public class SelfTestOneJarJdbc extends TestCase {

    protected static Object test;
    protected static int failures = 0;
    
    public static void main(String args[]) {
        new TestSuite(SelfTestOneJarJdbc.class).run(new TestResult());
    }
    
    public SelfTestOneJarJdbc() throws Exception {
        if (test == null) {
            // Load Test object from the jar.
            JarClassLoader loader = new JarClassLoader("");
            Boot.setClassLoader(loader);
            // loader.setVerbose(true);
            loader.load(null);
            test = loader.loadClass("com.simontuffs.onejar.test.jdbc.main.Main").newInstance();
        }
    }
    
    public void testJdbc() throws Exception {
        Invoker.invoke(test, "testJdbc");
    }
    
    public void testLoadDerbyDriver() throws Exception {
        Invoker.invoke(test, "testLoadDerbyDriver");
    }

    public void testLoadMySqlDriver() throws Exception {
        Invoker.invoke(test, "testLoadMySqlDriver");
    }
}
