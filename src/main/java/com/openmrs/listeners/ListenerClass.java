package com.openmrs.listeners;

import static com.openmrs.constants.FrameworkConstants.ICON_BUG;
import static com.openmrs.constants.FrameworkConstants.ICON_SMILEY_FAIL;
import static com.openmrs.constants.FrameworkConstants.ICON_SMILEY_PASS;
import static com.openmrs.constants.FrameworkConstants.YES;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.openmrs.annotations.FrameworkAnnotation;
import com.openmrs.base.BasePage;
import com.openmrs.driver.DriverManager;
import com.openmrs.reports.ExtentLogger;
import com.openmrs.reports.ExtentReport;
import com.openmrs.utils.BrowserOSInfoUtils;
import com.openmrs.utils.ConfigLoader;
import com.openmrs.utils.EmailSendUtils;
import com.openmrs.utils.IconUtils;
import com.openmrs.utils.Internet;
import com.openmrs.utils.ZipUtils;




/**
 *
 * @author Palpandi
 * @implNote Class will listen the entire test case execution  , listen and generate the data based on the pass , fail and skipped status
 */
public class ListenerClass implements ITestListener , ISuiteListener {


	 static int count_passedTCs;
	 static int count_skippedTCs;
	 static int count_failedTCs;
	 static int count_totalTCs;


	 /**
	  * Initialize the extent reports at the start of a TestNG suite.
	  *
	  * @param suite The TestNG suite that is starting.
	  * @implNote This method is called at the beginning of a TestNG suite execution. It initializes the extent reports
	  * that are used for generating detailed test reports. This is typically done to capture and report test results.
	  */
	 @Override
	 public void onStart(ISuite suite) {
	     ExtentReport.initReports();
	 } 

 
	 /**
	  * @author Palpandi
	  * 
	  * This method is called when a TestNG suite execution is finished.
	  * It performs several tasks, such as flushing test reports, compressing results,
	  * and sending an email with summary information.
	  *
	  * @param suite The TestNG suite for which the execution is finished.
	  */
	 @Override 
	 public void onFinish(ISuite suite) {
	     try {
	         // Flush Extent Reports to generate the test report
	         ExtentReport.flushReports();
	     } catch (IOException e) {
	         // Handle and print any IOException that occurs during flushing
	         e.printStackTrace();
	     }

	     // Compress test results into a ZIP file using custom utility
	     ZipUtils.zip();
	     // Send an email with counts of total, passed, failed, and skipped test cases
	     EmailSendUtils.sendEmail(count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs);
	     
	 }


