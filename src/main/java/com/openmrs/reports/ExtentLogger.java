package com.openmrs.reports;

import static com.openmrs.constants.FrameworkConstants.YES;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import com.openmrs.utils.ConfigLoader;
import com.openmrs.utils.ScreenshotUtils;

public final class ExtentLogger {

	private ExtentLogger() {
	}

	public static synchronized void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}

	public static synchronized void pass(Markup message) {
		ExtentManager.getExtentTest().pass(message);
	}

	public static synchronized void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
	}

	public static synchronized void fail(Markup message) {
		ExtentManager.getExtentTest().fail(message); 
	}

	public static synchronized void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}
 
	public static synchronized void skip(Markup message) {
		ExtentManager.getExtentTest().skip(message);
	}

	public static synchronized void info(Markup message) {
		ExtentManager.getExtentTest().info(message);
	}

	public static synchronized void info(String message) {
		ExtentManager.getExtentTest().info(message);
	}

	public static synchronized void pass(String message, boolean isScreeshotNeeded) {

		if (ConfigLoader.getInstance().getPassedStepsScreenshot().equalsIgnoreCase(YES) && isScreeshotNeeded) {
			ExtentManager.getExtentTest().pass(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		} else {
			pass(message);
		}
	}

	public static synchronized void pass(Markup message, boolean isScreeshotNeeded) {

		if (ConfigLoader.getInstance().getPassedStepsScreenshot().equalsIgnoreCase(YES) && isScreeshotNeeded) {

			ExtentManager.getExtentTest().pass(
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
			ExtentManager.getExtentTest().pass(message);
		} else {
			pass(message);
		}
	}

	public static synchronized void fail(String message, boolean isScreeshotNeeded) {

		if (ConfigLoader.getInstance().getFailedStepsScreenshot().equalsIgnoreCase(YES) && isScreeshotNeeded) {
			ExtentManager.getExtentTest().fail(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		} else {
			fail(message);
		}
	}

	public static synchronized void fail(Markup message, boolean isScreeshotNeeded) {

		if (ConfigLoader.getInstance().getFailedStepsScreenshot().equalsIgnoreCase(YES) && isScreeshotNeeded) {

			ExtentManager.getExtentTest().fail(
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
			ExtentManager.getExtentTest().fail(message);
		} else {
			fail(message);
		}
	}

	public static synchronized void skip(String message, boolean isScreeshotNeeded) {

		if (ConfigLoader.getInstance().getSkippedStepsScreenshot().equalsIgnoreCase(YES) && isScreeshotNeeded) {
			ExtentManager.getExtentTest().skip(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		} else {
			skip(message);
		}
	}

	public static synchronized void skip(Markup message, boolean isScreeshotNeeded) {

		if (ConfigLoader.getInstance().getSkippedStepsScreenshot().equalsIgnoreCase(YES) && isScreeshotNeeded) {

			ExtentManager.getExtentTest().skip(
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
			ExtentManager.getExtentTest().skip(message);
		} else {
			skip(message);
		}
	}

}
