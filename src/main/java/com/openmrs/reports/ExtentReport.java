package com.openmrs.reports;

import static com.openmrs.constants.FrameworkConstants.ICON_LAPTOP;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.openmrs.constants.FrameworkConstants;
import com.openmrs.enums.AuthorType;
import com.openmrs.enums.CategoryType;
import com.openmrs.enums.ProjectType;
import com.openmrs.utils.BrowserInfoUtils;
import com.openmrs.utils.IconUtils;

public final class ExtentReport {

   private ExtentReport() {
	   
   }

   private static final Object lock = new Object();
   private static ExtentReports extent;

   public static  void initReports() {

		synchronized (lock) {

			if (Objects.isNull(extent)) {
				extent = new ExtentReports();
				ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
				extent.attachReporter(spark);
				spark.config().setEncoding("utf-8");
				spark.config().setTheme(Theme.STANDARD);
				spark.config().setDocumentTitle(FrameworkConstants.getReportTitle());
				spark.config().setReportName(FrameworkConstants.getProjectName());
				extent.setSystemInfo("Organization", "<span style='font-weight: bold; color: #FFA500;'>OpenMRS.com</span>");
				extent.setSystemInfo("Employee", "<span style='font-weight: bold; color: #87CEEB;'>Palpandi.R</span>");
				extent.setSystemInfo("Domain", "<span style='font-weight: bold; color: #FFA500;'>QA Automation</span> " + ICON_LAPTOP);
				extent.setSystemInfo("Designation", "<span style='font-weight: bold; color: #87CEEB;'>QA Automation Tester</span>");
			}
		}
   }

   
   
	/**
	 * @author : Palpandi
	 * @Description : Removes previously generated reports.
	 * @throws : IOException If an I/O error occurs while removing reports.
	 */
	
	public static void flushReports() throws IOException {
		synchronized (lock) {
			if (Objects.nonNull(extent)) {
				extent.flush();
			}
			ExtentManager.unload();

			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					desktop.browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
				} else {
					System.out.println("Browsing is not supported on this platform.");
				}
			} else {
				System.out.println("Desktop is not supported on this platform.");
			}
		}
	}

   
	public static void createTest(String testCaseName) {
		synchronized (lock) {
			if (Objects.isNull(extent)) {
				extent = new ExtentReports();
			}
			ExtentManager.setExtentTest(extent.createTest(IconUtils.getBrowserIcon() + "  : " + testCaseName));
		}
	}

    public static synchronized void addAuthors(AuthorType[] authors) {
		for (AuthorType author : authors) {
			ExtentManager.getExtentTest().assignAuthor(author.toString());
		}
	}

	public static synchronized void addCategories(CategoryType[] categories) {

		for (CategoryType category : categories) {
			ExtentManager.getExtentTest().assignCategory(category.toString());
		}
	}

	public static synchronized void addModules(ProjectType[] modules) {

		for (ProjectType module : modules) {
			ExtentManager.getExtentTest().assignCategory(module.toString());
		}
	}

	public static synchronized void addDevices() {
		ExtentManager.getExtentTest().assignDevice(BrowserInfoUtils.getBrowserInfo());
	}

}
