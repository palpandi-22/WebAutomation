package com.openmrs.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;
import com.openmrs.PageLocators.Login;
import com.openmrs.PageLocators.PatientDetails;
import com.openmrs.PageLocators.Registration;
import com.openmrs.annotations.FrameworkAnnotation;
import com.openmrs.basetest.BaseTest;
import com.openmrs.enums.AuthorType;
import com.openmrs.enums.CategoryType;
import com.openmrs.enums.ProjectType;
import com.openmrs.utils.ConfigLoader;

import io.qameta.allure.Description;

public class LoginTest extends BaseTest   {
	
	
	public static final String UserRecord        =  "Pal Pandi";
    public static final String Vital_Label       =  "Vitals";
	public static final String Attachment_Label  =  "Attachment Upload";
	public static final String ReportPath        = "\\src\\test\\resources\\Test_Images\\Report.jpg";

	
	@FrameworkAnnotation( author   = { AuthorType.PALPANDI }, 
			              category = { CategoryType.REGRESSION }, 
			              module   = { ProjectType.OpenMRS })
	@Test
	@Description("validate the login scenario")

	public void TC_01_verify_the_Login()throws InterruptedException, IOException {
	
		Login login = new Login(getDriver()); 
		login.load_Login_Page()
		     .enter_Username(ConfigLoader.getInstance().getUsername())
		     .enter_Password(ConfigLoader.getInstance().getPassword())
		     .click_Laboratory()
		     .click_Login_Btn();
		Assert.assertEquals(login.getWelcomeText(), Login.Expected_Success);

	}
	
	
	@FrameworkAnnotation(author = { AuthorType.PALPANDI }, 
			           category = { CategoryType.REGRESSION }, 
			             module = { ProjectType.OpenMRS })
	@Test
	@Description(" validate the Register new patient with details")

	public void TC_02_verify_the_New_Register_Patient() throws InterruptedException, IOException {

		String GivenName   = "Pal";
		String FamilyName  = "Pandi";
		String Birth_Date  = "24";
		String Birth_Month = "May";
		String Birth_year  = "2001";
		String Address     = "11-1-8f , KovilStreet";
		String City        = "Nilakottai";
		String State       = "TamilNadu";
		String Country     = "India";
		String PinCode     = "624208";
        String PhoneNumber = "6383357070";
        String PersonName  = Faker.instance().name().firstName();
        
        SoftAssert Validation = new SoftAssert();
		new Login(getDriver()).doLogin();
		Registration  Register = new Registration(getDriver());
		Register.click_New_Register()
		        .enter_Given_Name(GivenName)
		        .enter_Family_Name(FamilyName)
		        .click_Next_Menu()
		        .select_Gender_Male()
		        .click_Next_Menu()
		        .enter_Birth_Date(Birth_Date)
		        .select_Birth_Month(Birth_Month)
		        .enter_Birth_Year(Birth_year)
		        .click_Next_Menu()
		        .enter_Primary_Address_Field(Address)
		        .enter_City_Name(City)
		        .enter_State_Name(State)
		        .enter_Country_Name(Country)
		        .enter_PostalCode(PinCode)
		        .click_Next_Menu()
		        .enter_PhoneNumber(PhoneNumber)
		        .click_Next_Menu()
		        .Select_Relationship()
		        .enter_Person_Name(PersonName)
		        .click_Next_Menu();
		
		 String ExpectedName     =  GivenName+", "+FamilyName;
		 String ExpectedDOB      =  Birth_Date +", "+Birth_Month+", "+Birth_year;
		 String ExpectedAddress  =  Address+", "+City+", "+State+", "+Country +", "+PinCode;

		 Validation.assertEquals(Register.getEnteredName() , ExpectedName);     
		 Validation.assertEquals(Register.getSelectedGender() , "Male");     
		 Validation.assertEquals(Register.getSelectedDob() , ExpectedDOB);     
		 Validation.assertEquals(Register.getSelectedAddress() , ExpectedAddress);     
		 Validation.assertEquals(Register.getEnteredNumber() , PhoneNumber);     
		 Register.click_Confirm_Btn();
      
		 Validation.assertEquals(Register.isSuccessVisible(), true);
		 Validation.assertAll();
	}
	
	
	
