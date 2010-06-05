package com.simontuffs.onejar.guice.test;

import junit.framework.TestCase;

import com.simontuffs.onejar.test.Invoker;
import com.simontuffs.onejar.test.Invoker.Result;

public class SelfTestEclipseGuice extends TestCase {

    protected static Object test;

    public void testEclipseGuice() throws Exception {
        Result result = Invoker.run("java -jar build/test-eclipse-guice.jar");
        System.out.println(result.toString());
        assertTrue("Expected pass did not occur: " + result, result.status == 0);
    }
    
}
