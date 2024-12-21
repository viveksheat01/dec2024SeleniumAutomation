package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginpage;
	protected AccountPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage regPage;

	@Description("Initializing the browser....")
	@Step("Setup for the test, initializing the browser: {0} ")
	@Parameters({"browser"})    
	@BeforeTest
	public void setUp(@Optional String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if (browserName!=null) {
			prop.setProperty("browser", browserName);
			
			
		}

		driver=df.initDriver(prop);
		loginpage =new LoginPage(driver);
		softAssert=new SoftAssert();
		

	}
	@Description("closing the browser....")
	@Step("closing browser....... ")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
