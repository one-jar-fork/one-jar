package com.simontuffs.onejar.test.jdbc.main;

import com.simontuffs.onejar.JarClassLoader;
import com.simontuffs.onejar.test.Testable;

public class Main extends Testable {
    
    public static void main(String args[]) throws Exception {
        Main main = new Main();
        main.runTests();
    }
    
    public void testJdbc() {
        System.out.println("testJdbc: OK");
    }
    
    public void testLoadDerbyDriver() throws Exception {
        ClassLoader cl = this.getClass().getClassLoader();
        System.out.println("classloader=" + cl);
        Class jdbc = Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        System.out.println("testLoadDerbyDriver: Loaded " + jdbc);
    }

    public void testLoadMySqlDriver() throws Exception {
        ClassLoader cl = this.getClass().getClassLoader();
        System.out.println("classloader=" + cl);
        Class jdbc = Class.forName("com.mysql.jdbc.Driver");     
        System.out.println("testLoadMySqlDriver: Loaded " + jdbc);
    }

}
