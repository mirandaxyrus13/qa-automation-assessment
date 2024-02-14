package com.test.automation.framework.pageobjects.saucedemo;

import com.test.automation.framework.webelements.Button;
import com.test.automation.framework.webelements.Element;
import com.test.automation.framework.webelements.ListElement;
import org.openqa.selenium.By;

public class HomePage {

	public static class Header{
		
		private static Element logoSwagLabs = new Element("Swag Labs Logo", By.xpath("//div[@class='app_logo']"));
		private static Button buttonCart = new Button("Cart", By.xpath("//a[@class='shopping_cart_link']"));
		
		public static void verifySwagLabsLogo() {
			logoSwagLabs.verifyDisplayed();

		}

		public static void clickCart() {
			buttonCart.click();
		}
	}
	
	public static class InventoryItems{

		
		private static ListElement listInventoryProducts = new ListElement("Inventory Products", By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_name']"));

		public static void clickProduct(String text) {
			listInventoryProducts.clickByText(text);
		}

		public static void clickAddToCart(String text) {
			Button buttonAddToCart = new Button(text + " Add to Cart", By.xpath("//div[@id]//div[text()='"+text+"']//ancestor::div[@class='inventory_item_description']//button"));
			buttonAddToCart.click();
		}
	}
}
