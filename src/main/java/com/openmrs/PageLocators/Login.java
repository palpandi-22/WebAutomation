package com.openmrs.PageLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.openmrs.base.BasePage;
import com.openmrs.enums.WaitStrategy;
import com.openmrs.utils.ConfigLoader;

public class Login extends BasePage {

	public Login(WebDriver driver) {
		super(driver);
	}

	public static final String Expected_Success = "Logged in as";
	
	private static final By Username               =  By.xpath("//input[@id='username']");
	private static final By Password               =  By.xpath("//input[@id='password']");
	private static final By Laboratary             =  By.xpath("//li[@id='Laboratory']");
	private static final By LoginBtn               =  By.xpath("//input[@id='loginButton']");
	private static final By Welcome_Message        =  By.xpath("//*[contains(text(),'Logged in as')]");
	
	
	public Login load_Login_Page() throws InterruptedException {
		load("/login.htm");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Username));
		return this;
	}
	
	public Login enter_Username(String UserName) {
		clearAndSendKeys(Username, UserName , WaitStrategy.CLICKABLE, " Username ");
		return this;
	}

	public Login enter_Password(String password) {
		sendKeys(Password, password , WaitStrategy.CLICKABLE, " Password ");
		return this;
	}
	
	public Login click_Laboratory () {
		click(Laboratary,  WaitStrategy.CLICKABLE, " Location - Laboratary ");
		return this;
	}
	
	public Login click_Login_Btn () {
		click(LoginBtn,  WaitStrategy.CLICKABLE, " Login Button ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Welcome_Message));
		return this;
	}
	
	public String getWelcomeText() {
		return driver.findElement(Welcome_Message).getText();
	}
	
	public void doLogin() throws InterruptedException {
		     load_Login_Page();
		     enter_Username(ConfigLoader.getInstance().getUsername());
		     enter_Password(ConfigLoader.getInstance().getPassword());
		     click_Laboratory();
		     click_Login_Btn();
	     Assert.assertTrue(getWelcomeText().contains("Logged in as"));
	}
	
	
	
	
}
