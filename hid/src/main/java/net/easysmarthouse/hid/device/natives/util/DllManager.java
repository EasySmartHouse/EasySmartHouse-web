/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.hid.device.natives.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author mirash
 */
public class DllManager {

    private static final Log log = LogFactory.getLog(DllManager.class);
    private static final int MILLIS_PER_DAY = 86400000;

    private static final class TempDLLFileFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            String name = pathname.getName();
            return pathname.isFile()
                    && name.startsWith(TEMP_FILE_PREFIX)
                    && name.endsWith(DLL_EXTENSION);
        }
    }
    public static final String LIB_DIR_OVERRIDE = "hid_natives_lib_dir";
    static final String TEMP_FILE_PREFIX = "temper";
    static final String DLL_EXTENSION = ".dll";
    static String LIB_NAME_BASE = "temper_";
    static final String DEFAULT_LIB_FOLDER = "lib";

    public static File findLibFile() throws IOException {
        String libName = buildLibName();
        File libFile = getOverrideLibFile(libName);
        if (libFile == null || libFile.exists() == false) {
            libFile = getDefaultLibFile(libName);
        }
        if (libFile == null || libFile.exists() == false) {
            libFile = extractToTempFile(libName);
        }
        return libFile;
    }

    public static void cleanupTempFiles() {
        try {
            String tempFolder = System.getProperty("java.io.tmpdir");
            if (tempFolder == null || tempFolder.trim().length() == 0) {
                return;
            }
            File fldr = new File(tempFolder);
            File[] oldFiles = fldr.listFiles(new TempDLLFileFilter());
            if (oldFiles == null) {
                return;
            }
            for (File tmp : oldFiles) {
                if ((System.currentTimeMillis() - tmp.lastModified()) > MILLIS_PER_DAY) {
                    tmp.delete();
                }
            }
        } catch (Exception e) {
            log.error("Error cleaning up temporary dll files. ", e);
        }
    }

    private static File getDefaultLibFile(String libName) {
        return new File(DEFAULT_LIB_FOLDER, libName);
    }

    private static File getOverrideLibFile(String libName) {
        String libDir = System.getProperty(LIB_DIR_OVERRIDE);
        if (libDir == null || libDir.trim().length() == 0) {
            return null;
        }
        return new File(libDir, libName);
    }

    static File extractToTempFile(String libName) throws IOException {
        InputStream source = DllManager.class.getResourceAsStream("/" + DEFAULT_LIB_FOLDER + "/" + libName);
        File tempFile = File.createTempFile(TEMP_FILE_PREFIX, DLL_EXTENSION);
        tempFile.deleteOnExit();
        FileOutputStream destination = new FileOutputStream(tempFile);
        copy(source, destination);
        return tempFile;
    }

    private static void closeStream(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (IOException e) {
            // Ignore cleanup errors
        }
    }

    static void copy(InputStream source, OutputStream dest)
            throws IOException {
        try {
            byte[] buffer = new byte[1024];
            int read = 0;
            while (read >= 0) {
                dest.write(buffer, 0, read);
                read = source.read(buffer);
            }
            dest.flush();
        } finally {
            closeStream(source);
            closeStream(dest);
        }
    }

    private static String buildLibName() {
        String arch = "w32";
        if (!System.getProperty("os.arch").equals("x86")) {
            arch = System.getProperty("os.arch");
        }
        return LIB_NAME_BASE + arch + DLL_EXTENSION;
    }
}
