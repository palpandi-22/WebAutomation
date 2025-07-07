package com.openmrs.utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.openmrs.driver.DriverManager;
import com.openmrs.enums.PropertyLoader;



public class Helper {

	static String colorCodeValidator;
	private static final Object lock = new Object();
	protected WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
	public static final int MAX_RETRIES = 3;
	public static final int CONNECTION_TIMEOUT = 5000; // 5 seconds

	//private static final Logger logger = Logger.getLogger(Helper.class.getName());

	/**
	 * @author Palpandi
	 * @param locator the locator used to find the elements
	 * @return a list of found WebElements
	 * @implNote This method fetches a group of elements located by the specified locator.
	 *            If a StaleElementReferenceException occurs, it refreshes the page and attempts to find the elements again.
	 */
	public List<WebElement> findElements(By locator) {
		try {
			return DriverManager.getDriver().findElements(locator);
		} catch (StaleElementReferenceException e) {
			DriverManager.getDriver().navigate().refresh();
			return DriverManager.getDriver().findElements(locator);
		}
	}


	/**
	 * @author Palpandi
	 * @param configPath The path to the properties file.
	 * @return A Properties instance loaded with the contents of the specified file.
	 * @implNote This method creates a new Properties instance with the contents from the given file path.
	 * @throws IOException If an I/O error occurs while reading the properties file.
	 */
	public Properties getPropertyAccess(String configPath) throws IOException {
		Properties prop = new Properties();
		FileInputStream path = new FileInputStream(configPath);
		prop.load(path);
		return prop;
	}

	
	/**
	 * @author Palpandi
	 * @param configPath The PropertyLoader object containing the path to the properties file.
	 * @param propertyName The name of the property whose value is to be retrieved.
	 * @return The value associated with the specified property name, or null if not found.
	 * @implNote This method retrieves the value from the properties file using the provided property name and file path.
	 * @throws IOException If an I/O error occurs while accessing the properties file.
	 */
	public String getValueFromProperty(PropertyLoader configPath, String propertyName) throws IOException {
		Properties prop;
		prop = getPropertyAccess(configPath.getValue());
		return prop.getProperty(propertyName);
	}

	
	/**
	 * @author Palpandi
	 * @param configPath The PropertyLoader object containing the path to the properties file.
	 * @param title The property name to be saved.
	 * @param value The value to be associated with the property name.
	 * @implNote This method saves a specified value in the properties file by providing
	 *           the property name, location, and absolute path.
	 * @throws IOException If an I/O error occurs while reading or writing to the properties file.
	 */
	public void saveOnProperties(PropertyLoader configPath, String title, String value) {
		try {
			FileInputStream in = new FileInputStream(configPath.getValue());
			Properties props = new Properties();
			props.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream(configPath.getValue());
			props.setProperty(title, value);
			props.store(out, null);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getAttribute(By locator, String AttributeName) {
		WebElement element = DriverManager.getDriver().findElement(locator);
		return element.getAttribute(AttributeName);
	}
	
	public int getCountOfElements(By element) {
		return DriverManager.getDriver().findElements(element).size();

	}

	
	/**
	 * @author Palpandi
	 * @usage  Converts an integer representing a month into its corresponding short name.
	 * @param  num the integer representing the month (1 for January, 2 for February, ..., 12 for December)
	 * @return the short name of the month (e.g., "Jan" for January) if the input is valid; 
	 *         returns "Invalid month number" if the input is less than 1 or greater than 12.
	 * @implNote This method uses the `DateFormatSymbols` class to retrieve an array of short month names. 
	 *           It checks if the input number is within the valid range (1-12) before accessing the array.
	 *           If the input is valid, it returns the corresponding month name, adjusted for zero-based indexing.
	 */
	public String getMonthForInt(int num) {
		if (num < 1 || num > 12) {
			return "Invalid month number";
		}
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getShortMonths();
		return months[num - 1]; // num - 1 to convert to zero-based index
	}


}
