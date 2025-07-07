package com.openmrs.PageLocators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.openmrs.base.BasePage;
import com.openmrs.enums.DropdownType;
import com.openmrs.enums.WaitStrategy;

public class Registration extends BasePage  {

	public Registration(WebDriver driver) {
		super(driver);
	}
	

	private static final By Register_Patient_Btn           =  By.xpath("//a[@id='referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension']");
	private static final By Given_Name                     =  By.xpath("//input[@name=\"givenName\"]");
	private static final By Middle_Name                    =  By.xpath("//input[@name=\"middleName\"]");
	private static final By Family_Name                    =  By.xpath("//input[@name=\"familyName\"]");
    private static final By Confirm_Next_Btn               =  By.xpath("//button[@id='next-button']");
	private static final By Gender_Dropdown                =  By.xpath("//select[@id='gender-field']");
	private static final By Birth_Date_Field               =  By.xpath("//input[@id='birthdateDay-field']");
	private static final By Birth_Month_Field              =  By.xpath("//select[@id='birthdateMonth-field']");
	private static final By Birth_Year_Field               =  By.xpath("//input[@id='birthdateYear-field']");
	private static final By Primary_Address_Field          =  By.xpath("//input[@id='address1']");
	private static final By City_Field                     =  By.xpath("//input[@id='cityVillage']");
	private static final By State_Field                    =  By.xpath("//input[@id='stateProvince']");
	private static final By Country_Field                  =  By.xpath("//input[@id='country']");
	private static final By PostalCode_Field               =  By.xpath("//input[@id='postalCode']");
	private static final By PhoneNumber_Field              =  By.xpath("//input[@name=\"phoneNumber\"]");
	private static final By Relationship_Type              =  By.xpath("//select[@id='relationship_type']");
	private static final By PersonName_Field               =  By.xpath("//input[@placeholder=\"Person Name\"]");
	private static final By Verify_Name                    =  By.xpath("//div[@id=\"dataCanvas\"]/div/p[1]");
	private static final By Verify_Gender                  =  By.xpath("//div[@id=\"dataCanvas\"]/div/p[2]");
	private static final By Verify_Birthday                =  By.xpath("//div[@id=\"dataCanvas\"]/div/p[3]");
	private static final By Verify_Address                 =  By.xpath("//div[@id=\"dataCanvas\"]/div/p[4]");
	private static final By Verify_PhoneNumber             =  By.xpath("//div[@id=\"dataCanvas\"]/div/p[5]");
	private static final By Verify_Relative                =  By.xpath("//div[@id=\"dataCanvas\"]/div/p[6]");
    private static final By Confirm_Btn                    =  By.xpath("//input[@id='submit']");
    private static final By Cancel_Btn                     =  By.xpath("//input[@id='cancelSubmission']");
    private static final By Success_Toaster                =  By.xpath("//div[@class=\"toast-item-close\"]");
	private static final By Dashboard_Dateofbirth          =  By.xpath("//div[@class='gender-age col-auto']//span[2]");
	
	
	
