package com.openmrs.PageLocators;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.openmrs.base.BasePage;
import com.openmrs.enums.WaitStrategy;

public class PatientDetails extends BasePage  {

	public PatientDetails(WebDriver driver) {
		super(driver);
	}
	
	private static final By Start_Visit_Btn                  =  By.xpath("//a[@id=\"org.openmrs.module.coreapps.createVisit\"]");
    private static By End_Visit_Btn                          =  By.xpath("//a[@id='referenceapplication.realTime.endVisit']");
    private static final By Merge_Visit_Btn                  =  By.xpath("//a[@id='org.openmrs.module.coreapps.mergeVisits']");
    private static final By Add_Visit_Btn                    =  By.xpath("//a[@id='org.openmrs.module.coreapps.createRetrospectiveVisit']");
    private static final By Delete_Patient_Btn               =  By.xpath("//a[@id='org.openmrs.module.coreapps.deletePatient']");
    
	private static final By Start_Visit_Confirm_Btn          =  By.xpath("//button[@id='start-visit-with-visittype-confirm']");
	private static final By Attachments                      =  By.xpath("//a[@id='attachments.attachments.visitActions.default']");
	private static final By Capture_Vitals                   =  By.xpath("//a[@id='referenceapplication.realTime.vitals']");
	private static final By End_Visit                        =  By.xpath("//a[normalize-space()='End Visit']");
	private static final By Upload_Place                     =  By.id("visit-documents-dropzone");
	private static final By Caption_Field                    =  By.xpath("//textarea[@placeholder='Enter a caption']");
	private static final By Upload_Form_Btn                  =  By.xpath("//button[text()='Clear forms']");
    private static final By Success_Toaster                  =  By.xpath("//div[@class=\"toast-item-close\"]");
    private static final By Back_Patient_Details             =  By.xpath("//ul[@id=\"breadcrumbs\"]/li[2]");
	private static final By Uploaded_Attachment              =  By.xpath("(//div[@class=\"att_thumbnail-image-section att_click-pointer\"])[1]");
    private static final By Uploaded_Date                    =  By.xpath("(//li[@class='ng-scope']/a)[1]");
	private static final By Uplodaed_Label                   =  By.xpath("(//li[@class='ng-scope']/a)[1]/following-sibling::div");
	private static final By yes_Button                       =  By.xpath("(//button[normalize-space(text())='Yes'])[1]");
	private static final By Height_Field                     =  By.xpath("//span[@id=\"height\"]/input");
	private static final By Next_button                      =  By.xpath("//button[@id='next-button']");
	private static final By Weight_Field                     =  By.xpath("//span[@id=\"weight\"]/input");
	private static final By BMI_Result                       =  By.xpath("//span[@id='calculated-bmi']");
	private static final By Save_Form_Button                 =  By.xpath("//a[@id='save-form']");
	private static final By Verify_Height                    =  By.xpath("//div[@id=\"dataCanvas\"]/div/p[1]");
	private static final By Verify_Weight                    =  By.xpath("//div[@id=\"dataCanvas\"]/div/p[2]");
	private static final By Verify_BMIs                      =  By.xpath("//div[@id=\"dataCanvas\"]/div/p[3]");
    private static final By Save_button                      =  By.xpath("//button[normalize-space()='Save']");
    private static final By Updated_Weight                   =  By.xpath("//span[@id=\"weight\"]/span[1]");
    private static final By Updated_Height                   =  By.xpath("//span[@id=\"height\"]/span[1]");
    private static final By Updated_BMI                      =  By.xpath("//span[@id=\"calculated-bmi\"]");
    private static final By Attachment_Merge_Checkbox        =  By.xpath("//td[normalize-space()='Attachment Upload']//parent::tr//td/input");
    private static final By Vitals_Merge_Checkbox            =  By.xpath("//td[normalize-space()='Vitals']//parent::tr//td/input");
    private static final By Merge_Selected_Visited_btn       =  By.xpath("//input[@id='mergeVisitsBtn']");
    
    private static final By Delete_Reason_Field              =  By.xpath("//input[@id='delete-reason']");
    private static final By Delete_Confirm_Button            =  By.xpath("(//div[@class=\"dialog-content\"]//button[normalize-space()='Confirm'])[2]");

