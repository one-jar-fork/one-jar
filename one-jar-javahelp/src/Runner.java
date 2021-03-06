

/*
 * @(#)Runner.java	1.4 06/10/30
 * 
 * Copyright (c) 2006 Sun Microsystems, Inc.  All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * 
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND
 * REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility. 
 */

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;
import java.util.jar.Manifest;
import java.util.jar.Attributes;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;

/**
 * Convience class for executing an application with parameters from a jar file.
 * This allows a single application to be invoked from multiple jar files. Two
 * additonal manifest attributes are utilized:
 * <UL>
 * <LI>Run-Class - The actual class to run
 * <LI>Arguments - a set of arguements to pass to the run class
 * </UL>
 * An example is below:
 * 
 * <pre>
 * Main-Class: Runner
 * Class-Path: ../../javahelp/lib/jh.jar hsviewer.jar ../lib/classviewer.jar ../hsjar/idehelp.jar ../hsjar/apidoc.jar
 * Run-Class: sunw.demo.jhdemo.JHLauncher
 * Arguments: -helpset api
 * </pre>
 * 
 */
public class Runner {

	public static void main(String[] args) {
		Runner run = new Runner();
		// [PST: 2010/3/15]
		// Fix for use with one-jar. System class-loader cannot see inside the
		// one-jar
		// and therefore fails to resolve the main class.
		// ClassLoader cl = ClassLoader.getSystemClassLoader();
		ClassLoader cl = Runner.class.getClassLoader();
		System.out.println("classloader=" + cl);
		Manifest mf;
		String runClass = null;
		String arguments = null;
		try {
			Enumeration enum1 = cl.getResources("META-INF/MANIFEST.MF");
			while (enum1.hasMoreElements()) {
				URL url = (URL) enum1.nextElement();
				InputStream manifestIS = url.openStream();
				mf = new Manifest(manifestIS);
				Attributes main = mf.getMainAttributes();
				runClass = main.getValue("Run-Class");
				arguments = main.getValue("Arguments");
				if (runClass != null) {
					break;
				}
			}
			if (args.length == 0) {
				args = getArgs(arguments);
			}
			System.out.print("Arguments: ");
			for (int i=0; i<args.length; i++) {
				System.out.print(args[i] + " ");
			}
			System.out.println();
			System.out.println("Run-Class: " + runClass);
			Class klass = cl.loadClass(runClass);
			Method m = klass.getMethod("main", new Class[] { args.getClass() });
			m.setAccessible(true);
			int mods = m.getModifiers();
			if (m.getReturnType() != void.class || !Modifier.isStatic(mods)
					|| !Modifier.isPublic(mods)) {
				throw new NoSuchMethodException("main");
			}
			m.invoke(null, new Object[] { args });
		} catch (IllegalAccessException e) {
			// This should not happen, as we have disable access checks
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException cex) {
			cex.printStackTrace();
		} catch (NoSuchMethodException nex) {
			nex.printStackTrace();
		} catch (InvocationTargetException iex) {
			iex.printStackTrace();
		}
	}

	/**
	 * Parse the arguments passed in the jar file into standard args format.
	 * Quoted argument "like this" are treated as a single argument.
	 */
	private static String[] getArgs(String arguments) {
		Vector args = new Vector();
		int startPos = 0, endPos = arguments.length();
		while (true) {
			int spacePos = arguments.indexOf(" ", startPos);
			int quotePos = arguments.indexOf("\"", startPos);
			if ((quotePos != -1 && spacePos < quotePos)
					|| (quotePos == -1 && spacePos != -1)) {
				args.add(arguments.substring(startPos, spacePos));
				startPos = spacePos + 1;
			} else if ((spacePos != 1 && quotePos < spacePos)
					|| (spacePos == -1 && quotePos != -1)) {
				int quotePos2 = arguments.indexOf("\"", quotePos + 1);
				if (quotePos2 == -1) {
					// skip the " and move on
					// user error in arguments
					startPos = quotePos + 1;
				} else {
					// get the stuff between the quotes
					args.add(arguments.substring(quotePos + 1, quotePos2 - 1));
					startPos = quotePos2 + 1;
				}
			} else {
				// spaces will always have something at the end. Check for this
				if (endPos != startPos) {
					args.add(arguments.substring(startPos, endPos));
				}
				// now we are assuming we're at the end
				break;
			}
		}
		// time to return
		String[] returnArgs = new String[args.size()];
		Enumeration enum1 = args.elements();
		for (int count = 0; enum1.hasMoreElements(); count++) {
			returnArgs[count] = (String) enum1.nextElement();
		}
		return returnArgs;
	}
}
