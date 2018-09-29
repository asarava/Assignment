package com.petrolink.testcase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.petrolink.pages.HomePage;
import com.petrolink.testbase.TestBase;
import com.petrolink.testutilities.TestUtilities;

public class TestScenarios extends TestBase{

	HomePage homePage;
	Object[][] testData;
	//Initializing TestBase
	public TestScenarios () {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}
	//Data from Excel sheet.
	@DataProvider(name = "menuTextPass")
	public Object[][] dataProviderOne(){
		Object data[][] = TestUtilities.getTestData("Menu");
		return data;
	}

	@DataProvider(name = "menuTextFail")
	public Object[][] dataProviderTwo(){
		Object data[][] = TestUtilities.getTestData("MenuFail");
		return data;
	}

	@DataProvider(name = "searchToNavigate")
	public Object[][] dataProviderThree(){
		Object data[][] = TestUtilities.getTestData("SearchTextToNavigate");
		return data;
	}

	@DataProvider(name = "searchText")
	public Object[][] dataProviderFour(){
		Object data[][] = TestUtilities.getTestData("SearchText");
		return data;
	}

	@Test(priority = 1, dataProvider="menuTextPass")
	public void navigationWordExistence(String mainMenu, String subMenu, String assertText) {
		homePage.menuNavigation(mainMenu, subMenu);
		try {
			TestUtilities.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + assertText + "')]"));
		Assert.assertTrue(list.size() > 0, "Text not found!");
	}

	@Test(priority = 2, dataProvider="menuTextFail")
	public void navigationWordNonExistence(String mainMenu, String subMenu, String assertText) {
		homePage.menuNavigation(mainMenu, subMenu);
		try {
			TestUtilities.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(driver.getPageSource().contains(assertText));
	}

	@Test(priority = 3, dataProvider="searchToNavigate")
	public void searchWebsiteNavigate(String searchText, String searchResult, String resultNavigate) {
		String resultVerify = null;
		resultVerify = homePage.searchWebsite(searchText);
		try {
			TestUtilities.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(resultVerify.contentEquals(searchResult));
		resultVerify = homePage.searchResultNavigate(resultNavigate);
		try {
			TestUtilities.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(resultVerify.contentEquals(resultNavigate));
	}

	@Test(priority = 4, dataProvider="searchText")
	public void textSearchWebsite(String searchText, String searchResult) {
		String searchResultVerify = null;
		searchResultVerify = homePage.searchWebsite(searchText);
		try {
			TestUtilities.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(searchResultVerify.contentEquals(searchResult));
	}

	@Test(priority =5)
	public void changeLanguage() {
		homePage.changeLanguageTo("French");
		try {
			TestUtilities.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(homePage.homeButtonFrench.getText().contentEquals("Accueil"));
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