	private static final By Find_Patient_Record_Btn          =  By.xpath("//a[@id='coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension']");
    private static final By Patient_Search                   =  By.xpath("//input[@id='patient-search']");
    private static final By No_Data_Found_message            =  By.xpath("//td[@class='dataTables_empty']");
   	
    //table[@class=" table-condensed"]//tr/td[normalize-space()='30']
    

    public PatientDetails click_StartVisit_Button() {
		click(Start_Visit_Btn,  WaitStrategy.CLICKABLE, " End Visit Button ");
        return this;
    }
    
    public PatientDetails click_EndVisit_Button() {
		click(End_Visit_Btn,  WaitStrategy.CLICKABLE, " End Visit Button ");
		return this;
	}
    
    public PatientDetails click_MergeVisit_Button() {
		click(Merge_Visit_Btn,  WaitStrategy.CLICKABLE, " Merge Visit Button ");
		return this;
	}
    
    public PatientDetails click_AddVisit_Button() {
		click(Add_Visit_Btn,  WaitStrategy.CLICKABLE, " Add Visit Button ");
		return this;
	}
	
    public PatientDetails click_DeletePatient_Button() {
		click(Delete_Patient_Btn,  WaitStrategy.CLICKABLE, " Delete Patient Button ");
		return this;
	}
    
    public PatientDetails click_Visit_Confirm_Button() {
		click(Start_Visit_Confirm_Btn,  WaitStrategy.CLICKABLE, " Start Visit Confirm Button ");
		return this;
	}
    
    public PatientDetails click_Attachments() {
		click(Attachments,  WaitStrategy.CLICKABLE, " Attachments ");
		return this;
	}
    
    public PatientDetails click_Capture_Vitals() {
		click(Capture_Vitals,  WaitStrategy.CLICKABLE, " Capture Vitals ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Height_Field));
		return this;
	}
    
    public PatientDetails click_EndVisit() {
    	click(End_Visit , WaitStrategy.CLICKABLE , " End Visit ");
		click(yes_Button,  WaitStrategy.CLICKABLE, " Yes Button ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Back_Patient_Details));
    	return this;
    }
    
    public PatientDetails Upload_Attachments(String relativeFilePath) throws AWTException {
        // Build the full file path
        String fullPath = System.getProperty("user.dir") + relativeFilePath;
        File file = new File(fullPath);

        if (!file.exists()) {
            throw new RuntimeException("❌ File not found: " + fullPath);
        }

        // Wrap in quotes for file path safety
        String filePath = "\"" + file.getAbsolutePath() + "\"";

        // Step 1: Click on the upload drop area
        click(Upload_Place, WaitStrategy.CLICKABLE, "Upload Drop Zone");

        // Step 2: Add a slight wait before opening the native dialog
        waitForGivenTime(1); // Optional: safer delay for file dialog to appear

        // Step 3: Upload file using Robot
        Robot robot = new Robot();
        robot.setAutoDelay(300);

        // Copy file path to clipboard
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // Paste path: CTRL + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Step 4: Wait for upload progress to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.dz-progress")));
            System.out.println("✅ Upload progress started.");
        } catch (TimeoutException e) {
            System.out.println("⚠️ Upload progress bar not shown — may be skipped for small files.");
        }

        // Step 5: Wait for upload progress bar to disappear (upload complete)
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.dz-progress")));
            System.out.println("✅ File uploaded successfully.");
        } catch (TimeoutException e) {
            System.out.println("❌ Upload progress bar still visible after timeout.");
        }

        waitForGivenTime(2); // Optional pause before next steps
        return this;
    }

	
	
	public PatientDetails enter_Caption(String Captions) {
		sendKeys(Caption_Field, Captions, WaitStrategy.VISIBLE, " Captions ");
		return this;
	}
	
	public PatientDetails click_Upload_Form_Btn() {
		click(Back_Patient_Details,  WaitStrategy.CLICKABLE, " Breadcrumb Back Arrow ");
		wait.until(ExpectedConditions.presenceOfElementLocated(Success_Toaster));
		return this;
	}
	
	public boolean isVisibleSuccessToaster() {
		return isDisplayed(Success_Toaster);
	}
	
	public PatientDetails click_Back_Arrow() {
		click(Upload_Form_Btn,  WaitStrategy.CLICKABLE, " Upload Form Button ");
		waitForGivenTime(2);
		return this;
	}
	
	public boolean isAttachmentSuccess() {
		return isDisplayed(Uploaded_Attachment);
	}
	
