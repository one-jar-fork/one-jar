package com.simontuffs.onejar.test.log4j;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Utils {
    
    static {
        try {
            PropertyConfigurator.configure(new URL("onejar:log4j.properties"));
        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }
    
    protected static Logger log = Logger.getLogger(Utils.class);
    
    public static void sayHello() {
        log.debug("sayHello() debug enabled");
        log.info("sayHello() info enabled");
    }
}
