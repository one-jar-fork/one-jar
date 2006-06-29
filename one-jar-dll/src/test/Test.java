/*
 * Created on Jun 5, 2005
 *
 */

package test;

/**
 * @author simon
 * Test class for JNI access to a C++ class in a DLL.
 */
public class Test {
    
    static {
        try {
            System.out.println("Test: loading native code");
            System.loadLibrary("one-jar-dll");
            System.out.println("Test: native code loaded");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    native String hello();
    native String echo(String message);
   
    public static void main(String args[]) {
        Test test = new Test();
        System.out.println("Test: " + test.hello());
        System.out.println("Test: " + test.echo("Test: hello from Java!"));
        System.out.println("Test: Done.");
    }

}
