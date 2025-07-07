package com.openmrs.utils;

import static com.openmrs.constants.FrameworkConstants.ICON_BROWSER_EDGE;
import static com.openmrs.constants.FrameworkConstants.ICON_BROWSER_PREFIX;
import static com.openmrs.constants.FrameworkConstants.ICON_BROWSER_SUFFIX;
import static com.openmrs.constants.FrameworkConstants.ICON_OS_LINUX;
import static com.openmrs.constants.FrameworkConstants.ICON_OS_MAC;
import static com.openmrs.constants.FrameworkConstants.ICON_OS_WINDOWS;




public final class IconUtils {

	private IconUtils() {
	}

	
   /**
	* @author Palpandi
	* @implNote This method returns the icon associated with the currently running browser.
	* @return A string representing the browser icon.
	*/
	public static String getBrowserIcon() {
		String browserInLowerCase = BrowserInfoUtils.getBrowserInfo().toLowerCase();
		if (browserInLowerCase.contains(ICON_BROWSER_EDGE)) {
			return ICON_BROWSER_PREFIX + ICON_BROWSER_EDGE + ICON_BROWSER_SUFFIX;
			//return "<i class='fa-brands fa-edge'></i>";
		} else {
			return ICON_BROWSER_PREFIX + browserInLowerCase + ICON_BROWSER_SUFFIX;
		}
	}


   /**
	* @author Palpandi
	* @implNote This method returns the icon associated with the current operating system of the machine.
	* @return A string representing the OS icon based on the current operating system.
	*/
	public static String getOSIcon() {

		String operSys = OSInfoUtils.getOSInfo().toLowerCase();
		if (operSys.contains("win")) {
			return ICON_OS_WINDOWS;
		} else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
			return ICON_OS_LINUX;
		} else if (operSys.contains("mac")) {
			return ICON_OS_MAC;
		}
		return operSys;
	}

}
