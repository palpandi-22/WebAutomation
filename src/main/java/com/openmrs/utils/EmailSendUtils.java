package com.openmrs.utils;



import static com.openmrs.constants.FrameworkConstants.YES;
import static com.openmrs.javamail.EmailConfig.FROM;
import static com.openmrs.javamail.EmailConfig.PASSWORD;
import static com.openmrs.javamail.EmailConfig.PORT;
import static com.openmrs.javamail.EmailConfig.SERVER;
import static com.openmrs.javamail.EmailConfig.SUBJECT;
import static com.openmrs.javamail.EmailConfig.TO;

import javax.mail.MessagingException;

import com.openmrs.constants.FrameworkConstants;
import com.openmrs.javamail.EmailAttachmentsSender;


public class EmailSendUtils {

   /**
	* @author Palpandi
	* @param count_totalTCs
	* @param count_passedTCs
	* @param count_failedTCs
	* @param count_skippedTCs
	* @implNote Method will sent the email once all the test case got executed
	*/
	public static void sendEmail(int count_totalTCs, int count_passedTCs, int count_failedTCs, int count_skippedTCs) {

		if (ConfigLoader.getInstance().getSendEmailToUsers().equalsIgnoreCase(YES) &&  ConfigLoader.getInstance().getSendEmailToUsers().isEmpty()) {
			    System.out.println("****************************************");
			    System.out.println("                                        ");
			    System.out.println("           SEND EMAIL - START           ");
			    System.out.println("                                        ");
			    System.out.println("****************************************");
			    String messageBody = getTestCasesCountInFormat(
			    		             count_totalTCs, 
							         count_passedTCs, 
							         count_failedTCs,
					                 count_skippedTCs);
			    System.out.println("File Path      : " + FrameworkConstants.getExtentReportFilePath());
			    System.out.println("Result Status  : " + messageBody);
			    String attachmentFile_ExtentReport = FrameworkConstants.getExtentReportFilePath();
			try {
				EmailAttachmentsSender.sendEmailWithAttachments(SERVER , PORT , FROM , PASSWORD , TO , SUBJECT , messageBody , attachmentFile_ExtentReport);
				System.out.println("****************************************");
				System.out.println("                                        ");
				System.out.println("                                        ");
				System.out.println("         EMAIL SEND SUCCESSFULLY !      ");
				System.out.println("                                        ");
				System.out.println("           SEND EMAIL - END             ");
				System.out.println("                                        ");
				System.out.println("****************************************");
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}
		else {
			   System.out.println("THE EMAIL OPTION WILL BE TURNED OFF FOR THE PROJECT....");
		}

	}


	/**
	 * @author Palpandi
	 * @param count_totalTCs
	 * @param count_passedTCs
	 * @param count_failedTCs
	 * @param count_skippedTCs
	 * @implNote Method will generate the UI with all test case status or to attach this HTMl design when sent email once the
	 *           all test execution done
	 * @return
	 */
	private static String getTestCasesCountInFormat(int count_totalTCs, int count_passedTCs, int count_failedTCs,
			int count_skippedTCs) {
		System.out.println("count_totalTCs: " + count_totalTCs);
		System.out.println("count_passedTCs: " + count_passedTCs);
		System.out.println("count_failedTCs: " + count_failedTCs);
		System.out.println("count_skippedTCs: " + count_skippedTCs);

		return "<html>\r\n" + "\r\n" + " \r\n" + "\r\n"
				+ "        <body> \r\n<table class=\"container\" align=\"center\" style=\"padding-top:20px\">\r\n<tr align=\"center\"><td colspan=\"4\"><h2>"
				+ FrameworkConstants.getProjectName() + "</h2></td></tr>\r\n<tr><td>\r\n\r\n"
				+ "       <table style=\"background:#67c2ef;width:120px\" >\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_totalTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_passedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Passed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "                <table style=\"background:#ff5454;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_failedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Failed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_skippedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Skipped</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                </tr>\r\n" + "               \r\n" + "                \r\n"
				+ "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>";
	}












/**
 * @author Palpandi
 * @param testName
 * @param exceptio
 * @implNote Method will trigger the email if any particulat test case got failed
 * @param testData
 */
	public static void sendFailEmail(String testName, String exception, String testData ,String[] TO) {
		
		if (ConfigLoader.getInstance().getSendFailedTestEmail().equalsIgnoreCase(YES)) {
			System.out.println("****************************************");
			System.out.println("Send Email - START");
			System.out.println("****************************************");

			String messageBody = getTestFailDetails(testName, exception, testData);
			try {
				
				EmailAttachmentsSender.sendEmailWithAttachments(SERVER , PORT , FROM , PASSWORD , TO , testName , messageBody , "");
				
				System.out.println("****************************************");
				System.out.println("Email sent successfully.");
				System.out.println("Send Email - END");
				System.out.println("****************************************");
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

	} 


/**
 * @author Palpandi
 * @param testName
 * @param exception
 * @param testData
 * @implNote Method will gather the failes test case details and generate it as HTML UI for to attach these detail on the email
 * @return
 */
	private static String getTestFailDetails(String testName, String exception , String testData) {
		System.out.println("Failed Test Name : " + testName);
		System.out.println("Raised Exception : " + exception);
		System.out.println("Test Data        : " + testData);

		 return "<!DOCTYPE html>\n"
        + "<html>\n"
        + "<head>\n"
        + "	<meta charset=\"UTF-8\">\n"
        + "	<title>Test Case Failed: " + testName + "</title>\n"
        + "	<style>\n"
        + "		body {\n"
        + "			margin: 0;\n"
        + "			padding: 0;\n"
        + "			font-family: Arial, sans-serif;\n"
        + "			background-color: #f2f2f2;\n"
        + "		}\n"
        + "		.container {\n"
        // ... rest of the CSS styles
        + "		}\n"
        + "		.header {\n"
        // ... header styles
        + "		}\n"
        + "		.header h1 {\n"
        // ... header h1 styles
        + "		}\n"
        + "		.header p {\n"
        // ... header p styles
        + "		}\n"
        + "		.content {\n"
        // ... content styles
        + "		}\n"
        + "		.content table {\n"
        // ... table styles
        + "		}\n"
        + "		.content th, .content td {\n"
        // ... table th and td styles
        + "		}\n"
        + "		.content th {\n"
        // ... table th styles
        + "		}\n"
        + "		.content td {\n"
        // ... table td styles
        + "		}\n"
        + "		.footer {\n"
        // ... footer styles
        + "		}\n"
        + "		.footer p {\n"
        // ... footer p styles
        + "		}\n"
        + "		.smiley {\n"
        // ... smiley styles
        + "		}\n"
        + "		.smiley.passed {\n"
        // ... smiley passed styles
        + "		}\n"
        + "		.smiley.failed {\n"
        // ... smiley failed styles
        + "		}\n"
        + "		.smiley.skipped {\n"
        // ... smiley skipped styles
        + "		}\n"
        + "	</style>\n"
        + "</head>\n"
        + "<body>\n"
        + "	<div class=\"container\">\n"
        + "		<div class=\"header\">\n"
        + "			<h1>Test Case Failed</h1>\n"
        + "			<p>" + testName + " <span class=\"smiley failed\">&#x1F641;</span></p>\n"
        + "		</div>\n"
        + "		<div class=\"content\">\n"
        + "			<table>\n"
        + "				<tr>\n"
        + "					<th style=\"background-color: #ffcccc;\">Test Data</th>\n"
        + "					<td>" + testData + "</td>\n"
        + "				</tr>\n"
        + "				<tr>\n"
        + "					<th style=\"background-color: #ffcccc;\">Exception Message</th>\n"
        + "					<td>" + exception + "</td>\n"
        + "				</tr>\n"
        + "			</table>\n"
        + "		</div>\n"
        + "		<div class=\"footer\">\n"
        + "			<p>This email was sent automatically by the testing system. Please do not reply.</p>\n"
        + "		</div>\n"
        + "	</div>\n"
        + "</body>\n"
        + "</html>\n";


        }
	
	
	
	
	
	
	
	


	}




