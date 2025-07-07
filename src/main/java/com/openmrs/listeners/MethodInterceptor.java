package com.openmrs.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.openmrs.annotations.FrameworkAnnotation;
import com.openmrs.enums.CategoryType;
import com.openmrs.enums.ProjectType;
import com.openmrs.utils.ConsoleTimer;

/** 
 * Custom Test Case Interceptor for filtering test methods based on specified categories and modules.
 */

public class MethodInterceptor implements IMethodInterceptor {
	private static final String MASS_EXECUTION_WARNING = "CANCEL THE BUILD BEFORE THE TIMER STOPS, IF YOU DID IT MISTAKENLY";
	private static final int DELAY_TIME = 100;

	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		List<IMethodInstance> result = new ArrayList<>();

		String category = System.getProperty("testCategory", "");
		String module = System.getProperty("testModule", "");

		Set<String> testCategory = new HashSet<>(Arrays.asList(category.split(",")));
		Set<String> testModule = new HashSet<>(Arrays.asList(module.split(",")));

		    System.out.println("##############################################################################");
		    System.out.println("                                                                               "); 
		    System.out.println("                               TEST CASES                                      ");
		    System.out.println("                                                                                "); 
		if (!category.isEmpty() && !module.isEmpty()) {
			System.out.println("          TEST CATEGORIES    : " + Arrays.toString(testCategory.toArray()));
		} else if(category.isEmpty() && module.isEmpty()){
			System.out.println("          CATGEORY IS NULL , THIS MIGHT LEAD TO OVERALL PROJECT EXECUTION     ");
			System.out.println("                                                                              ");
			System.out.println("##############################################################################");
		}

		for (IMethodInstance method : methods) {
			FrameworkAnnotation annotation = method.getMethod().getConstructorOrMethod().getMethod()
					.getDeclaredAnnotation(FrameworkAnnotation.class);

			if (annotation != null) {
				List<CategoryType> categoryList = Arrays.asList(annotation.category());
				List<ProjectType> moduleList = Arrays.asList(annotation.module());

				if (!module.isEmpty() && !category.isEmpty()) {
					for (ProjectType actualModule : moduleList) {
						for (String expectedModule : testModule) {
							if (actualModule.toString().equalsIgnoreCase(expectedModule)) {
								for (CategoryType actualCategory : categoryList) {
									for (String expectedCategory : testCategory) {
										if (actualCategory.toString().equalsIgnoreCase(expectedCategory)
												&& (!result.contains(method))) {
											result.add(method);
										}
									}
								}
							}
						}
					}
				}  
				else if (!module.isEmpty() && category.isEmpty()) {
					for (ProjectType actualModule : moduleList) {
						for (String expectedModule : testModule) {
							if (actualModule.toString().equalsIgnoreCase(expectedModule)
									&& (!result.contains(method))) {
								result.add(method);
							}
						}
					}
				} else if (category.isEmpty() && module.isEmpty()) {
					printBulkExecutionWarning();
					break;
				}
			}
		}
		
		int size = result.isEmpty() ? methods.size() : result.size();

		System.out.println("#############################################################################");
	    System.out.println("                                                                              "); 
		System.out.println( size + " TEST CASES GOING TO EXECUTE... APPROXIMATELY IT WILL TAKE : " + calculateExecutionTime(size));
	    System.out.println("                                                                              "); 
		System.out.println("#############################################################################");

		return category.isEmpty() && module.isEmpty() ? methods : result;
	}

	private void printBulkExecutionWarning() {
		ConsoleTimer countdownTimer = new ConsoleTimer();
		countdownTimer.printWithDelay(MASS_EXECUTION_WARNING, DELAY_TIME);
	}

	private String calculateExecutionTime(int numTestCases) {
		if (numTestCases >= 120) {
			return numTestCases * 30 / 3600 + " Hours TO COMPLETE ";
		} else if (numTestCases >= 3) {
			return numTestCases * 30 / 60 + " Minutes TO COMPLETE ";
		} else {
			return numTestCases * 30 + " Seconds TO COMPLETE ";
		}
	}
}
	    
	



