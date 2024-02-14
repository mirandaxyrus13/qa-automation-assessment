package com.test.automation.framework.dataobjects.saucedemo;

import com.test.automation.framework.core.DataAccessLayer;

public class SauceDemo_Data {

    public static class TestDataTC0001 {

        public static String getUsername() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Username");
        }

        public static String getPassword() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Password");
        }

        public static String getProduct() throws Exception {
            return DataAccessLayer.getTestData("TC0001", "Product");
        }

    }

    public static class TestDataTC0002 {

        public static String getUsername() throws Exception {
            return DataAccessLayer.getTestData("TC0002", "Username");
        }

        public static String getPassword() throws Exception {
            return DataAccessLayer.getTestData("TC0002", "Password");
        }

        public static String getProduct() throws Exception {
            return DataAccessLayer.getTestData("TC0002", "Product");
        }

    }

}