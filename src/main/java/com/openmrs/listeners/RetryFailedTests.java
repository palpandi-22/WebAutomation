package com.openmrs.listeners;


import static com.openmrs.constants.FrameworkConstants.YES;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.openmrs.utils.ConfigLoader;


public class RetryFailedTests implements IRetryAnalyzer {

	private int count = 0;
	private int retries = Integer.parseInt(System.getProperty("retry", "1"));
   
	
	/**
	 * 
	 * @author Palpandi
	 * 
	 * This method is responsible for deciding whether a test should be retried if it fails.
	 *
	 * @param result The result of the test that just ran, including information about the test method.
	 * @return True if the test should be retried; false otherwise.
	 */
	@Override
	public boolean retry(ITestResult result) { 
	    boolean value = false;

	    // Check if test retry is enabled in the configuration
	    if (ConfigLoader.getInstance().getRetryFailedTests().equalsIgnoreCase(YES)) {
	        // Check if the maximum retry count (retries) has not been exceeded
	        if (count < retries) {
	            count++;
	            return true; // Retry the test
	        }
	    }

	    return value; // Don't retry the test
	}


}
