package com.test.automation.framework.saucedemo;

import com.test.automation.framework.apiobjects.OpenWeatherAPI;
import com.test.automation.framework.core.RestAssuredCore;
import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;
import com.test.automation.framework.dataobjects.openweather.OpenWeather_Data;
import org.testng.annotations.Test;


public class SmokeTesting extends Browser {
	
	@Test
	public static void TC0003() throws Exception{
		
		Log.setStoryName("SwagLabs Sauce Demo");
		Log.setTestScriptName("Purchase Swag Labs products");
		Log.setTestScriptDescription("User is purchases Swag Labs products");
//		String[][] myStrings = {
//				{"Authorization", "x-api-key", "customer-id"},
//				{"token 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", "ac1634cd0369751c55eb8d24aadaa8a7", "d35320d4-5c0f-49b6-b8e5-5dcb64a6969e"}
//		};
//		APIConfig.getRequestHeaders(myStrings);

//		String[] headerNames = {"Authorization", "x-api-key"};
//		String[] headerValues = {"token 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", "ac1634cd0369751c55eb8d24aadaa8a7"};

		String[] headerNames = {"x-api-key"};
		String[] headerValues = {"ac1634cd0369751c55eb8d24aadaa8a7"};

		String[] parameterNames = {"q", "units"};
		String[] parameterValues = {"New York City", "ac1634cd0369751c55eb8d24aadaa8a7"};

//		RestAssuredCore.setAPIBaseURL();
//		RestAssuredCore.setRequestHeaders(headerNames, headerValues);
//		RestAssuredCore.setRequestParameters(parameterNames, parameterValues);
//		RestAssuredCore.setRequestEndpoint("");
//		RestAssuredCore.triggerRequestAndRetrieveResponse("GET");
//		RestAssuredCore.getResponseStatusCode();
//		RestAssuredCore.getResponseBody();

		OpenWeatherAPI.setOpenWeatherEndpoint(OpenWeather_Data.TestDataTC0001.getEndpointValue());
		OpenWeatherAPI.setOpenWeatherHeaders(OpenWeather_Data.TestDataTC0001.getHeaderKey(), OpenWeather_Data.TestDataTC0001.getHeaderValue());
		OpenWeatherAPI.setOpenWeatherParameters(OpenWeather_Data.TestDataTC0001.getParameterKey(), OpenWeather_Data.TestDataTC0001.getParameterValue());
		OpenWeatherAPI.triggerOpenWeatherRequest(OpenWeather_Data.TestDataTC0001.getMethod());
		OpenWeatherAPI.validateResponseStatusCode(OpenWeather_Data.TestDataTC0001.getStatusCode());
		OpenWeatherAPI.validateResponseBody(OpenWeather_Data.TestDataTC0001.getFieldKey(), OpenWeather_Data.TestDataTC0001.getFieldValue());


//		LoginPage.LoginSection.loginUser(SauceDemo_Data.TestDataTC0001.getUsername(), SauceDemo_Data.TestDataTC0001.getPassword());
//		HomePage.Header.verifySwagLabsLogo();
//		HomePage.InventoryItems.clickAddToCart(SauceDemo_Data.TestDataTC0001.getProduct());
//		HomePage.Header.clickCart();
//		Thread.sleep(5000);
	}
	
}
