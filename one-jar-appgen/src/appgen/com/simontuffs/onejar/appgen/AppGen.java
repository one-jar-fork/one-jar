/*
 * Copyright (c) 2004-2010, P. Simon Tuffs (simon@simontuffs.com)
 * All rights reserved.
 *
 * See the full license at http://one-jar.sourceforge.net/one-jar-license.html
 * This license is also included in the distributions of this software
 * under doc/one-jar-license.txt
 */  
package com.simontuffs.onejar.appgen;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class AppGen {
    
    public static String toCamelCase(String string) {
        String toks[] = string.split("-");
        String camel = "";
        for (String tok: toks) {
            camel += Character.toUpperCase(tok.charAt(0)) + tok.substring(1);
        }
        return camel;
    }
    
    public static void main(String args[]) throws Exception {
        String project, pkg;
        if (args.length == 2) {
            project = args[0];
            pkg = args[1];
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter project path (project name is last segment): "); System.out.flush();
            project = br.readLine();
            System.out.print("Enter java package name: "); System.out.flush();
            pkg = br.readLine();
        }
        File dir = new File(project);
        if (dir.exists()) 
            throw new Exception("Directory " + dir + " already exists");
        dir.mkdirs();
        project = dir.getName();
        String pkgdir = pkg.replace(".", "/");
        if (pkg.contains("-") || pkg.contains("/"))
            throw new Exception("Illegal Java package name: " + pkg);
        String camel = toCamelCase(project);
        String under = project.replace("-", "_");

        JarInputStream is;
        try {
            is = new JarInputStream(AppGen.class.getClassLoader().getResourceAsStream("one-jar-$project$.jar"));
        } catch (Exception x) {
            is = new JarInputStream(new FileInputStream("one-jar-$project$.jar"));
        }
        JarEntry entry = is.getNextJarEntry();
        while (entry != null) {
            String name = entry.getName();
            if (name.equals("META-INF"))
                continue;
            if (entry.isDirectory()) {
                name = name.replace("$package$",pkgdir)
                    .replace("$project$", under);
                File file = new File(dir, name);
                System.out.println("mkdir " + file);
                file.mkdir();
            } else if (name.endsWith(".jar")) {
                File file = new File(dir, name);
                file.getParentFile().mkdirs();
                OutputStream os = new FileOutputStream(file);
                InputStream bis = new BufferedInputStream(is);
                byte buf[] = new byte[256];
                int len;
                while ((len=bis.read(buf)) >= 0) {
                    os.write(buf, 0, len);
                }
                os.close();
                
            } else {
                name = name.replace("$package$", pkgdir)
                    .replace("$project$Suite", camel + "Suite")
                    .replace("$project$-suite", project + "-suite")
                    .replace("test-$project$", "test-" + project)
                    .replace("$project$Main", camel + "Main")
                    .replace("$project$", under);
                File file = new File(dir, name);
                file.getParentFile().mkdirs();
                name = entry.getName().replace("$package$",pkg).replace("OneJar$project$", "OneJar" + camel).replace("test$project$", "test" + camel).replace("$project$", under);
                FileWriter fw = new FileWriter(file);
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("entry: " + name.replace("$project$", under));
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.replace("$package$",pkg).replace("OneJar$project$", "OneJar" + camel).replace("test$project$", "test" + camel)
                        .replace("$project$Suite", camel + "Suite")
                        .replace(".$project$\"", "." + project + "\"")
                        .replace("test-$project$.jar", "test-" + project + ".jar")
                        .replace("name=\"one-jar-$project$\"", "name=\"" + project + "\"")
                        .replace("<name>one-jar-$project$</name>", "<name>"+project+"</name>")
                        .replace("$project$Main", camel + "Main")
                        .replace("$project$", under);
                    System.out.println(line);
                    fw.write(line + "\n");
                }
                fw.close();
            }
            entry = is.getNextJarEntry();
        }
    }

}
