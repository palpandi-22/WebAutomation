package com.openmrs.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDate;

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
import com.openmrs.enums.PropertyLoader;
import com.openmrs.utils.ConfigLoader;
import com.openmrs.utils.Helper;

import io.qameta.allure.Description;

public class OpenMRsTest extends BaseTest   {
	
	
    public static final String Vital_Label       =  "Vitals";
	public static final String Attachment_Label  =  "Attachment Upload";
	public static final String ReportPath        =  "\\src\\test\\resources\\Test_Images\\Report.jpg";
    public static final String Merge_Label       =  "Vitals, Attachment Upload";
	
	
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
	    Assert.assertTrue(login.getWelcomeText().contains(Login.Expected_Success) ,  "❌ Expected text to contain 'Logged in as', but was: " + login.getWelcomeText());
	}
	
	
	@FrameworkAnnotation(author = { AuthorType.PALPANDI }, 
			           category = { CategoryType.REGRESSION }, 
			             module = { ProjectType.OpenMRS })
	@Test
	@Description(" validate the Register new patient with details")

	public void TC_02_verify_the_New_Register_Patient() throws InterruptedException, IOException {

		String GivenName   = Faker.instance().name().firstName();
		String FamilyName  = Faker.instance().name().lastName();
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

        SoftAssert Soft = new SoftAssert();
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

		 Soft.assertEquals(Register.getEnteredName() , ExpectedName);     
		 Soft.assertEquals(Register.getSelectedGender() , "Male");     
		 Soft.assertEquals(Register.getSelectedDob() , ExpectedDOB);     
		 Soft.assertEquals(Register.getSelectedAddress() , ExpectedAddress);     
		 Soft.assertEquals(Register.getEnteredNumber() , PhoneNumber);     
		 Register.click_Confirm_Btn();
      
		 Soft.assertEquals(Register.isSuccessVisible(), true);
		 Soft.assertAll();
		 
		 new Helper().saveOnProperties(PropertyLoader.OPENMRS ,"Patient_Name",GivenName+" "+FamilyName );
	}
	
	
	
	@FrameworkAnnotation(author = { AuthorType.PALPANDI }, 
	                   category = { CategoryType.REGRESSION }, 
	                     module = { ProjectType.OpenMRS })
    @Test 
    @Description(" validate the Register new patient with details")
	public void TC_03_Upload_Attachment_and_Verify_Patient_Documents() throws InterruptedException, IOException, AWTException {
		
		String UserRecord = new Helper().getValueFromProperty(PropertyLoader.OPENMRS, "Patient_Name");
		System.out.println("UserRecord : " + UserRecord);
	    String caption    = Faker.instance().medical().diseaseName() + " Report";
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
		       .click_Upload_File_Btn();
		Soft.assertEquals(Patient.isVisibleSuccessToaster(), true);
		Patient.click_Back_Arrow();
		Soft.assertEquals(Patient.isAttachmentSuccess(caption), true);
		Soft.assertEquals(Patient.getUpdatedDate() , Patient.getCurrentDateFormatted());
		Soft.assertEquals(Patient.getUpdatedLabel(), Attachment_Label);
		Patient.click_EndVisit_Button();
		Soft.assertAll();
	
	}
	
	
	@FrameworkAnnotation(author = { AuthorType.PALPANDI }, 
	                   category = { CategoryType.REGRESSION }, 
	                     module = { ProjectType.OpenMRS })
    @Test
    @Description(" validate the new patient with Height and Weight details")
	public void TC_04_Upload_Capture_Vitals_and_Verify_Patient_Height_Weights() throws InterruptedException, IOException, AWTException {
		
		String UserRecord = new Helper().getValueFromProperty(PropertyLoader.OPENMRS, "Patient_Name");
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
	public void TC_05_Merge_Visits_Add_Past_Visit_Verify_DatePicker_Disables_Future_Dates() throws InterruptedException, IOException, AWTException {
		
		String UserRecord = new Helper().getValueFromProperty(PropertyLoader.OPENMRS, "Patient_Name");
		new Login(getDriver()).doLogin();
		PatientDetails Patient = new PatientDetails(getDriver());
		SoftAssert Soft = new SoftAssert();
		Patient.click_Find_Patient_Btn()
		       .Search_Patient_Details(UserRecord)
		       .click_User_Details_View(UserRecord)
		       .click_MergeVisit_Button()
		       .click_Attachment_Merge_Checkbox()
		       .click_Vitals_Merge_Checkbox()
		       .click_Merge_Selected_button()
		       .click_Back_Arrow();
		Soft.assertEquals(Patient.getUpdatedDate(), Patient.getCurrentDateFormatted());
		Soft.assertEquals(Patient.getUpdatedLabel(), Merge_Label);
		Patient.click_Add_PastVisit_Button();
		int tomorrowDay = LocalDate.now().plusDays(1).getDayOfMonth();
		boolean isDisabled = Patient.isFutureDateDisabled(tomorrowDay);
		Soft.assertTrue(isDisabled, "❌ Future date 9 is not disabled!");
		Soft.assertAll();
		       
	}
	
	
	@FrameworkAnnotation(author = { AuthorType.PALPANDI }, 
                       category = { CategoryType.REGRESSION }, 
                         module = { ProjectType.OpenMRS })
	@Test
	@Description(" validate the search and delete the patient details entirely")
	public void TC_06_Verify_Complete_Removal_Of_Deleted_Patient_Data() throws InterruptedException, IOException, AWTException { 
		
		String UserRecord = new Helper().getValueFromProperty(PropertyLoader.OPENMRS, "Patient_Name");
		String deleteReason = "Reason: " + Faker.instance().lorem().sentence(3); 
		new Login(getDriver()).doLogin();
		PatientDetails Patient = new PatientDetails(getDriver());
		SoftAssert Soft = new SoftAssert();
		Patient.click_Find_Patient_Btn()
		       .Search_Patient_Details(UserRecord)
		       .click_User_Details_View(UserRecord)
		       .click_DeletePatient_Button()
		       .enter_Reason_Delete(deleteReason)
		       .click_Delete_Confirmation();
		Soft.assertEquals(Patient.isVisibleSuccessToaster(), true);
		Patient.Search_Patient_Details(UserRecord);
		Soft.assertEquals(Patient.isPresentMessage(), true);
		Soft.assertAll();
	}
	
	

}
