package com.fox.page;

import java.util.concurrent.TimeUnit;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.fox.utilities.CommonFunctions;

public class Account_Signup {
	
	private Logger logger= Logger.getLogger(Account_Signup.class);
	private WebDriver driver;
	private CommonFunctions CF;
	
	@FindBy(how = How.XPATH, using = "//*[@id='path-1']")
	private WebElement CreateAccount;
	
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[1]/div[2]/div/div[4]/button[1]")
	private WebElement Signup;
	
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[4]/div[1]/input")
	private WebElement FirstName;
	
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[4]/div[2]/input")
	private WebElement LastName;
	
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[5]/input")
	private WebElement EmailAddress;
	
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[7]/div/input")
	private WebElement Password;
	
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[9]/div[2]/input")
	private WebElement Birthdate;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[9]/div[1]/div/div[1]/div/a")
	private WebElement Gender;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[9]/div[1]/div/div[2]/a[2]/div")
	private WebElement Female;
	
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[11]/button")
	private WebElement CreateProfile ;
	
	@FindBy(how = How.XPATH, using = "//*[@id='path-1']")
	private WebElement SignOff;
	
	public  Account_Signup(WebDriver driver){
	
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		CF = new CommonFunctions();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(driver.getTitle().trim(), "Stream and Watch your Favorite TV Shows, Movies and Live TV - FOX");
		logger.info(" Account Sign up page title verified..."); 
		
	}
	
	public void Login(){
		CreateAccount.click();
		Signup.click();
		FirstName.sendKeys("Alekhya");
		LastName.sendKeys("K");
		EmailAddress.sendKeys("alekhya@gmail.com");
		Password.sendKeys("test123");
		Birthdate.sendKeys("01/01/1992");
        Gender.click();
        Female.click();
		CreateProfile.click();
	}}