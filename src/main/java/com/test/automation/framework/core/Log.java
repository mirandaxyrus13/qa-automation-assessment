package com.test.automation.framework.core;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.junit.Assert;

import java.io.IOException;

public class Log extends Browser{
    private static Logger consoleLog = Logger.getLogger(Log.class.getName());

    public static void setLog(String name) {
        Reporter.log("[Log:] " + name);
        consoleLog.info("[Log:] " + name);
        test.info("[Log:] " + name);
    }

    public static void setScreenshot(String name) throws IOException {
        Reporter.log("[Screenshot:] " + name);
        consoleLog.info("[Screenshot:] " + name);
        test.info("Snapshot below: " + test.addScreenCaptureFromPath(Screenshot.path, name));
    }

    public static void setStoryName(String name) {
        Reporter.log("[Story Name] " + name);
        consoleLog.info("[Story Name] " + name);
        test.info("[Story Name] " + name);
    }

    public static void setTestCaseNote(String name) {
        Reporter.log("[Note:] " + name);
        consoleLog.info("[Note:] " + name);
        test.info("[Note:] " + name);
    }

    public static void setTestScriptDescription(String name) {
        Reporter.log("[Test Script Description] " + name);
        consoleLog.info("[Test Script Description] " + name);
        test.info("[Test Script Description] " + name);
    }

    public static void setTestScriptName(String name) {
        Reporter.log("[Test Script Name] " + name);
        consoleLog.info("[Test Script Name] " + name);
        test.info("[Test Script Name] " + name);
    }

    public static void setTestStep(String name) {
        Reporter.log("[Test Step:] " + name);
        consoleLog.info("[Test Step:] " + name);
        test.info("[Test Step:] " + name);
    }

    public static void setWarning(String name) {
        Reporter.log("[Log:] " + name);
        consoleLog.warn("[Log:] " + name);
        test.info("[Log:] " + name);
    }

    public static void testStep(String tag, String Actual, String Expected) {

        if (tag.toUpperCase() == "PASSED") {
            Reporter.log("[" + tag + "] " + Actual);
            consoleLog.info("[" + tag + "] " + Actual);
            test.pass("[" + tag + "] " + Actual);
        } else if (tag.toUpperCase() == "FAILED") {
            Reporter.log("[" + tag + "] Expected: " + Expected);
            Reporter.log("[" + tag + "] Actual: " + Actual);
            consoleLog.warn("[" + tag + "] Expected: " + Expected);
            consoleLog.warn("[" + tag + "] Actual: " + Actual);
            test.info("[" + tag + "] Expected: " + Expected);
            test.fail("[" + tag + "] Actual: " + Actual);
            Assert.fail(Actual);
        } else if (tag.toUpperCase() == "WARNING") {
            Reporter.log("[" + tag + "] " + Actual);
            consoleLog.warn("[" + tag + "] " + Actual);
            test.warning("[" + tag + "] " + Actual);
        } else {
            Reporter.log("[" + tag + "] " + Actual);
            consoleLog.info("[" + tag + "] " + Actual);
            test.info("[" + tag + "] " + Actual);
        }
    }
}
