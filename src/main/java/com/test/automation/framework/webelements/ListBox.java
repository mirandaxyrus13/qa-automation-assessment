package com.test.automation.framework.webelements;

import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ListBox extends Browser {
    private By by;
    private WebElement element;
    private String name;
    private Select select;

    public ListBox(String name, By by) {
        this.by = by;
        this.name = name;
    }

    public String getFirstSelectedValue() {
        element = Browser.findElement(by);
        select = new Select(element);
        if (element == null) {
            Log.testStep("FAILED", name + " ListBox is NOT displayed", name + " ListBox is displayed");
            return "";
        } else {
            return select.getFirstSelectedOption().getText();
        }
    }

    public int getOptionSize() {
        element = Browser.findElement(by);
        if (element == null) {
            return 0;
        } else {
            select = new Select(element);
            List<WebElement> options = select.getOptions();
            return options.size();
        }
    }

    public String getValue() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " ListBox is NOT displayed", name + " ListBox is displayed");
            return "";
        } else {
            return element.getAttribute("value");
        }
    }

    public boolean hasDefaultSelectedValue() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " ListBox is NOT displayed", name + " ListBox is displayed");
            return false;
        } else {
            select = new Select(element);
            return (select.getFirstSelectedOption().getAttribute("disabled") != null);
        }
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

    public void selectByIndex(int index) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " ListBox is NOT displayed", name + " ListBox is displayed");
        } else {
            int count = 0;
            while (!element.isDisplayed() && count != 5) {
                delay(1);
                count++;
            }
            select = new Select(element);
            select.selectByIndex(index);
            Log.testStep("PASSED", "Selected index '" + index + "' from " + name + " ListBox",
                    "Selected index '" + index + "' from " + name + "ListBox");
        }
    }

    public void selectByVisibleText(String value) {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " ListBox is NOT displayed", name + " ListBox is displayed");
        } else {
            for (int i = 0; i <= 2; i++) {
                try {
                    int count = 0;
                    while (!element.isDisplayed() && count != 5) {
                        delay(1);
                        count++;
                    }
                    select = new Select(element);
                    select.selectByVisibleText(value);
                    Log.testStep("PASSED", "Selected '" + value + "' from " + name + " ListBox",
                            "Selected '" + value + "' from " + name + "ListBox");
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

    public void verifyDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " ListBox is NOT displayed", name + " ListBox is displayed");
        } else {
            Log.testStep("PASSED", name + " ListBox is displayed", name + " ListBox is displayed");
        }
    }

    public void verifyNotDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("PASSED", name + " ListBox is NOT displayed", name + " ListBox is NOT displayed");
        } else {
            try {
                WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
                wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
                Log.testStep("PASSED", name + " ListBox is NOT displayed", name + " ListBox is NOT displayed");
            } catch (Exception e) {
                Log.testStep("FAILED", name + " ListBox is displayed", name + " ListBox is NOT displayed");
            }
        }
    }
}
