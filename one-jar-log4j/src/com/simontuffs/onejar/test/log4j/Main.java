package com.simontuffs.onejar.test.log4j;

import org.apache.log4j.Logger;

import com.simontuffs.onejar.example.main.Testable;

public class Main extends Testable {
    
    public static Logger log = Logger.getLogger(Main.class);
    
    public static void main(String args[]) throws Exception {
        log.debug("log4j.debug is enabled");
        log.info("log4j.info is enabled");
        log.warn("log4j.warn is enabled");
        log.error("log4j.error is enabled");
        Main main = new Main();
        main.runTests();
    }
    
    public void testlog4j() {
        System.out.println("testlog4j: OK");
    }
    
    public void testUtilsSayHello() {
        Utils.sayHello();
    }
    
}
