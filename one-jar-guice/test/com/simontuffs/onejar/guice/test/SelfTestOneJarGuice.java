package com.simontuffs.onejar.guice.test;

import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;

public class SelfTestOneJarGuice extends TestCase {

    protected static Object test;
    protected static int failures = 0;

    protected static Throwable thrown;
    
    static {
        Logger root = Logger.getLogger("");
        root.addHandler(new Handler() {
            @Override
            public void publish(LogRecord record) {
                thrown = record.getThrown();
                System.out.println("thrown=" + thrown);
            }
            @Override
            public void close() throws SecurityException {
            }
            @Override
            public void flush() {
            }
        });
        
    }
    
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
        System.out.println("SelfTestOneJarGuice: testHello()");
        Invoker.invoke(test, "sayHello");
        System.out.println("thrown=" + thrown);
        if (thrown != null) {
            throw new RuntimeException(thrown);
        }
    }
    
}
