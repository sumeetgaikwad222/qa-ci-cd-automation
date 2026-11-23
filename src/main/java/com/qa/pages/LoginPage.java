package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en_lol.WEN;

public class LoginPage {
	
	@FindBy(xpath = "//a[normalize-space()='Signup / Login']") private WebElement loginlink;
	@FindBy(xpath = "//input[@data-qa='login-email']") private WebElement emailField;
	@FindBy(xpath = "//input[@placeholder='Password']") private WebElement passwordField;
	@FindBy(xpath = "//button[normalize-space()='Login']") private WebElement LoginButton;
	@FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']") private WebElement errorMessage;
	@FindBy(xpath = "//a[normalize-space()='Logout']") private WebElement LogoutButton;
	private WebDriver driver;
	private WebDriverWait wait;
	public LoginPage(WebDriver driver) {
		this.driver  = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver , this);
	}
	
	public void navigateLoginPage()
	{
		wait.until(ExpectedConditions.elementToBeClickable(loginlink));
		loginlink.click();
		wait.until(ExpectedConditions.urlContains("login"));
	}
	 public void validateLogin(String email, String password) {

	        wait.until(ExpectedConditions.visibilityOf(emailField));

	        emailField.clear();
	        emailField.sendKeys(email);

	        passwordField.clear();
	        passwordField.sendKeys(password);

	        wait.until(ExpectedConditions.elementToBeClickable(LoginButton));

	        LoginButton.click();
	    }
	
	public String loginVerify()
	{
		wait.until(ExpectedConditions.elementToBeClickable(LoginButton));
		return LoginButton.getText();
	}
}
