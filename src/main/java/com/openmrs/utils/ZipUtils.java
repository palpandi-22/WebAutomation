package com.openmrs.utils;

import static com.openmrs.constants.FrameworkConstants.EXTENT_REPORT_FOLDER_PATH;
import static com.openmrs.constants.FrameworkConstants.Zipped_ExtentReports_Folder_Name;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.zeroturnaround.zip.ZipUtil;

/**
 *
 * @author Palpandi
 * @implNote Method will create a Zip file with all the generated report
 */
public class ZipUtils {
	
	public static void zip() {
		ZipUtil.pack(new File(EXTENT_REPORT_FOLDER_PATH),
				new File(Zipped_ExtentReports_Folder_Name));

		System.out.println("Zipped ExtentReports folder successfully");
	}
	
	
   /**
	* @author Palpandi
    * @usage Unzips the specified ZIP file to the given destination directory.
    * @param zipFilePath The path to the ZIP file to be unzipped.
    * @param destDir The destination directory where the unzipped files will be stored.
    * @throws IOException If an I/O error occurs during the unzipping process.
    */
	public static void unzip(String zipFilePath, String destDir) throws IOException {
		File dir = new File(destDir);
		if (!dir.exists()) {
			dir.mkdirs(); // Create destination directory if it doesn't exist
		}

		try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
			ZipEntry entry;
			while ((entry = zipInputStream.getNextEntry()) != null) {
				File newFile = new File(destDir, entry.getName());

				// Create directories for subdirectories in ZIP
				if (entry.isDirectory()) {
					newFile.mkdirs();
				} else {
					// Create parent directories if necessary
					new File(newFile.getParent()).mkdirs();
					// Write file content
					try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile))) {
						byte[] buffer = new byte[1024];
						int len;
						while ((len = zipInputStream.read(buffer)) > 0) {
							bos.write(buffer, 0, len);
						}
					}
				}
				zipInputStream.closeEntry();
			}
		}
	}
	
	
	

 
}
