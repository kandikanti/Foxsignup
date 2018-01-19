package com.fox.test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fox.page.Account_Signup;
import com.fox.utilities.CommonFunctions;
import com.fox.utilities.ReadPropertyFile;

public class TC_Signup {

	private WebDriver driver;
	private CommonFunctions CF;
	private ReadPropertyFile RP;
	

	@BeforeClass(alwaysRun=true)
	public void setup() throws Exception {
		CF=new CommonFunctions();
		RP =new ReadPropertyFile();
		String getBrowserType=RP.getConfigPropertyVal("BrowserType");
		driver=CF.openBrowser(getBrowserType);
	
	}
	

	@BeforeMethod(alwaysRun=true)
	public void navigate() throws Exception {
		RP =new ReadPropertyFile();
		String sURL=RP.getConfigPropertyVal("sURL");
		CF.navigateURL(sURL);
	
	}
	
	@AfterMethod(alwaysRun=true) 
	public void after() throws Exception{
	    driver.manage().deleteAllCookies(); 
	    CF.tearDown();
	}

	@Test
	public void TC_Accountsignup(){
	
		Account_Signup Account_Signup =new Account_Signup(driver);
		Account_Signup.Login();
	
	}	
	
}
	