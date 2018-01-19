package com.fox.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonFunctions {
	private WebDriver driver;
	private ReadPropertyFile ReadPropertyFile;
	public  String OSName=System.getProperty("os.name");
	public Actions builder;
	public WebDriverWait wait;
    Logger logger= Logger.getLogger(CommonFunctions.class);
			
    public WebDriver openBrowser(String remoteBrowserType) throws Exception
	{		
		ReadPropertyFile =new ReadPropertyFile();
		DesiredCapabilities Capabilities = new DesiredCapabilities();
		String browser =remoteBrowserType;
		if(OSName.toLowerCase().contains("windows")){
			File IEfile = new File("C:\\Selenium\\drivers\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", IEfile.getAbsolutePath());		
			File chromedriver = new File("C:\\Selenium\\drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
			
			logger.info("Browser = " + browser);
			
			if (browser.equalsIgnoreCase("FireFox")) {
			
				System.setProperty("webdriver.gecko.driver","./drivers/geckodriver.exe");
	       	 driver = new FirefoxDriver();
	       	// driver.manage().deleteAllCookies();
			}
			else if (browser.equalsIgnoreCase("Safari")) {
				 //Assert.assertTrue(isSupportedPlatform());
		       	 driver = new SafariDriver();
		       	 //driver.manage().deleteAllCookies();
				}
			else if(browser.equalsIgnoreCase("IE")) {	
	      	    Capabilities = DesiredCapabilities.internetExplorer();
	      	    Capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	      	    driver = new InternetExplorerDriver(Capabilities);      	    
			} 
			else if(browser.equalsIgnoreCase("Chrome")) {
	       		Capabilities = DesiredCapabilities.chrome();
	       		Capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
	       		driver = new ChromeDriver(Capabilities);       	
			}
			else if(browser.equalsIgnoreCase("Remote")) {
				logger.info("Browser is=" + remoteBrowserType );
				
				if (remoteBrowserType.equalsIgnoreCase("FireFox"))
					driver= new RemoteWebDriver(DesiredCapabilities.firefox());			
	          	else if(remoteBrowserType.equalsIgnoreCase("IE")){
	          		Capabilities = DesiredCapabilities.internetExplorer();
	          		Capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	          		driver= new RemoteWebDriver(Capabilities);
	          	}
	         	else if(remoteBrowserType.equalsIgnoreCase("Chrome")) 
	      	    	driver= new RemoteWebDriver(DesiredCapabilities.chrome());
				//driver = new Augmenter().augment(driver);  
				else if(remoteBrowserType.equalsIgnoreCase("Safari")) 
					//Assert.assertTrue(isSupportedPlatform());
	      	    	driver= new RemoteWebDriver(DesiredCapabilities.safari());
			
			}
		}else if(OSName.toLowerCase().contains("mac")){
			if(browser.equalsIgnoreCase("Remote")){ 
				//Capabilities = DesiredCapabilities.safari();
				driver = new RemoteWebDriver(DesiredCapabilities.safari());
			}else{
				Capabilities = DesiredCapabilities.safari();
				driver = new SafariDriver(Capabilities);
			}
				
		}
     
		driver.manage().deleteAllCookies();    
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
        driver.manage().window().maximize();   
        builder=new Actions(driver);
      //  selenium = new WebDriverBackedSelenium(driver, ReadPropertyFile.getConfigPropertyVal("URL"));
        logger.info("Executed Before Class Method Successfully");
        
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		System.out.println("version: " + caps.getVersion());
		String browserName=caps.getBrowserName();
        String browserVersion=caps.getVersion();
        //String OStype=Capabilities.toString();
        logger.info("\n"+"Browser="+browserName+"\n"+"BrowserVersion="+browserVersion+"\n"+"OS="+System.getProperty("os.name")+"\n"+"OSVersion="+System.getProperty("os.version"));
//       driverScreenshot = new Augmenter().augment(driver);
        return driver;
	}
	
			
			public WebDriver getDriver(){
				return driver;	
			}
						
			
			public void sendKeys(WebElement slocator, String sValue){
				
				try{
					slocator.sendKeys();
					logger.info(slocator + "entered");	
				}catch(Exception e){
					logger.info(slocator + "xpath");
					Assert.fail();
				}
				
			}
			
			public void click(WebElement slocator){
				try{
					slocator.click();
					logger.info(slocator +"clicked");
				}catch(Exception ex){
					ex.printStackTrace();
					logger.info(slocator + "xpath");
					Assert.fail();
				}
			
			}
			public void selectDropDownByValue(WebElement locator, String value){
				try{
					Select select = new Select(locator);
					select.selectByValue(value);
					logger.info("[" + value +"] selected");
				}
				catch(Exception e){
					logger.info("[" + value +"] not found");
					Assert.fail("[" + value +"] not found");
				}
			}
			
			 public void tearDown(){
				 logger.info("Closing browser");
				 driver.quit();
				 logger.info("browser closed");
				 
			 }
		
		 
		 
		 public void navigateURL(String sURL) throws InterruptedException{
			 driver.get(sURL);
			 logger.info("navigating the url="+sURL);
			   Thread.sleep(5000);
			  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			 
		 }


}

