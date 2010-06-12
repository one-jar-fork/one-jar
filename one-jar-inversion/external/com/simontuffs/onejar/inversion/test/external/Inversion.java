package com.simontuffs.onejar.inversion.test.external;

import com.simontuffs.onejar.inversion.test.Main;

public class Inversion {
    
    // Depends on classes inside a One-Jar, but is present in a Jar file external
    // to the One-Jar.  Tests ability to support classloader inversion.
    
    public void callIntoOneJar() {
        new Main().testInversion();
    }
    
}
