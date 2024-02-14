package com.test.automation.framework.webelements;

import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;

public class Alerts extends Browser {
    public static void acceptAlert() {
        driver.switchTo().alert().accept();
        Log.testStep("INFO", "" + " Pop-up alert accepted " + "",
                "" + " Pop-up alert accepted " + "");
    }
}
