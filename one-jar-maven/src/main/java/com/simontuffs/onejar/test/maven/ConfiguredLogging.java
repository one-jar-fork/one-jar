package com.simontuffs.onejar.test.maven;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class ConfiguredLogging 
{
	static Logger log = Logger.getLogger(ConfiguredLogging.class);
    public static void main( String[] args )
    {
        System.out.println("Hello from one-jar-maven");
        log.info("info from log4j");
        log.warn("warn from log4j");
    }
}
