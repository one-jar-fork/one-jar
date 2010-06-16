/*
 * Created on Jun 5, 2005
 *
 */

package test;

import com.simontuffs.onejar.test.Testable;

/**
 * @author simon
 * Test class for JNI access to a C++ class in a DLL.
 */
public class Test extends Testable {
    
    static {
        try {
            String path = System.getProperty("one-jar.dll.path", "");
            System.out.println("Test: loading native code");
            System.loadLibrary(path + "one-jar-dll");
            System.out.println("Test: native code loaded");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    native String hello();
    native String echo(String message);
   
    public void testHello() {
        System.out.println("Test: " + hello());
    }
    
    public void testEcho() {
        String sent = "Test: hello from Java!";
        String expected = "echo(" + sent + ") (c++ strings)";
        String echo = echo(sent);
        if (!expected.equals(echo)) {
            fail("Echo'd message '" + echo + "' was not as expected '" + expected + "'");
        }
        System.out.println("Test: " + echo(sent));
        System.out.println("Test: Done.");
    }
    
    public static void main(String args[]) {
        Test test = new Test();
        test.testHello();
        test.testEcho();
    }

}
