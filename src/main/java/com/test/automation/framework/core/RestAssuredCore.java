package com.test.automation.framework.core;



import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.ss.formula.functions.T;
import org.hamcrest.Matchers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredCore {

    private static Map<String, String> headers = new HashMap<>();
    private static Map<String, String> parameters = new HashMap<>();
    private static Response response;
    private static String endpoint;
    private static String requestBody;
    private static String method;
    private static String responseBody;
    private static int responseStatusCode;
    private static String responseStatusLine;
    private static FileInputStream fileInputStream;
    private static Properties properties;


    public static void setAPIBaseURL(){
        String environment = System.getProperty("environment").toString();
        if (environment.equals("dev")) {
            Log.testStep("INFO", "Setting the API environment to " + environment,
                    "Setting the API environment to " + environment);
            RestAssured.baseURI = "https://openweathermap.org";
        } else if (environment.equals("test")) {
            Log.testStep("INFO", "Setting the API environment to " + environment,
                    "Setting the API environment to " + environment);
            RestAssured.baseURI = "https://api.openweathermap.org";

        }
    }

    public static void setRequestEndpoint(String value) throws Exception {
        fileInputStream = new FileInputStream(
                System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/AppConfig/config.properties");
        properties = new Properties();
        properties.load(fileInputStream);
        endpoint = properties.getProperty(value).toString();
        Log.testStep("INFO", "Setting the API endpoint to " + endpoint,
                "Setting the API endpoint to " + endpoint);
    }
    public static void setRequestParameters(String[] parameterNames, String[] parameterValues){
        for(int pidx = 0; pidx < parameterNames.length; pidx++){
            parameters.put(parameterNames[pidx], parameterValues[pidx]);
        }
        Log.testStep("INFO", "Setting Request parameters: " + parameters,
                "Setting Request parameters: " + parameters);
    }

    public static void setRequestHeaders(String[] headerNames, String[] headerValues) {
        for(int hidx = 0; hidx < headerNames.length; hidx++){
            headers.put(headerNames[hidx], headerValues[hidx]);
            Log.testStep("INFO", "Setting Request headers: " + headerNames[hidx] + " = ************************",
                    "Setting Request headers: " + headerNames[hidx] + " = ************************");
        }
    }

    public static void setRequestBody(String body){
        requestBody = body;
        Log.testStep("INFO", "Setting Request body: " + requestBody,
                "Setting Request body: " + requestBody);
    }

    public static Response triggerRequest(String methodValue) throws Exception {

        fileInputStream = new FileInputStream(
                System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/AppConfig/config.properties");
        properties = new Properties();
        properties.load(fileInputStream);
        method = properties.getProperty(methodValue).toString();

        if(method.equalsIgnoreCase("GET")){
            if(parameters.isEmpty()){
                response = RestAssured.given()
                        .headers(headers)
                        .when()
                        .get(endpoint);
            }else{
                response = RestAssured.given()
                        .headers(headers)
                        .params(parameters)
                        .when()
                        .get(endpoint);
            }

        } else if (method.equalsIgnoreCase("POST")){
            if(parameters.isEmpty()){
                response = RestAssured.given()
                        .headers(headers)
                        .body(requestBody)
                        .when()
                        .post(endpoint);
            }else {
                response = RestAssured.given()
                        .headers(headers)
                        .params(parameters)
                        .body(requestBody)
                        .when()
                        .post(endpoint);
            }

        } else if (method.equalsIgnoreCase("PUT")){
            if(parameters.isEmpty()){
                response = RestAssured.given()
                        .headers(headers)
                        .body(requestBody)
                        .when()
                        .put(endpoint);
            }else {
                response = RestAssured.given()
                        .headers(headers)
                        .params(parameters)
                        .body(requestBody)
                        .when()
                        .put(endpoint);
            }
        } else if (method.equalsIgnoreCase("PATCH")){
            if(parameters.isEmpty()){
                response = RestAssured.given()
                        .headers(headers)
                        .body(requestBody)
                        .when()
                        .patch(endpoint);
            }else {
                response = RestAssured.given()
                        .headers(headers)
                        .params(parameters)
                        .body(requestBody)
                        .when()
                        .patch(endpoint);
            }
        } else if (method.equalsIgnoreCase("DELETE")){
            if(parameters.isEmpty()){
                response = RestAssured.given()
                        .headers(headers)
                        .body(requestBody)
                        .when()
                        .delete(endpoint);
            }else {
                response = RestAssured.given()
                        .headers(headers)
                        .params(parameters)
                        .body(requestBody)
                        .when()
                        .delete(endpoint);
            }
        }

        return response;
    }

    public static void triggerRequestAndRetrieveResponse(String method) throws Exception{

        Response responseAPI = triggerRequest(method);

        responseStatusCode = responseAPI.getStatusCode();
        responseStatusLine = responseAPI.getStatusLine();
        responseBody = responseAPI.getBody().jsonPath().prettify();

        Log.testStep("INFO", "Response Status Code: " + responseStatusCode,
                "Response Status Code: " + responseStatusCode);
        Log.testStep("INFO", "Response Status Line: " + responseStatusLine,
                "Response Status Line: " + responseStatusLine);
        Log.testStep("INFO", "Response Body: " + responseBody,
                "Response Status Code: " + responseBody);

    }

    public static int getResponseStatusCode(){
        return responseStatusCode;

    }

    public static String getResponseBody(){
        return responseBody;
    }

    public static void assertStatusCode(int statusCode){
        try{
            response.then().assertThat().statusCode(statusCode);
            Log.testStep("PASSED", "Response Status Code is " + responseStatusCode,  "Response Status Code is " + statusCode);
        }catch(Exception e){
            Log.testStep("FAILED", "Response Status Code is " + responseStatusCode,  "Response Status Code should be " + statusCode);
        }
    }

    public static void assertResponseBody(String[] jsonField, String[] jsonValue){
        for(int idx = 0; idx < jsonField.length; idx++){
            assertDynamicValue(responseBody, jsonField[idx], jsonValue[idx]);
        }
    }

    // Method to perform dynamic assertions for nested paths
    private static <T> void assertDynamicValue(String responseBody, String nestedPath, T expectedValue) {
        try{
            // Perform assertions using Hamcrest matchers and JSONPath expressions
            org.hamcrest.MatcherAssert.assertThat(
                    "Dynamic assertion PASSED for nested path: " + nestedPath,
                    responseBody, Matchers.containsString("\"" + nestedPath + "\": \"" + expectedValue + "\""));
//            Log.testStep("PASSED", ",  "Response Status Code is " + statusCode);
        }catch(Exception e){
//            Log.testStep("FAILED", "Response Status Code is " + responseStatusCode,  "Response Status Code should be " + statusCode);
        }
    }

}