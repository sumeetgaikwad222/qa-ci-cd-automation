package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.pages.LoginPage;
import com.qa.testdata.LoginTestData;

public class LoginPageTest extends BaseClass{
	
	@Test(dataProvider = "LoginData", dataProviderClass = LoginTestData.class)
	public void LoginCheck(String email, String password)
	{
		LoginPage lp = new LoginPage(driver.get());
		lp.navigateLoginPage();
		
		lp.validateLogin(email, password);
		
		
	}
}
