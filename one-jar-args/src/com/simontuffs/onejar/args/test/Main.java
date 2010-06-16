package com.simontuffs.onejar.args.test;

import java.util.Arrays;

import com.simontuffs.onejar.test.Testable;

public class Main extends Testable {
    
    protected static String[] args = new String[0];
    
    // Note: this method is not called during test-suite, so arguments are
    // not propagated. Run the one-jar directly to see the default arguments.
    public static void main(String args[]) throws Exception {
        Main.args = args;
        Main main = new Main();
        main.runTests();
    }
    
    public void testArgs() {
        System.out.println("testArgs: args=" + Arrays.asList(args));
    }
    
}
