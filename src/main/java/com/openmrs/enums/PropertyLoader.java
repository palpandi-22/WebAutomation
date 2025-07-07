package com.openmrs.enums;

import com.openmrs.utils.ConfigLoader;

/**
 * 
 * @author Palpandi
 * 
 *         The {@code PropertyLoader} enum associates property file paths with
 *         different modules or components within a software application. These
 *         predefined constants provide easy access to the file paths for
 *         configuration files associated with each module.
 *
 *         Usage:
 * 
 *         <pre>
 * @code   Access the property file path for the CFR (Continuous Feedback and Recognition) module
 *         String cfrPropertyPath = PropertyLoader.CFR.getValue();
 *         
 *  Use the property path for loading module-specific configuration settings.
 *  Example: Load and configure settings related to the CFR module.
 * 
 * </pre>
 */


public enum PropertyLoader {

	OPENMRS            (System.getProperty("user.dir") + "/src/main/java/com/openmrs/properties/openmrs.properties"),
	CONFIG_FILE_LOCATION (ConfigLoader.getInstance().getConfigFileLocation());

	private String value;

	private PropertyLoader(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}

