package com.test.automation.framework.webelements;

import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Link extends Browser {
    private By by;
    private WebElement element;
    private String name;

    public Link(String name, By by) {
        this.by = by;
        this.name = name;
    }

    public void click() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " Link is NOT displayed", name + " Link is displayed");
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
                    Log.testStep("PASSED", name + " Link is clicked", name + " Link is clicked");
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
            Log.testStep("FAILED", name + " Link is NOT displayed", name + " Link is displayed");
            return null;
        } else {
            return element.getAttribute(attribute);
        }
    }

    public String getText() {
        element = Browser.findElement(by);
        if (element == null) {
            return null;
        } else {
            return element.getText();
        }
    }

    public boolean isAttributeContains(String attribute, String attributeValue) {
        element = Browser.findElement(by);
        if (element == null) {
            return false;
        } else {
            return element.getAttribute(attribute).contains(attributeValue);
        }
    }

    public boolean isAttributeEquals(String attribute, String attributeValue) {
        element = Browser.findElement(by);
        if (element == null) {
            return false;
        } else {
            return element.getAttribute(attribute).equals(attributeValue);
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

    public boolean isSelected() {
        element = Browser.findElement(by);
        if (element == null) {
            return false;
        } else {
            return element.isSelected();
        }
    }

    public boolean isTextContains(String text) {
        element = Browser.findElement(by);
        if (element == null) {
            return false;
        } else {
            return element.getText().contains(text);
        }
    }

    public boolean isTextEquals(String text) {
        element = Browser.findElement(by);
        if (element == null) {
            return false;
        } else {
            return element.getText().equals(text);
        }
    }

    public void verifyAttributeContains(String attribute, String attributeValue) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " Link is NOT displayed", name + " Link is displayed");
        } else {
            if (element.getAttribute(attribute).contains(attributeValue)) {
                Log.testStep("PASSED", name + " Link attribute contains " + attributeValue,
                        name + " Link attribute contains " + attributeValue);
            } else {
                Log.testStep("FAILED", name + " Link attribute NOT contains " + attributeValue,
                        name + " Link attribute contains " + attributeValue);
            }
        }
    }

    public void verifyAttributeEquals(String attribute, String attributeValue) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " Link is NOT displayed", name + " Link is displayed");
        } else {
            if (element.getAttribute(attribute).equals(attributeValue)) {
                Log.testStep("PASSED", name + " Link attribute is equal to " + attributeValue,
                        name + " Link attribute is equal to " + attributeValue);
            } else {
                Log.testStep("FAILED", name + " Link attribute is NOT equal to " + attributeValue,
                        name + " Link attribute is equal to " + attributeValue);
            }
        }
    }

    public void verifyDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " Link is NOT displayed", name + " Link is displayed");
        } else {
            Log.testStep("PASSED", name + " Link is displayed", name + " Link is displayed");
        }
    }

    public void verifyNotDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("PASSED", name + " Link is NOT displayed", name + " Link is NOT displayed");
        } else {
            try {
                WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
                wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
                Log.testStep("PASSED", name + " Link is NOT displayed", name + " Link is NOT displayed");
            } catch (Exception e) {
                Log.testStep("FAILED", name + " Link is displayed", name + " Link is NOT displayed");
            }
        }
    }

    public void verifyTextContains(String text) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " Link is NOT displayed", name + " Link is displayed");
        } else {
            if (element.getText().contains(text)) {
                Log.testStep("PASSED", name + " Link text contains " + text, name + " Link text contains " + text);
            } else {
                Log.testStep("FAILED", name + " Link text NOT contains " + text, name + " Link text contains " + text);
            }
        }
    }

    public void verifyTextEquals(String text) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " Link is NOT displayed", name + " Link is displayed");
        } else {
            if (element.getText().equals(text)) {
                Log.testStep("PASSED", name + " Link text is equal to " + text,
                        name + " Link text is equal to " + text);
            } else {
                Log.testStep("FAILED", name + " Link text is NOT equal to " + text,
                        name + " Link text is equal to " + text);
            }
        }
    }
}
