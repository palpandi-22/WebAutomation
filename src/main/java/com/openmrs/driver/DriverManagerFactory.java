package com.openmrs.driver;

import com.openmrs.enums.DriverType;

public class DriverManagerFactory {

	
	/**
	 * 
	 * @author Palpandi
	 * 
	 * Get a driver manager instance based on the specified driver type.
	 *
	 * This method returns a driver manager instance specific to the given driver type.
	 *
	 * @param driverType The type of driver for which a manager is needed.
	 * @return A driver manager instance corresponding to the provided driver type.
	 * @throws IllegalArgumentException if an invalid or unsupported driver type is provided.
	 */
	public static DriverManager_OC getManager(DriverType driverType) {
	    switch (driverType) {
	        case CHROME:
	            return new DriverManagerChrome();
	        case FIREFOX:
	            return new DriverManagerFirefox();
	        case EDGE:
	            return new DriverManagerEdge();
	        case SAFARI:
	            return new DriverManagerSafari();
	        case OPERA:
	            return new DriverManagerOpera();
	        default:
	            throw new IllegalArgumentException("Invalid Driver: " + driverType);
	    }
	}


}
