package com.openmrs.utils;

public final class BrowserOSInfoUtils {
	
	// Private constructor to prevent instantiation
    private BrowserOSInfoUtils() {
    }

    /**
     * Fetches the running OS details along with the browser name and version,
     * and returns them as a formatted string.
     *
     * @return a string containing OS details, browser name, and browser version.
     * @author Palpandi
     * @implNote This method retrieves OS information from OSInfoUtils and
     *           browser information from BrowserInfoUtils, combining them into a single string.
     */
    public static String getOS_Browser_BrowserVersionInfo() {
        return OSInfoUtils.getOSInfo() + " & " +
               BrowserInfoUtils.getBrowserInfo() + " - " +
               BrowserInfoUtils.getBrowserVersionInfo();
    }
}
