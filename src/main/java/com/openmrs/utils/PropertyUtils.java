package com.openmrs.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.openmrs.enums.PropertyLoader;


public final class PropertyUtils {
	
	
	
	/**
	 * @author Palpandi
     * @Usage Loads properties from the specified file path.
     * @param filePath the path to the properties file.
     * @return a Properties object containing the loaded properties.
     * @throws RuntimeException if the properties file is not found or cannot be loaded.
     */
	public static Properties propertyLoader(String filePath) {
		Properties properties = new Properties();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new RuntimeException("Properties file not found at: " + filePath);
		}
		try {
			properties.load(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load the Properties file: " + filePath);
		}
		return properties;
	}
	

	
	/**
	 * @author Palpandi
	 * @implNote Clears all properties in the specified properties file associated with
	 *           the provided PropertyLoader.
	 * @Description This method reads the properties file, clears all existing
	 *              properties, and saves the empty properties back to the file. It is
	 *              designed to remove all variables from the specified properties file.
	 * @param loader The PropertyLoader object associated with the properties file
	 *               to be cleared.
	 * @throws IOException If an I/O error occurs while reading or writing to the
	 *                     properties file.
	 */
	public void clearProperties(PropertyLoader loader) {

		try (FileInputStream fileInputStream = new FileInputStream(loader.getValue())) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			properties.clear();

			try (FileOutputStream fileOutputStream = new FileOutputStream(loader.getValue())) {
				properties.store(fileOutputStream, null);
			}

			System.out.println("All variables removed from the properties file.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}


