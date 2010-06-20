package $package$.test;

import com.simontuffs.onejar.test.Testable;

public class Test extends Testable {
    
    public static void main(String args[]) throws Exception {
        Test test = new Test();
        test.runTests();
    }
    
    // Test other aspects of the application at unit level (e.g. library
    // methods).
    public void test$project$1() {
        System.out.println("test$project$1: OK");
    }
    public void test$project$2() {
        System.out.println("test$project$2: OK");
    }
    public void test$project$3() {
        System.out.println("test$project$3: OK");
    }
    
}
