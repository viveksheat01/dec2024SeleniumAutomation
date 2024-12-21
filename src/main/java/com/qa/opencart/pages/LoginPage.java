package com.qa.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.contants.Appcontants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.Timeutil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;

	private ElementUtil eleUtil;

	// 1. page object : By locator

	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	

	// 2. public const.... of the page

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// public page action

	@Step("Getting login page tilte-----")
	public String getLoginPageTitle() {

		String title = eleUtil.waitForTitleToBe(Appcontants.LOGIN_PAGE_TITLE, Timeutil.DEFAULT_TIME);

		System.out.println("login page Title==== " + title);
		return title;
	}

	@Step("Getting login page URL-----")

	public String getLoginPageUrl() {

		String getUrl = eleUtil.waitForURLToBe(Appcontants.LOGIN_PAGE_FRACTION_URL, Timeutil.DEFAULT_TIME);

		System.out.println("login page URL" + getUrl);
		return getUrl;
	}
	@Step("Getting the state of forgot pwd link-----")

	public boolean checkForgotPwdlinkExist() {

		return eleUtil.doIsDisplayed(forgotPwdLink);

	}

	@Step("login to application with username: {0} and password: {1}")
	public AccountPage doLogin(String username, String pwd) {
		
		System.out.println("user name and passowrd : >>> " + username + " " + password);

		eleUtil.doSendKeys(emailID, username, Timeutil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);

		return new AccountPage(driver);

	}
	@Step("navigate to register page....")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink, Timeutil.DEFAULT_TIME);
		return new RegisterPage(driver);
		
		
	}

}
