package com.test.automation.framework.pageobjects.saucedemo;

import com.test.automation.framework.webelements.Button;
import com.test.automation.framework.webelements.TextBox;
import org.openqa.selenium.By;

public class LoginPage {

	public static class LoginSection{
		
		private static TextBox textBoxUsername = new TextBox("Username", By.xpath("//input[@id='user-name']"));
		private static TextBox textBoxPassword = new TextBox("Password", By.xpath("//input[@id='password']"));
		private static Button buttonLogin = new Button("Sign In", By.xpath("//input[@id='login-button']"));
		
		public static void loginUser(String username, String password) {
			textBoxUsername.setText(username);
			textBoxPassword.setPassword(password);
			buttonLogin.click();
		}
		
	}
	
}