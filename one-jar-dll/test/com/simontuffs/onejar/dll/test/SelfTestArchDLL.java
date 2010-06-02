package com.simontuffs.onejar.dll.test;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.simontuffs.onejar.Boot;
import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Invoker;

public class SelfTestArchDLL extends SelfTestDLL {

    public static void main(String args[]) {
        new TestSuite(SelfTestArchDLL.class).run(new TestResult());
    }
    
    public SelfTestArchDLL() throws Exception {
        if (test == null) {
            // Load Test object from the jar.
            JarClassLoader loader = new JarClassLoader("");
            Boot.setClassLoader(loader);
            // loader.setVerbose(true);
            loader.load(null);
            test = loader.loadClass("test.Test").newInstance();
        }
    }
    
}
