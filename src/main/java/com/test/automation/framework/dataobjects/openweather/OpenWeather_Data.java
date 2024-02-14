package com.test.automation.framework.dataobjects.openweather;

import com.test.automation.framework.core.DataAccessLayer;

public class OpenWeather_Data {

    public static class TestDataTC0001 {

        public static String getHeaderKey() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Header Key");
        }

        public static String getHeaderValue() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Header Value");
        }

        public static String getParameterKey() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Parameter Key");
        }

        public static String getParameterValue() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Parameter Value");
        }

        public static String getEndpointValue() throws Exception {
            return "openweather_weather_endpoint";
        }

        public static String getMethod() throws Exception {
            return "get_method";
        }

        public static String getStatusCode() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Status Code");
        }

        public static String getFieldKey() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Field Key");
        }

        public static String getFieldValue() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Field Value");
        }

    }

    public static class TestDataTC0002 {

    }

}