	 /**
	  * 
	  * @author Palpandi
	  * 
	  * This method is called at the start of a test case.
	  * It performs various tasks such as checking internet connectivity,
	  * initializing test counters, creating an Extent report for non-setup methods,
	  * and adding information about authors, categories, modules, and devices to the report.
	  *
	  * @param result The result of the test case, including information about the test method.
	  */
	 @Override
	 public void onTestStart(ITestResult result) {
	     // Check for internet connection, and if not available, stop test execution
	     if (!Internet.check_Internet_Connection()) {
	         System.out.println("Internet connection not available. Stopping test execution...");
//	         System.exit(0);
	     }

	     // Increment the total test case count
	     count_totalTCs = count_totalTCs + 1;

	     // Create an Extent report entry for non-setup methods
	     if (!"entirePageLoading".equals(result.getMethod().getMethodName())) {
	         ExtentReport.createTest(result.getMethod().getMethodName());
	     }

	     // Add authors, categories, and modules information to the report
	     ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod()
	             .getAnnotation(FrameworkAnnotation.class).author());
	     ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod()
	             .getAnnotation(FrameworkAnnotation.class).category());
	     ExtentReport.addModules(result.getMethod().getConstructorOrMethod().getMethod()
	             .getAnnotation(FrameworkAnnotation.class).module());

	     // Add device information to the report
	     ExtentReport.addDevices();

	     // Log information about the operating system and browser
	     ExtentLogger.info("<b>" + IconUtils.getOSIcon() + "  &  " + IconUtils.getBrowserIcon() + " --------- "
	             + BrowserOSInfoUtils.getOS_Browser_BrowserVersionInfo() + "</b>");
	 }


	   /**
		* 
		* @author   Palpandi
		* @implSpec This method is called when a test case succeeds. It increments the
		*           count of passed test cases, logs the success, and attaches console errors.
		*
		* @param result The result of the test case, including information about the
		*               test method.
		*/
		@Override
		public void onTestSuccess(ITestResult result) {
			// Increment the count of passed test cases
			count_passedTCs = count_passedTCs + 1;

			// Generate a log message for the passed test case and add a success icon
			String logText = "<b>" + result.getMethod().getMethodName() + " is passed.</b>" + "  " + ICON_SMILEY_PASS;
			Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			ExtentLogger.pass(markup_message);

			// Attach console errors, if any
			attach_Console_Error();

		}


		/**
		 * 
		 * @author Palpandi
		 * 
		 * @implSpec This method is called when a test case fails. It increments the
		 *           count of failed test cases, logs the failure and exception details,
		 *           and attaches console errors. Additionally, it sends a failure email
		 *           if configured.
		 * @param result The result of the test case, including information about the
		 *               test method and any exceptions.
		 */
		
	    @Override
		public void onTestFailure(ITestResult result) {
			
	    	// Increment the count of failed test cases
			count_failedTCs = count_failedTCs + 1;
			
			// Log the test failure, including an icon and exception details
			ExtentLogger.fail(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");

			// Prepare and log exception details in a collapsible section
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			String message = "<details><summary><b><font color=red> Exception occurred, click to see details: "
					+ ICON_SMILEY_FAIL + " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
					+ "</details> \n";
			ExtentLogger.fail(message);

			// Log the test case as failed with a label and attach console errors
			String logText = "<b>" + result.getMethod().getMethodName() + " is failed.</b>" + "  " + ICON_SMILEY_FAIL;
			Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.RED);
			ExtentLogger.fail(markup_message);

    		attach_Console_Error();

		}

 
	 /**
	  * 
	  * @author Palpandi
	  * 
	  * This method is called when a test case is skipped.
	  * It increments the count of skipped test cases, logs the skip and exception details,
	  * and attaches console errors.
	  *
	  * @param result The result of the test case, including information about the test method and any exceptions.
	  */
	 @Override
	 public void onTestSkipped(ITestResult result) {
	     // Increment the count of skipped test cases
	     count_skippedTCs = count_skippedTCs + 1;

	     // Log the test case as skipped with an icon and exception details
	     ExtentLogger.skip(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");

	     // Create and log a message indicating the skipped test case with a label
	     String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>" + "  " + ICON_SMILEY_FAIL;
	     Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
	     ExtentLogger.skip(markup_message, true);
	     // Attach console errors, if any
	     attach_Console_Error();
	  
	 }
		/**
		 * 
		 * @author    Palpandi
		 * @implSpec This method is a utility function used to attach console error
		 *         - information to the test report. It retrieves the console error
		 *         - messages using a utility class, checks for the presence of errors,
		 *         - and attaches them to the report as a collapsible section if errors are found.
		 */

		private void attach_Console_Error() {

			try {
				// Retrieve error messages
				Set<String> errorMessages = new BasePage(DriverManager.getDriver()).processErrorLogEntries();
				// Check if error messages exist and are not empty
				if (errorMessages != null && !errorMessages.isEmpty()) {

					// Prepare and log console error details in a collapsible section
					String consoleMessage = "<details><summary><b><font color=red> Console Error occurred, click to see details: "
							+ ICON_SMILEY_FAIL + " </font></b>" + "</summary>" + errorMessages + "</details> \n";
					ExtentLogger.info(consoleMessage);

			   }

			} catch (Exception e) {
				// Log any exceptions that occur during the method execution
				System.out.println("An error occurred while attaching console error:");
			}
		}

		@SuppressWarnings("unused")
		private String getUrlStringFromParameters(ITestResult result) {
			// Retrieve the parameters passed to the test method
			Object[] parameters = result.getParameters();

			if (parameters != null && parameters.length > 0 && parameters[0] instanceof String) {
				// Check if parameters exist and the first parameter is a String
				String parameterUrl = (String) parameters[0];
				// Check if the parameter URL starts with "https://"
				if (parameterUrl.startsWith("https://")) {
					// If true, return the parameter URL
					return parameterUrl;
				}
			}

			// If no valid parameter URL starting with "https://" is found,
			// retrieve the current URL using DriverManager and return it
			String url = DriverManager.getDriver().getCurrentUrl();
			return url;
		}


}
