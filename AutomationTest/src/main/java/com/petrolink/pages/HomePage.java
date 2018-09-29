package com.petrolink.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.petrolink.testbase.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//input[@class = 'form-control input-sm']")
	WebElement searchBox;
	
	@FindBy(xpath = "//button[@class = 'btn btn-default btn-xs']")
	WebElement searchButton;
	
	@FindBy(xpath = "//h1[@class = 'title']")
	WebElement searchTitle;
	
	@FindBy(xpath = "//h2[@class = 'title']")
	WebElement navigateTitle;

	@FindBy(xpath = "//a[contains(text(), 'Language')]")
	WebElement languageSelect;
	
	@FindBy(xpath = "//a[contains(text(), 'English')]")
	WebElement languageEnglish;
	
	@FindBy(xpath = "//a[contains(text(), 'Français')]")
	WebElement languageFrench;
	
	@FindBy(xpath = "//a[contains(text(), 'Русский')]")
	WebElement languageRussian;
	
	@FindBy(xpath = "//a[contains(text(), 'Home')]")
	public WebElement homeButtonEnglish;
	
	@FindBy(xpath = "//a[contains(text(), 'Accueil')]")
	public WebElement homeButtonFrench;
	
	@FindBy(xpath = "//a[contains(text(), 'Главная страница')]")
	public WebElement homeButtonRussian;
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}

	public void menuNavigation(String menuItem, String subMenuItem) {
		List<WebElement> menuNavigationList = null;
		List<WebElement> subMenuNavigationList = null;
		menuNavigationList = driver.findElements(By.xpath("//ul[@id = 'mega-menu-main']/li"));
		for(int i=0; i<menuNavigationList.size(); i++) {
			if(menuNavigationList.get(i).getText().equalsIgnoreCase(menuItem)) {
				menuNavigationList.get(i).click();
				subMenuNavigationList = menuNavigationList.get(i).findElements(By.xpath(".//ul[@class = 'mega-sub-menu']/li"));
				break;
			}
		}
		for(int i=0; i<subMenuNavigationList.size(); i++) {
			if(subMenuNavigationList.get(i).getText().equalsIgnoreCase(subMenuItem)) {
				subMenuNavigationList.get(i).click();
				break;
			}
		}

	}
	
	public String searchWebsite(String searchText) {
		searchBox.click();
		searchBox.sendKeys(searchText);
		searchButton.click();
		String temp = searchTitle.getText();
		return temp;
	}
	
	public String searchResultNavigate(String resultToNavigate) {
		driver.findElement(By.xpath(("//a[contains(text(),'"+resultToNavigate+"')]"))).click();
		String temp = navigateTitle.getText();
		return temp;
	}
	
	public void changeLanguageTo(String language) {
		languageSelect.click();
		if(language.equalsIgnoreCase("English"))
			languageEnglish.click();
		else if(language.equalsIgnoreCase("French"))
			languageFrench.click();
		else if(language.equalsIgnoreCase("Russian"))
			languageRussian.click();
	}
}



