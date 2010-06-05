One-Jar Java-Help Demo
----------------------

This demonstrates the use of One-Jar to package Java-Help demo examples.  To build
the demo, you must first download the Java-Help 2.0 distribution: search for 
"javahelp 2.0 download", and you should find a download link on this page:
 
    http://java.sun.com/javase/technologies/desktop/javahelp/download_binary.html
    
The current (March 2010) release is javahelp-2_0_05.zip.  Unpack this distribution 
"next" to this example, and build it using ant.  If you put it elsewhere, edit the 
build.xml file javahelp.dir to point to it.

This will result in a One-Jar package: dist/one-jar-javahelp.jar, which contains two
main entry points (taken from the Java-Help demo/bin directory)

  * idedemo.jar  -- An example of a simple (fake) IDE with JavaHelp integration
  * hsviewer.jar -- A simple help-set viewer.

The default app is idedemo, you run this using "java -jar one-jar-javahelp.jar".

To run the other demo, use: "java -jar -Done-jar.main.app=hsviewer one-jar-java-help.jar".

Enjoy.
