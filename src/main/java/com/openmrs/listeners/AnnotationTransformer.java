
package com.openmrs.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;


public class AnnotationTransformer implements IAnnotationTransformer {
	
	/**
	 * 
	 * @author Palpandi
	 * @usage Custom TestNG annotation transformer to enable test case retry upon failure.
	 * @param annotation The test annotation to be modified.
	 * @param testClass The test class where the annotation is applied.
	 * @param testConstructor The constructor of the test class.
	 * @param testMethod The test method where the annotation is applied.
	 * @implNote This method is called to transform test annotations before test execution. It sets a retry analyzer for the
	 *           - test, allowing it to be retried upon failure to address flaky tests.
	 */
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		/**
		 * I don't need to add these details with Test case
		 *
		 * @Test(dataProvider = "getData", dataProviderClass = DataProviderUtils.class)
		 */
		annotation.setRetryAnalyzer(RetryFailedTests.class);
		// annotation.setDataProvider("getData");
		// annotation.setDataProviderClass(DataProviderUtils.class);
	}

}
