package com.openmrs.utils;


import static com.openmrs.constants.FrameworkConstants.EXTENT_REPORT_FOLDER_PATH;
import static com.openmrs.constants.FrameworkConstants.EXTENT_REPORT_NAME;
import static com.openmrs.constants.FrameworkConstants.NO;



public final class ReportPathUtils {
	private ReportPathUtils() {
	}

	public static String createExtentReportPath() { 
		if (ConfigLoader.getInstance().getOverrideReports().equalsIgnoreCase(NO) && EXTENT_REPORT_NAME.isEmpty()) {		
			return EXTENT_REPORT_FOLDER_PATH + OSInfoUtils.getOSInfo() + "_" + DateUtils.getCurrentDate() ;
		} else if (ConfigLoader.getInstance().getOverrideReports().equalsIgnoreCase(NO) && EXTENT_REPORT_NAME != null) {
			return EXTENT_REPORT_FOLDER_PATH + EXTENT_REPORT_NAME;
		} else {
			return EXTENT_REPORT_FOLDER_PATH + "AutomationReport";
		}
	}

}
