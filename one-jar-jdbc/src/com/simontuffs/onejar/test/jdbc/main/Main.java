package com.simontuffs.onejar.test.jdbc.main;

import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.example.main.Testable;

public class Main extends Testable {
    
    public static void main(String args[]) throws Exception {
        Main main = new Main();
        main.runTests();
    }
    
    public void testJdbc() {
        System.out.println("testJdbc: OK");
    }
    
    public void testLoadDriver() throws Exception {
        ClassLoader cl = this.getClass().getClassLoader();
        System.out.println("classloader=" + cl);
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        System.out.println("testLoadDriver: Loaded class org.apache.derby.jdbc.EmbeddedDriver");
    }

}
