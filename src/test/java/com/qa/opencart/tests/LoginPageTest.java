package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contants.Appcontants;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design open cart Application with shopping workflow")
@Story("US 101: Design login page for open Cart Application " )


@Listeners({ExtentReportListener.class, TestAllureListener.class})
public class LoginPageTest extends BaseTest {

	
	@Description("checking login page title-------")
	@Severity(SeverityLevel.MINOR)
	@Owner("Vivek Singh")
	@Test(priority = 1)
	public void loginPageTitleTest() {

		String actTile = loginpage.getLoginPageTitle();

		Assert.assertEquals(actTile, Appcontants.LOGIN_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);

	}

	@Description("Checking login page Url------")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Vivek Singh")
	@Test(priority = 2)
	public void loginPageUrlTest() {

		String actUrl = loginpage.getLoginPageUrl();

		Assert.assertTrue(actUrl.contains(Appcontants.LOGIN_PAGE_FRACTION_URL), AppErrors.URL_NOT_FOUND);

	}

	@Description("Checking firgot pwd link exist on the login page------")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vivek Singh")
	@Test(priority = 3)
	public void forgotpwdLinkExitTest() {
		Assert.assertTrue(loginpage.checkForgotPwdlinkExist(), AppErrors.ELEMENT_NOT_FOUND);

	}

	@Description("Checking user is able to login successfully------")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Vivek Singh")
	@Test(priority = 4)
	public void loginTest() {

	accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	Assert.assertEquals(accPage.getAccPageTitle(), Appcontants.ACCOUNT_PAGE_TITLE,AppErrors.TITLE_NOT_FOUND);
	


	}

}
