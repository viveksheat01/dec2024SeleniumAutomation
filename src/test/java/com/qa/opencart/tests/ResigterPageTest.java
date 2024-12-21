package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contants.Appcontants;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class ResigterPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetUp() {
		regPage=loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] userRegTestdata() {
		
		return new Object[][] {
			{"vivek", "selenium", "9876543222", "vivek@123","yes"},
			{"niraj", "niraj", "7702930002", "niraj@123","no"},
			{"tom", "tom", "8393900002", "tom@123","yes"}
			
			
		};
	}
	
	
	@DataProvider
	public Object[][] userRegisterFromSheet() {
	return	ExcelUtil.getTestData(Appcontants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider ="userRegisterFromSheet" )
	public void userRegisterationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue
		(regPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), telephone, password, subscribe),
			AppErrors.USER_REG_NOT_DONE
		);
	}
	

}
