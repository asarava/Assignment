package com.petrolink.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.petrolink.reports.WebEventListener;
import com.petrolink.testutilities.TestUtilities;


public class TestBase {

	public static WebDriver driver;
	public static Properties configProp;
	public static String inputData;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase(){
	//Reading Config File.
		try {
			configProp = new Properties();
			FileInputStream readConfigLoc = new FileInputStream("C:\\Users\\samsung\\eclipse-workspace\\AutomationTest\\src\\main\\java\\com\\"
					+ "petrolink\\configfile\\ConfigurationFile");
			configProp.load(readConfigLoc);
			inputData = configProp.getProperty("inputdata");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Initializing web browser 
	public static void initialization(){
		String browserName = configProp.getProperty("browser");
		String driverLoc = null;
		inputData = configProp.getProperty("inputdata");

		if(browserName.equalsIgnoreCase("chrome")){
			driverLoc = configProp.getProperty("chromedriver");
			System.setProperty("webdriver.chrome.driver", driverLoc);	
			driver = new ChromeDriver(); 
		}else if(browserName.equalsIgnoreCase("firefox")){
			driverLoc = configProp.getProperty("firefoxdriver");
			System.setProperty("webdriver.gecko.driver", driverLoc);	
			driver = new FirefoxDriver(); 
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.get(configProp.getProperty("URL"));
		driver.manage().timeouts().pageLoadTimeout(TestUtilities.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	}
}
