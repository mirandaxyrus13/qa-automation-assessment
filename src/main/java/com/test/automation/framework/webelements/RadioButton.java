package com.test.automation.framework.webelements;

import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RadioButton extends Browser {
    private By by;
    private WebElement element;
    private String name;

    public RadioButton(String name, By by) {
        this.by = by;
        this.name = name;
    }

    public void click() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " RadioButton is NOT displayed", name + " RadioButton is displayed");
        } else {
            for (int i = 0; i <= 2; i++) {
                try {
                    int count = 0;
                    while (!element.isDisplayed() && count != 5) {
                        delay(1);
                        count++;
                    }
                    wait.ignoring(StaleElementReferenceException.class)
                            .until(ExpectedConditions.elementToBeClickable(element));
                    element.click();
                    Log.testStep("PASSED", name + " RadioButton is clicked", name + " RadioButton is clicked");
                    break;
                } catch (Exception e) {
                    Log.setLog(StringUtils.substringBefore(e.getMessage(), ":"));
                    delay(1);
                    element = Browser.findElement(by);
                    wait.ignoring(StaleElementReferenceException.class)
                            .until(ExpectedConditions.elementToBeClickable(element));
                }
            }
        }
    }

    public String getAttribute(String attribute) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " RadioButton is NOT displayed", name + " RadioButton is displayed");
            return null;
        } else {
            return element.getAttribute(attribute);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            return false;
        } else {
            return element.isDisplayed();
        }
    }

    public boolean isEnabled() {
        element = Browser.findElement(by);
        if (element == null) {
            return false;
        } else {
            return element.isEnabled();
        }
    }

    public boolean isSelected() {
        element = Browser.findElement(by);
        if (element == null) {
            return false;
        } else {
            return element.isSelected();
        }
    }

    public void verifyDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " RadioButton is NOT displayed", name + " RadioButton is displayed");
        } else {
            Log.testStep("PASSED", name + " RadioButton is displayed", name + " RadioButton is displayed");
        }

    }

    public void verifyNotDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("PASSED", name + " RadioButton is NOT displayed", name + " RadioButton is NOT displayed");
        } else {
            try {
                WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
                wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
                Log.testStep("PASSED", name + " RadioButton is NOT displayed", name + " RadioButton is NOT displayed");
            } catch (Exception e) {
                Log.testStep("FAILED", name + " RadioButton is displayed", name + " RadioButton is NOT displayed");
            }
        }
    }
}
