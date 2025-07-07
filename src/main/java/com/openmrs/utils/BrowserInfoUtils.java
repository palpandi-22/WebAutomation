package com.openmrs.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.openmrs.driver.DriverManager;

public final class BrowserInfoUtils {

	// Private constructor to prevent instantiation
    private BrowserInfoUtils() {
    }

    /**
     * Fetches the currently running browser's name and returns it as a string.
     *
     * @return the name of the browser in uppercase.
     * @author Palpandi
     * @implNote This method retrieves the browser name from the capabilities of the currently running driver.
     */
    public static String getBrowserInfo() {
        Capabilities capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        return capabilities.getBrowserName().toUpperCase();
    }

    /**
     * Retrieves the version of the currently running browser and returns it as a string.
     *
     * @return the browser version.
     * @author Palpandi
     * @implNote This method fetches the browser version from the capabilities of the currently running driver.
     */
    public static String getBrowserVersionInfo() {
        Capabilities capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        return capabilities.getBrowserVersion();
    }

}
