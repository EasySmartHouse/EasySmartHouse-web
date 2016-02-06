/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.ginger.smarthome.hid.device.natives.util;

import by.ginger.smarthome.hid.TestUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author rusakovich
 */
public class DllManagerTest {
    
	@AfterClass
	public static void teardown() {
		System.setProperty(DllManager.LIB_DIR_OVERRIDE, "");
	}
	
	@Test
	public void testFindLibFileOverride() throws IOException {
		String path = TestUtil.TEST_DATA_FOLDER+"win";
		System.setProperty(DllManager.LIB_DIR_OVERRIDE, path);
		File actual = DllManager.findLibFile();
		assertTrue(actual.getAbsolutePath().contains(path));
	}

	@Test
	public void testFindLibFileDefault() throws IOException {
		System.setProperty(DllManager.LIB_DIR_OVERRIDE, "");
		File actual = DllManager.findLibFile();
		assertTrue(actual.getAbsolutePath().contains(File.separator+"temper"));
	}

	@Test
	@Ignore // We now only clean files older than one day. Issue 52
	public void testCleanupTempFiles() throws IOException {
		File f1 = File.createTempFile(DllManager.TEMP_FILE_PREFIX+"_ABC", DllManager.DLL_EXTENSION);
		assertTrue(f1.exists());
		DllManager.cleanupTempFiles();
		assertFalse(f1.exists());
	}
	
	@Test
	public void testFileCopy() throws IOException {
		File originalFile = new File(TestUtil.TEST_DATA_FOLDER+"win"+File.separator+"temper_w32.dll");
		File tempFile = File.createTempFile(DllManager.TEMP_FILE_PREFIX, DllManager.TEMP_FILE_PREFIX);
		DllManager.copy(new FileInputStream(originalFile), new FileOutputStream(tempFile));
		assertTrue(tempFile.exists() && tempFile.length() == originalFile.length());
		tempFile.delete();
	}
    
}
