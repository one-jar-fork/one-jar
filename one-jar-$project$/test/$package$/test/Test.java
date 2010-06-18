package $package$.test;

import com.simontuffs.onejar.test.Testable;

public class Test extends Testable {
    
    public static void main(String args[]) throws Exception {
        Test test = new Test();
        test.runTests();
    }
    
    public void test$project$() {
        System.out.println("test$project$: OK");
    }
    
}
