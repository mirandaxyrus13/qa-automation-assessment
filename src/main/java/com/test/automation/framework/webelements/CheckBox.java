package com.test.automation.framework.webelements;

import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckBox extends Browser {
    private By by;
    private WebElement element;
    private String name;

    public CheckBox(String name, By by) {
        this.by = by;
        this.name = name;
    }

    public void check() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());

        if (element == null) {
            Log.testStep("FAILED", name + " CheckBox is NOT displayed", name + " CheckBox is displayed");
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
                    if (element.isEnabled() && element.isSelected()) {
                        Log.testStep("PASSED", name + " CheckBox is already ticked",
                                name + " CheckBox is already ticked");
                    } else {
                        element.click();
                        Log.testStep("PASSED", name + " CheckBox is ticked", name + " CheckBox is ticked");
                    }
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

    public void click() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());

        if (element == null) {
            Log.testStep("FAILED", name + " CheckBox is NOT displayed", name + " CheckBox is displayed");
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
                    Log.testStep("PASSED", name + " CheckBox is clicked", name + " CheckBox is clicked");
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

    public String getName() {
        return name;
    }

    public String getValue(String attribute) {
        element = Browser.findElement(by);
        if (element == null) {
            return null;
        } else {
            return element.getAttribute(attribute);
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
            Log.testStep("FAILED", name + " CheckBox is NOT displayed", name + " Element is displayed");
            return false;
        } else {
            return element.isEnabled();
        }
    }

    public boolean isSelected() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " CheckBox is NOT displayed", name + " CheckBox is displayed");
            return false;
        } else {
            return element.isSelected();
        }
    }

    public void tick(Boolean value) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " CheckBox is NOT displayed", name + " CheckBox is displayed");
        } else {
            if (value) {
                if (element.isSelected() == false) {
                    try {
                        element = new WebDriverWait(Browser.getDriver(), setWaitDurationTime())
                                .ignoring(StaleElementReferenceException.class)
                                .until(ExpectedConditions.elementToBeClickable(by));
                        element.click();
                    } catch (Exception e) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e1) {
                        }
                        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
                        executor.executeScript("arguments[0].click();", element);
                    }
                }
            } else if (!value) {
                if (element.isSelected() == true) {
                    try {
                        element = new WebDriverWait(Browser.getDriver(), setWaitDurationTime())
                                .ignoring(StaleElementReferenceException.class)
                                .until(ExpectedConditions.elementToBeClickable(by));
                        element.click();
                    } catch (Exception e) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e1) {
                        }
                        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
                        executor.executeScript("arguments[0].click();", element);
                    }
                }
            }
            Log.testStep("PASSED", name + " CheckBox value is set to " + value,
                    name + " CheckBox value is set to " + value);
        }
    }

    public void unCheck() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());

        if (element == null) {
            Log.testStep("FAILED", name + " CheckBox is NOT displayed", name + " CheckBox is displayed");
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
                    if (element.isEnabled() && element.isSelected()) {
                        element.click();
                        Log.testStep("PASSED", name + " CheckBox is unticked", name + " CheckBox is unticked");
                    } else {
                        Log.testStep("PASSED", name + " CheckBox is already unticked",
                                name + " CheckBox is already unticked");
                    }
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
            Log.testStep("FAILED", name + " CheckBox is NOT displayed", name + " CheckBox is displayed");
        } else {
            Log.testStep("PASSED", name + " CheckBox is displayed", name + " CheckBox is displayed");
        }
    }

    public void verifyNotDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("PASSED", name + " CheckBox is NOT displayed", name + " CheckBox is NOT displayed");
        } else {
            try {
                WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
                wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
                Log.testStep("PASSED", name + " CheckBox is NOT displayed", name + " CheckBox is NOT displayed");
            } catch (Exception e) {
                Log.testStep("FAILED", name + " CheckBox is displayed", name + " CheckBox is NOT displayed");
            }
        }
    }
}
