package com.simontuffs.onejar.test.maven.TestMaven;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.simontuffs.onejar.test.maven.SimpleLogging;

/**
 * Unit test for simple App.
 */
public class SimpleLoggingTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SimpleLoggingTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SimpleLoggingTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testSimpleLogging()
    {
        // TODO: install logging filters and watch for expected output.
        SimpleLogging.main(new String[0]);
    }
}