	@FrameworkAnnotation(author = { AuthorType.PALPANDI }, 
	           category = { CategoryType.REGRESSION }, 
	             module = { ProjectType.OpenMRS })
    @Test
    @Description(" validate the Register new patient with details")
	public void TC_03_Upload_Attachment_and_Verify_Patient_Documents() throws InterruptedException, IOException, AWTException {
       
	    String caption = Faker.instance().medical().diseaseName() + " Report";
		new Login(getDriver()).doLogin();
		PatientDetails Patient = new PatientDetails(getDriver());
		SoftAssert Soft = new SoftAssert();
		Patient.click_Find_Patient_Btn()
		       .Search_Patient_Details(UserRecord)
		       .click_User_Details_View(UserRecord)
		       .click_StartVisit_Button()
		       .click_Visit_Confirm_Button()
		       .click_Attachments()
		       .Upload_Attachments(ReportPath)
		       .enter_Caption(caption)
		       .click_Upload_Form_Btn();
		Soft.assertEquals(Patient.isVisibleSuccessToaster(), true);
		Patient.click_Back_Arrow();
		Soft.assertEquals(Patient.isAttachmentSuccess(), true);
		Soft.assertEquals(Patient.getUpdatedDate(), Patient.getCurrentDateFormatted());
		Soft.assertEquals(Patient.getUpdatedLabel(), "Attachment Upload");
		Patient.click_EndVisit_Button();
		Soft.assertAll();
		
	}
	
	
	@FrameworkAnnotation(author = { AuthorType.PALPANDI }, 
	                   category = { CategoryType.REGRESSION }, 
	                     module = { ProjectType.OpenMRS })
    @Test
    @Description(" validate the new patient with Height and Weight details")
	public void TC_003_Upload_Capture_Vitals_and_Verify_Patient_Height_Weights() throws InterruptedException, IOException, AWTException {
	   
		String UserHeight = "170";
	    String UserWeight = "77";
		new Login(getDriver()).doLogin();
		PatientDetails Patient = new PatientDetails(getDriver());
		SoftAssert Soft = new SoftAssert();
		Patient.click_Find_Patient_Btn()
		       .Search_Patient_Details(UserRecord)
		       .click_User_Details_View(UserRecord)
		       .click_StartVisit_Button()
		       .click_Visit_Confirm_Button()
		       .click_Capture_Vitals()
		       .enter_Height(UserHeight)
		       .click_Next_Menu()
		       .enter_Weight(UserWeight)
		       .click_Next_Menu();
		Soft.assertEquals(Patient.getBMIResult(), Patient.calculateBMI(UserWeight, UserHeight));
		Patient.click_Save_Form_Button()
		       .click_Save_Button()
		       .click_EndVisit()
		       .click_Back_Arrow();
		Soft.assertEquals(Patient.getupdatedWeight(), UserWeight);
		Soft.assertEquals(Patient.getupdatedHeight(), UserHeight);
		Soft.assertEquals(Patient.getUpdatedBMIs(), String.valueOf(Patient.getBMIResult()));
		Soft.assertEquals(Patient.getUpdatedLabel(), Vital_Label);
		Soft.assertEquals(Patient.getUpdatedDate(), Patient.getCurrentDateFormatted());
		Soft.assertAll();
				
	}
	 
	@FrameworkAnnotation(author = { AuthorType.PALPANDI }, 
            category = { CategoryType.REGRESSION }, 
              module = { ProjectType.OpenMRS })
	@Test
	@Description(" validate the new patient with Height and Weight details")
	public void TC_004_Merge_Visit_and() throws InterruptedException, IOException, AWTException {
	
	}
	
	
	
	
	
	
	

}
