/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.scripting.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author rusakovich
 */
public final class FileHelper {

    private static final Charset CHARSET_DEFAULT = Charset.defaultCharset();

    private FileHelper() {
    }

    public static String readFile(String path, String encoding) {
        Charset charset = Charset.forName(encoding);
        return readFile(path, charset);

    }

    /**
     * File content reading
     *
     * @param path - path to file
     * @param encoding - encoding
     * @return content
     */
    public static String readFile(String path, Charset encoding) {
        byte[] encoded = new byte[0];

        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException ex) {
            throw new RuntimeException("Cannot get the file: [" + path + "]", ex);
        }

        return new String(encoded, encoding);
    }

    /**
     *
     * @param path - path to file
     * @return content
     */
    public static String readFile(String path) {
        return readFile(path, CHARSET_DEFAULT);
    }

    public static String readFully(InputStream inputStream, String encoding)
            throws IOException {
        return new String(readFully(inputStream), encoding);
    }

    private static byte[] readFully(InputStream inputStream)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toByteArray();
    }

    public static String getExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i + 1);
        }

        return extension;
    }

    /**
     * List directory contents for a resource folder. Not recursive.
     * This is basically a brute-force implementation.
     * Works for regular files and also JARs.
     *
     * @author Greg Briggs
     * @param clazz Any java class that lives in the same place as the resources you want.
     * @param path Should end with "/", but not start with one.
     * @return Just the name of each member item, not the full paths.
     * @throws URISyntaxException
     * @throws IOException
     */
    private static String[] getResourceListing(Class clazz, String path) throws URISyntaxException, IOException {
        if (path == null){
            throw new NullPointerException("Path must not be null!");
        }
        String correctedPath = (!path.endsWith(File.separator)) ? path + File.separator : path;
        URL dirURL = clazz.getClassLoader().getResource(correctedPath);
        if (dirURL != null && dirURL.getProtocol().equals("file")) {
        /* A file path: easy enough */
            return new File(dirURL.toURI()).list();
        }

        if (dirURL == null) {
        /*
         * In case of a jar file, we can't actually find a directory.
         * Have to assume the same jar as clazz.
         */
            String me = clazz.getName().replace(".", "/")+".class";
            dirURL = clazz.getClassLoader().getResource(me);
        }

        if (dirURL.getProtocol().equals("jar")) {
        /* A JAR path */
            String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!")); //strip out only the JAR file
            JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
            Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
            Set<String> result = new HashSet<String>(); //avoid duplicates in case it is a subdirectory
            while(entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith(path)) { //filter according to the path
                    String entry = name.substring(path.length());
                    int checkSubdir = entry.indexOf("/");
                    if (checkSubdir >= 0) {
                        // if it is a subdirectory, we just return the directory name
                        entry = entry.substring(0, checkSubdir);
                    }
                    result.add(entry);
                }
            }
            return result.toArray(new String[result.size()]);
        }

        throw new UnsupportedOperationException("Cannot list files for URL "+dirURL);
    }

    /**
     * Getting the names of files in folderPath
     *
     * @param folderPath - folder in classpath, absolute or relative path
     * @param filter - filter to select files
     * @return the name of each member item, not the full paths.
     * @throws IOException
     */
    public static String[] getFiles(String folderPath, FilenameFilter filter) throws IOException {
        String context = folderPath.split(":")[0] + ":";
        String folderWithoutContext = folderPath.split(":")[1];

        File folder = null;
        //if folder path is absolute or relative
        if (context.equalsIgnoreCase("file:")) {
            folder = new File(folderWithoutContext);
            if (!folder.exists()) {
                throw new IOException("Folder [" + folder.getAbsolutePath() + "] doesn't exist");
            }
            File[] filteredFiles = folder.listFiles(filter);
            String[] filteredFileNames = new String[filteredFiles.length];
            for (int i = 0; i < filteredFiles.length; i++) {
                filteredFileNames[i] = filteredFiles[i].getName();
            }
            return filteredFileNames;
        //folder is in classpath
        } else if (context.equalsIgnoreCase("classpath:")) {
            try {
                String[] fileNames =  getResourceListing(FileHelper.class, folderWithoutContext);
                List<String> filteredFileNames = new ArrayList<>(fileNames.length);
                for (int i = 0; i < fileNames.length; i++) {
                    if (filter.accept(null, fileNames[i])){
                        filteredFileNames.add(fileNames[i]);
                    }
                }
                return filteredFileNames.toArray(new String[filteredFileNames.size()]);
            } catch (URISyntaxException ex) {
                throw new IllegalStateException(ex);
            }
        }

        return null;
    }

}
