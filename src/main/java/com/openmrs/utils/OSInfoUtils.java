package com.openmrs.utils;
/**
 *
 * @author Palpandi
 * @implNote Method will return current machine os name
 */
public final class OSInfoUtils {

	private OSInfoUtils() {}

	public static String getOSInfo() {
		return System.getProperty("os.name").replace(" ", "_");
	}

}