	public Registration click_New_Register() {
		click(Register_Patient_Btn,  WaitStrategy.CLICKABLE, " New Patient Register ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Given_Name));
		return this;
	}
	
	public Registration enter_Given_Name(String Givenname) {
		sendKeys(Given_Name, Givenname , WaitStrategy.CLICKABLE, " Given Name ");
		return this;
	}
	
	public Registration enter_Middle_Name(String middlename) {
		sendKeys(Middle_Name, middlename , WaitStrategy.CLICKABLE, " Middle Name ");
		return this;
	}
	
	public Registration enter_Family_Name(String familyname) {
		sendKeys(Family_Name, familyname , WaitStrategy.CLICKABLE, " Family Name ");
		return this;
	}
	
	public Registration click_Next_Menu () {
		click(Confirm_Next_Btn,  WaitStrategy.CLICKABLE, " Next button ");
		return this;
	}
	
	public Registration select_Gender_Male() {
		selectFromDropdown(Gender_Dropdown , "M" , WaitStrategy.CLICKABLE , " Gender Selection  " , DropdownType.selectByValue , 1 );
		return this;
	}
	
	public Registration enter_Birth_Date (String birthdate) {
		sendKeys(Birth_Date_Field, birthdate , WaitStrategy.CLICKABLE, " Birth date field  ");
		return this;
	}
	
	public Registration select_Birth_Month(String Month) {
		selectFromDropdown(Birth_Month_Field , "5" , WaitStrategy.CLICKABLE , " Birth Month Selection  " , DropdownType.selectByValue , 5 );
		return this;
	}
	
	public Registration enter_Birth_Year (String BirthYear) {
		sendKeys(Birth_Year_Field, BirthYear , WaitStrategy.CLICKABLE, " Birth Year field  ");
		return this;
	}
	
	public Registration enter_Primary_Address_Field (String Address) {
		sendKeys(Primary_Address_Field, Address , WaitStrategy.CLICKABLE, " Addresss Field ");
		return this;
	}
	
	public Registration enter_City_Name (String CityName) {
		sendKeys(City_Field, CityName , WaitStrategy.CLICKABLE, " City Field ");
		return this;
	}
	
	public Registration enter_State_Name (String StateName) {
		sendKeys(State_Field, StateName , WaitStrategy.CLICKABLE, " State Field ");
		return this;
	}
	
	public Registration enter_Country_Name (String Countryname) {
		sendKeys(Country_Field, Countryname , WaitStrategy.CLICKABLE, " Country Field ");
		return this;
	}
	
	public Registration enter_PostalCode (String Postal) {
		sendKeys(PostalCode_Field, Postal , WaitStrategy.CLICKABLE, " Postal Code Field ");
		return this;
	}
	
	public Registration enter_PhoneNumber (String PhoneNumber) {
		sendKeys(PhoneNumber_Field , PhoneNumber , WaitStrategy.CLICKABLE, " Phone Number Field ");
		return this;
	}
	
	public Registration Select_Relationship () {
		selectFromDropdown(Relationship_Type , "Sibling" , WaitStrategy.CLICKABLE , " Relationship Type Selection " , DropdownType.selectByIndex , 2 );
		return this;
	}
	
	public Registration enter_Person_Name(String PersonName) {
		sendKeys(PersonName_Field, PersonName , WaitStrategy.CLICKABLE, " Person Name Field ");
		return this;
	}
	
	public String getEnteredName() {
		WebElement Element = driver.findElement(Verify_Name);
		return getExactText(Element);
	}
		
	public String getSelectedGender() {
		WebElement Element = driver.findElement(Verify_Gender);
		return getExactText(Element);			
	}
	
	public String getSelectedDob() {
		WebElement Element = driver.findElement(Verify_Birthday);
		return getExactText(Element);
	}
	
	public String getSelectedAddress() {
		WebElement Element = driver.findElement(Verify_Address);
		return getExactText(Element);		
	}
	
	public String getEnteredNumber() {
		WebElement Element = driver.findElement(Verify_PhoneNumber);
		return getExactText(Element);
	}
	
	public String getSelectedRelative() {
		WebElement Element = driver.findElement(Verify_Relative);
		return getExactText(Element);
	}
	
	public Registration click_Confirm_Btn () {
		click(Confirm_Btn , WaitStrategy.CLICKABLE , " Confirm button "); 
		waitForGivenTime(2);
		return this;
	}
	
	public Registration click_Cancel_Btn () {
		click(Cancel_Btn , WaitStrategy.CLICKABLE , " Cancel button "); 
		return this;
	}
	
	public boolean isSuccessVisible() {
		return driver.findElement(Success_Toaster).isDisplayed();
	}
	
	public String getExactText(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String nameOnly = (String) js.executeScript(
				"let p = arguments[0]; " + "let nodes = p.childNodes; " + "for (let i = 0; i < nodes.length; i++) { "
						+ "  if (nodes[i].nodeType === Node.TEXT_NODE) { return nodes[i].textContent.trim(); } "
						+ "} return '';",
				element);
		return nameOnly;
	}
	
	public String getBirthDate() {
		String UIText = driver.findElement(Dashboard_Dateofbirth).getText();
		String formattedDate = null;
		
		Pattern pattern = Pattern.compile("\\((\\d{2})\\.(\\w{3})\\.(\\d{4})\\)");
		Matcher matcher = pattern.matcher(UIText);
		if (matcher.find()) {
			String day    = matcher.group(1); 
			String month  = matcher.group(2); 
			String year   = matcher.group(3); 
			formattedDate = day + ", " + month + ", " + year;
		} else {
			System.out.println("Date not found in the text.");
		}
		return formattedDate;
	}
	
	
	
}
