package com.openmrs.constants;

import com.openmrs.utils.ReportPathUtils;

/**
 * 
 * @author Palpandi
 * @implNote All the frameWork constants will be maintained here
 */
public class FrameworkConstants {

	
	private static final String PROJECT_PATH = System.getProperty("user.dir");

	
	/* ICONS - START */
	public static final String ICON_SMILEY_PASS       =  "<i class='fa fa-smile-o' style='font-size:24px'></i>";
	public static final String ICON_SMILEY_SKIP       =  "<i class=\"fas fa-frown-open\"></i>";
	public static final String ICON_SMILEY_FAIL       =  "<i class='fa fa-frown-o' style='font-size:24px'></i>";
	public static final String ICON_URL_RUNNING       =  "<i class='fas fa-sync-alt'></i>";
	public static final String ICON_OS_WINDOWS        =  "<i class='fa fa-windows' ></i>";
	public static final String ICON_OS_MAC            =  "<i class='fa fa-apple' ></i>";
	public static final String ICON_OS_LINUX          =  "<i class='fa fa-linux' ></i>";
	public static final String ICON_Navigate_Right    =  "<i class='fa fa-arrow-circle-right' ></i>";
	public static final String ICON_LAPTOP            =  "<i class='fa fa-laptop' style='font-size:18px'></i>";
	public static final String ICON_BUG               =  "<i class='fa fa-bug' ></i>";
	public static final String CODECAMP_ICON          =  "<i class=\"fa fa-free-code-camp\" aria-hidden=\"true\"></i>";
	public static final String ICON_CAMERA            =  "<i class=\"fa fa-camera\" aria-hidden=\"true\"></i>";
	public static final String ICON_BROWSER_EDGE      =  "edge";
	public static final String ICON_BROWSER_PREFIX    =  "<i class=\"fa fa-";
	public static final String ICON_BROWSER_SUFFIX    =  "\" aria-hidden=\"true\"></i>";

	
	/* ICONS - END */
	public static final String ASSERTION_FOR_RESPONSE_STATUS_CODE  = "Assertion for Response Status Code";
	public static final String ASSERTION_FOR_RESPONSE_HEADER       = "Assertion for Response Headers";
	public static final String ASSERTION_FOR_RESPONSE_CUSTOM_FIELD = "Assertion for Response Custom Field";

	public static final String YES = "yes";
	public static final String NO  = "no";
	
	private static final int EXPLICIT_WAIT  = Integer.parseInt(System.getProperty("explicitWait", "45"));
	public static final int  WAIT           = Integer.parseInt(System.getProperty("wait	", "30"));

	public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + "/ExtentReports/";
	public static final String EXTENT_REPORT_NAME        = System.getProperty("projectName","Automation Report By PALPANDI");
	private static final String REPORT_EXTENSION         = ".html";

	/**
	 * API CONSTANTS
	 */
	
	public static final String SESSIONKEY      = "sessionKey";
	private static String extentReportFilePath = "";

	/** Zip file of Extent Reports */
	
	public static final String Zipped_ExtentReports_Folder_Name = "ExtentReports.zip";

	private static final String Project_Name   = "    openMRS.com";
    private static final String Report_Title   = " openMRS Web Automation Report";
	
	public static String getReportTitle() {
		return Report_Title;
	}
	
	public static String getProjectPath() {
		return PROJECT_PATH;
	}

	public static String getProjectName() {
		if (EXTENT_REPORT_NAME.isEmpty()) {
			return Project_Name;
		} else {
			return Project_Name + " [ " + EXTENT_REPORT_NAME + " ]";
		}
	}

	public static String getZipped_ExtentReports_Folder_Name() {
		return Zipped_ExtentReports_Folder_Name;
	}

	public static String getExtentReportFilePath() {
		if (extentReportFilePath.isEmpty()) {
			extentReportFilePath = ReportPathUtils.createExtentReportPath() + REPORT_EXTENSION;
		}
		return extentReportFilePath;
	}

	public static int getExplicitWait() {
		return EXPLICIT_WAIT;
	}
	
	public static int getWait() {
		return WAIT;
	}

	
}
