package com.simontuffs.onejar.test.log4j;

import java.lang.reflect.Method;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;
import com.simontuffs.onejar.test.log4j.Main;

public class SelfTestOneLog4jSuite {

    protected static Object test;
    protected static int failures = 0;
    
    public static final String MAIN_CLASS = "com.simontuffs.onejar.test.log4j.Main";

    public static void main(String args[]) {
        new TestSuite(SelfTestOneLog4jSuite.class).run(new TestResult());
    }
    
    public static Test suite() throws Exception {
        if (test == null) {
            // Load Test object from the jar.
            JarClassLoader loader = new JarClassLoader("");
            Boot.setClassLoader(loader);
            // loader.setVerbose(true);
            loader.load(null);
            test = loader.loadClass(MAIN_CLASS).newInstance();
        }
        TestSuite top = new TestSuite();
        TestSuite suite = new TestSuite(MAIN_CLASS);
        top.addTest(suite);
        Method methods[] = test.getClass().getMethods();
        for (final Method method: methods) {
            if (method.getName().startsWith("test")) {
                System.out.println("" + method);
                suite.addTest(new TestCase() {
                    {
                        setName(method.getName());
                    }
                    @Override
                    public void run(TestResult result) {
                        // TODO Auto-generated method stub
                        result.startTest(this);
                        try {
                            Invoker.invoke(test, method.getName());
                        } catch (Exception x) {
                            x.printStackTrace();
                            result.addFailure(this, new AssertionFailedError(x.toString()));
                        }
                        result.endTest(this);
                    }
                });
            }
        }
        
        return suite;
    }
    
}
