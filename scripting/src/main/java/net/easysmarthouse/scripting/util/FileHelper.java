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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public static File[] getFiles(String folderName, FilenameFilter filter) throws IOException {
        String context = folderName.split(":")[0] + ":";
        String folderWithoutContext = folderName.split(":")[1];

        File folder = null;
        if (context.equalsIgnoreCase("file:")) {
            folder = new File(folderWithoutContext);
            if (!folder.exists()) {
                throw new IOException("Folder [" + folder.getAbsolutePath() + "] doesnt exist");
            }
        } else if (context.equalsIgnoreCase("classpath:")) {
            URL url = FileHelper.class.getResource(folderWithoutContext);
            try {
                folder = new File(url.toURI());
            } catch (URISyntaxException ex) {
                throw new IllegalStateException(ex);
            }
        }

        if (folder != null) {
            File[] scripts = folder.listFiles(filter);
            return scripts;
        }

        return null;
    }

}
