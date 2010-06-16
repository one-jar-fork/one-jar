package com.simontuffs.onejar.inversion.test;

import com.simontuffs.onejar.inversion.test.external.Inversion;
import com.simontuffs.onejar.test.Testable;

public class Main extends Testable {
    
    public static void main(String args[]) throws Exception {
        Main main = new Main();
        main.runTests();
    }
    
    public void testInversion() {
        System.out.println("testInversion: OK");
    }
    
    public void testCallIntoOneJar() {
        System.out.println("calling out to Inversion()");
        new Inversion().callIntoOneJar();
    }
}
