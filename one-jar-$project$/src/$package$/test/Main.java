package $package$.test;

import com.simontuffs.onejar.test.Testable;

public class Main extends Testable {
    
    public static void main(String args[]) throws Exception {
        Main main = new Main();
        main.runTests();
    }
    
    public void test$project$() {
        System.out.println("test$project$: OK");
    }
    
}
