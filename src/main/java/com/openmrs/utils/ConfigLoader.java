package com.openmrs.utils;

import java.util.Properties;

import com.openmrs.enums.EnvType;


   /**
    * @author      - Palpandi
    * @description - ConfigLoader is responsible for loading and configuring the
    *              - environment settings required for test case execution.
    */
    public class ConfigLoader {
    	
    	
    	 private Properties properties;
         public static String Configfilelocation;
     	 public static ConfigLoader configLoader;

     	 private static final String ENV 							    =  "env";
         private static final String CONFIG_PROPERTIES 				    =  "_config.properties";
    	 private static final String BASE_URL 						    =  "baseUrl";
    	 private static final String USERNAME 						    =  "username";
    	 private static final String PASSWORD 						    =  "password";
    	 private static final String GENERATE_TEST_STEPS_ON_REPORT	    =  "generate_test_Steps_On_report";
         private static final String PASSED_STEPS_SCREENSHOT 		    =  "passed_steps_screenshot";
     	 private static final String FAILED_STEPS_SCREENSHOT 		    =  "failed_steps_screenshot";
         private static final String SKIPPED_STEPS_SCREENSHOT 		    =  "skipped_steps_screenshot";
     	 private static final String RETRY_FAILED_TESTS 				=  "retry_failed_tests";
         private static final String OVERRIDE_REPORTS 			        =  "override_reports";
     	 private static final String REQUEST_DETAILS_IN_REPORTS 		=  "request_details_in_reports";
     	 private static final String START_EXECUTION_FROM_SIGNUP        =  "start_execution_from_signup";
     	 private static final String DELETE_FIRM_AFTER_BUILD            =  "delete_firm_after_build";
     	 private static final String SEND_FAILED_TEST_EMAIL             =  "send_failed_test_email";
         private static final String SEND_EMAIL_TO_USERS 			    =  "send_email_to_users";
      
         
         // Default config files
         private static final String OPENMRS_CONFIG_PROPERTIES               = "openmrs"   + CONFIG_PROPERTIES;
     	 private static final String RESOURCES_PATH                      = System.getProperty("user.dir") + "/src/test/resources/Config_Properties/";
     	
     	 /**
     	  * @author       - Palpandi
     	  * @Description  - Setting EnvType.STAGE as default environment 
     	  *               - This will check for the env value from Jenkins/Maven first. If no input is 
     	  *               - provided from Jenkins/Maven command line, it will default to using 
     	  *               - stg_config.properties file.
     	  */

     	  private ConfigLoader() {
     	   
     	    String env = System.getProperty(ENV, EnvType.OPENMRS.toString());

     	    switch (EnvType.valueOf(env)) {
     	        case OPENMRS: {
     	            Configfilelocation = RESOURCES_PATH + OPENMRS_CONFIG_PROPERTIES;
     	            properties = getConfigPropertyFile(OPENMRS_CONFIG_PROPERTIES);
     	            break;
     	        }
     	    
     	        default: {
     	            throw new IllegalStateException("Invalid EnvType: " + env);
     	        }
     	    }
     	}


		private Properties getConfigPropertyFile(String configFile) {
			return PropertyUtils.propertyLoader(RESOURCES_PATH + configFile);
		}

	
		private String getPropertyValue(String propertyKey) {
			String prop = properties.getProperty(propertyKey);
			if (prop != null) {
				return prop.trim();
			} else {
				throw new RuntimeException(
						"Property " + propertyKey + " is not specified in the config.properties file ");
			}
		}

	
		public static ConfigLoader getInstance() {
			if (configLoader == null) {
				configLoader = new ConfigLoader();
			}
			return configLoader;
		}

	
	public String getBaseUrl() {
		return getPropertyValue(BASE_URL);
	}

	public String getUsername() {
		return getPropertyValue(USERNAME);
	}

	public String getPassword() {
		return getPropertyValue(PASSWORD);
	}
	
	public String getGenerateTestStepsOnReport() {
		return getPropertyValue(GENERATE_TEST_STEPS_ON_REPORT);
	}
	
	public String getPassedStepsScreenshot() {
		return getPropertyValue(PASSED_STEPS_SCREENSHOT);
	}

	public String getFailedStepsScreenshot() {
		return getPropertyValue(FAILED_STEPS_SCREENSHOT);
	}

	public String getSkippedStepsScreenshot() {
		return getPropertyValue(SKIPPED_STEPS_SCREENSHOT);
	}

	public String getRetryFailedTests() {
		return getPropertyValue(RETRY_FAILED_TESTS);
	}

	public String getOverrideReports() {
		return getPropertyValue(OVERRIDE_REPORTS);
	}

	public String getRequestDetailsInReports() {
		return getPropertyValue(REQUEST_DETAILS_IN_REPORTS);
	}
	
	public String getStartExecutionFromSignup() {
		return getPropertyValue(START_EXECUTION_FROM_SIGNUP);
	}

	public String getSignupFirmDelete() {
		return getPropertyValue(DELETE_FIRM_AFTER_BUILD);
	}

	public String getSendFailedTestEmail() {
		return getPropertyValue(SEND_FAILED_TEST_EMAIL);
	}
	
	public String getSendEmailToUsers() {
		return getPropertyValue(SEND_EMAIL_TO_USERS);
	}
	
	
	public String getLoginUserName() {
		return USERNAME;
	}
	
	public String getLoginPassword() {
		return PASSWORD;
	}
	
	public String getConfigFileLocation() {
		return Configfilelocation;
	}
	
	

}