	public String getUpdatedDate() {
		return driver.findElement(Uploaded_Date).getText();
	}
    
	public String getUpdatedLabel() {
		return driver.findElement(Uplodaed_Label).getText();
	}
	
	public PatientDetails click_Yes_Button() {
		click(yes_Button,  WaitStrategy.CLICKABLE, " Yes Button ");
		return this;
	}
	
	public String getCurrentDateFormatted() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MMM.yyyy");
		Date date = new Date();
		return formatter.format(date);
	}
	
	public PatientDetails enter_Height(String Length) {
		clearAndSendKeys(Height_Field, Length, WaitStrategy.VISIBLE, " Height Field ");
		return this;
	}
	
	public PatientDetails click_Next_Menu() {
		click(Next_button,  WaitStrategy.CLICKABLE, " Next Menu Button ");
		return this;
	}
	
	public PatientDetails enter_Weight(String Weight) {
		clearAndSendKeys(Weight_Field, Weight, WaitStrategy.VISIBLE, " Weight Field ");
		return this;
	}
	
	public double getBMIResult() {
	    String bmiText = driver.findElement(BMI_Result).getText().trim();
	    return Double.parseDouble(bmiText);
	}
	
	public double calculateBMI(String weightStr, String heightStr) {
	    int weightKg = Integer.parseInt(weightStr.trim());
	    int heightCm = Integer.parseInt(heightStr.trim());

	    double heightM = heightCm / 100.0;
	    double bmi = weightKg / (heightM * heightM);

	    return Math.round(bmi * 100.0) / 100.0; // Round to 2 decimal places
	}
	
	public PatientDetails click_Save_Form_Button() {
		click(Save_Form_Button,  WaitStrategy.CLICKABLE, " Save Form Button ");
		return this;
	}
	
	public String getVerifiedHeight() {
		return driver.findElement(Verify_Height).getText();
	}
	
	public String getVerifiedWeight() {
		return driver.findElement(Verify_Weight).getText();
	}
	
	public String getVerifiedBMIs() {
		return driver.findElement(Verify_BMIs).getText();
	}
	
	public PatientDetails click_Save_Button() {
		click(Save_button,  WaitStrategy.CLICKABLE, " Save Button ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Capture_Vitals));
		return this;
	}
	
	public String getupdatedWeight() {
		return driver.findElement(Updated_Weight).getText();
	}
	
	public String getupdatedHeight() {
		return driver.findElement(Updated_Height).getText();
	}
	
	public String getUpdatedBMIs() {
		return driver.findElement(Updated_BMI).getText();
	}
	
	public PatientDetails click_Attachment_Merge_Checkbox() {
		click(Attachment_Merge_Checkbox,  WaitStrategy.CLICKABLE, " Attachment Merge Checkbox ");
		return this;
	}
	
	public PatientDetails click_Vitals_Merge_Checkbox() {
		click(Vitals_Merge_Checkbox,  WaitStrategy.CLICKABLE, " Vitals Merge Checkbox ");
		return this;
	}
	
	public PatientDetails click_Merge_button() {
		click(Merge_Selected_Visited_btn,  WaitStrategy.CLICKABLE, " Merge button ");
		return this;
	}
	
	public PatientDetails enter_Reason_Delete (String Reasons) {
		clearAndSendKeys(Delete_Reason_Field, Reasons ,  WaitStrategy.VISIBLE, " Delete Reason field ");
		return this;
	}

	public PatientDetails click_Delete_Confirmation() {
		click(Delete_Confirm_Button,  WaitStrategy.CLICKABLE, " Delete Cofirm button ");
		return this;
	}
	
	public PatientDetails Search_Patient_Details (String PatientName) {
		clearAndSendKeys(Patient_Search, PatientName ,  WaitStrategy.VISIBLE, " Find Person Details Search field ");
		return this;
	}
	
	public PatientDetails click_Find_Patient_Btn() {
		click(Find_Patient_Record_Btn,  WaitStrategy.CLICKABLE, " Find Patient Search button ");
		return this;
	}
	
	public PatientDetails click_User_Details_View(String Username) {
		By Locator = By.xpath("//td[normalize-space()='" + Username + "']");
		click(Locator, WaitStrategy.CLICKABLE, " Searched User Navigate Details ");
		waitForGivenTime(2);
		return this;
	}
	
	public boolean isPresentMessage() {
		return isDisplayed(No_Data_Found_message);
	}

	
}
