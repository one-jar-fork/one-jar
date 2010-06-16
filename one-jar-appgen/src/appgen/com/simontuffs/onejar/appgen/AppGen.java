package com.simontuffs.onejar.appgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
        if (args.length < 2) 
            throw new Exception("Usage: appgen <project-name> <package-name>");
        String project = args[0];
        String pkg = args[1];
        String pkgdir = pkg.replace(".", "/");
        if (pkg.contains("-") || pkg.contains("/"))
            throw new Exception("Illegal Java package name: " + pkg);
        String camel = toCamelCase(project);
        String under = project.replace("-", "_");
        File dir = new File("../" + project);
        if (dir.exists()) 
            throw new Exception("Directory " + dir + " already exists");
        dir.mkdirs();
        
        
        JarFile jar = new JarFile("one-jar-$project$.jar");
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            if (name.equals("META-INF"))
                continue;
            if (entry.isDirectory()) {
                name = name.replace("$package$",pkgdir).replace("OneJar$project$", "OneJar" + camel).replace("test$project$", "test" + camel).replace("$project$", under);
                File file = new File(dir, name);
                System.out.println("mkdir " + file);
                file.mkdir();
            } else {
                name = name.replace("$package$", pkgdir).replace("OneJar$project$", "OneJar" + camel).replace("$project$", under);
                File file = new File(dir, name);
                file.getParentFile().mkdirs();
                name = entry.getName().replace("$package$",pkg).replace("OneJar$project$", "OneJar" + camel).replace("test$project$", "test" + camel).replace("$project$", under);
                FileWriter fw = new FileWriter(file);
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("entry: " + name.replace("$project$", under));
                BufferedReader br = new BufferedReader(new InputStreamReader(jar.getInputStream(entry)));
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.replace("$package$",pkg).replace("OneJar$project$", "OneJar" + camel).replace("test$project$", "test" + camel)
                        .replace("name=\"$project$\"", "name=\"" + project + "\"")
                        .replace("<name>$project$</name>", "<name>"+project+"</name>")
                        .replace("$project$", under);
                    System.out.println(line);
                    fw.write(line + "\n");
                }
                fw.close();
            }
        }
    }

}
