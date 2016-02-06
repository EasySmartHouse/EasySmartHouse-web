/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.cameras.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

/**
 *
 * @author rusakovich
 */
public class WebResourceUtil {
    
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SOURCE_PATH = "src/main/webapp/";

    private WebResourceUtil() {
    }

    public static URI findWebResourceBase(ClassLoader classLoader, String webResourceRef) {

        try {
            // Look for resource in classpath (best choice when working with archive jar/war file)
            URL webXml = classLoader.getResource(webResourceRef);
            if (webXml != null) {
                return webXml.toURI();
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException("Bad ClassPath reference for: " + webResourceRef, e);
        }

        // Look for resource in common file system paths
        try {
            Path pwd = new File(USER_DIR).toPath().toRealPath();
            FileSystem fs = pwd.getFileSystem();

            // Try the generated maven path first
            PathMatcher matcher = fs.getPathMatcher("glob:**/embedded-servlet-*");
            try (DirectoryStream<Path> dir = Files.newDirectoryStream(pwd.resolve("target"))) {
                for (Path path : dir) {
                    if (Files.isDirectory(path) && matcher.matches(path)) {
                        // Found a potential directory
                        Path possible = path.resolve(webResourceRef);
                        // Does it have what we need?
                        if (Files.exists(possible)) {
                            return possible.getParent().toUri();
                        }
                    }
                }
            }

            // Try the source path next
            Path srcWebapp = pwd.resolve(SOURCE_PATH + webResourceRef);
            if (Files.exists(srcWebapp)) {
                return srcWebapp.getParent().toUri();
            }
            
        } catch (Throwable t) {
            throw new RuntimeException("Unable to find web resource in file system: " + webResourceRef, t);
        }

        throw new RuntimeException("Unable to find web resource ref: " + webResourceRef);
    }

}
