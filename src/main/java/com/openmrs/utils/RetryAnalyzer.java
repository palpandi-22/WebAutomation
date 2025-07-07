package com.openmrs.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 *
 * @author Palpandi
 * @implNote Method will analyze the test case execution , if any test case fails method will ,make it run again based on the maxTry value
 */
public class RetryAnalyzer implements IRetryAnalyzer{

	private int count = 0;
    private static int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (!result.isSuccess())
		{
			if (result.getMethod().getMethodName().equals("Entire_Page_Loading")) {

				System.out.println(" Entered inside Retry skip ");
				result.setStatus(ITestResult.FAILURE);
            }else if (count < maxTry)
			{
				count++;
				System.out.println("===========================================================");
				System.out.println(" Retry Triggered "+result.getMethod().getMethodName());
				System.out.println("===========================================================");
				return true;
			}
			else
			{
				result.setStatus(ITestResult.FAILURE);
			}
		} else
		{
			result.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}


	}


