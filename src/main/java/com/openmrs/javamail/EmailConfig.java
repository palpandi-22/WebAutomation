package com.openmrs.javamail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.openmrs.constants.FrameworkConstants;
import com.openmrs.enums.ProjectType;



/**
 * 
 * @author Palpandi
 * 
 * Configuration constants for email and email server settings.
 */

public class EmailConfig {

	public static final String SERVER = "";
    public static final String PORT   = "587";

    public static final String FROM = "";
    public static final String PASSWORD = "";

	public static final String[] TO = { "palpandi022@gmail,com"};
 
    public static final String SUBJECT = FrameworkConstants.getProjectName();

    public static final String host = "imap.gmail.com";
    public static final String mailStoreType = "imap";
    
    public static final String username = "palpandi022@gmail.com";
    public static final String password = "Pal@1234";

   
    /**
     * 
     * @author Palpandi
     * 
     * Retrieve email addresses from a properties file based on the specified module types.
     *
     * @param filePath The path to the properties file.
     * @param moduleTypes An array of module types to determine which email addresses to extract.
     * @return An array of unique email addresses extracted from the specified modules.
     * @throws IOException If there's an issue with file access.
     * @implNote This method reads the properties file at the specified path and extracts email addresses based on the
     * module types provided in the array. It returns an array of unique email addresses. Email addresses are expected
     * to be stored as comma-separated values in the properties file.
     */
	public String[] getEmailsFromProperties(String filePath, ProjectType[] moduleTypes) throws IOException {
		Set<String> emailSet = new HashSet<>();
		for (ProjectType module : moduleTypes) {
			String emails = getValueFromProperty(filePath, module.toString());
			List<String> emailsForModule = Arrays.asList(emails.split(","));
			emailSet.addAll(emailsForModule);
		}

		return emailSet.toArray(new String[0]);
	}

	public static String getValueFromProperty(String configPath, String propertyName) throws IOException {
		Properties prop;
		prop = getPropertyAccess(configPath);
		return prop.getProperty(propertyName);
	}
	
	public static Properties getPropertyAccess(String configPath) throws IOException {
		Properties prop = new Properties();
		FileInputStream path = new FileInputStream(configPath);
		prop.load(path);
		return prop;
	}
	
	
}
