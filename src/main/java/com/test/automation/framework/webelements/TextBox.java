package com.test.automation.framework.webelements;

import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextBox extends Browser {
    private By by;
    private WebElement element;
    private String name;

    public TextBox(String name, By by) {
        this.by = by;
        this.name = name;
    }

    public void clear() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            Log.testStep("PASSED", name + " TextBox is cleared ", name + " TextBox is cleared");
            element.clear();
        }
    }

    public String getAttribute(String attribute) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
            return null;
        } else {
            return element.getAttribute(attribute);
        }
    }

    public String getName() {
        return name;
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

    public void setPassword(String password) {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            for (int i = 0; i <= 2; i++) {
                try {
                    int count = 0;
                    while (!element.isDisplayed() && count != 5) {
                        delay(1);
                        count++;
                    }
                    wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
                    element.clear();
                    element.sendKeys(password);
                    Log.testStep("PASSED", name + " TextBox is populated with '******'",
                            name + " TextBox is populated with '******'");
                    break;
                } catch (Exception e) {
                    Log.setLog(StringUtils.substringBefore(e.getMessage(), ":"));
                    delay(1);
                    element = Browser.findElement(by);
                    wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
                }
            }
        }
    }

    public void setText(String text) {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            for (int i = 0; i <= 2; i++) {
                try {
                    int count = 0;
                    while (!element.isDisplayed() && count != 5) {
                        delay(1);
                        count++;
                    }
                    wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
                    element.clear();
                    element.sendKeys(text);
                    Log.testStep("PASSED", name + " TextBox is populated with '" + text + "'",
                            name + " TextBox is populated with '" + text + "'");
                    break;
                } catch (Exception e) {
                    Log.setLog(StringUtils.substringBefore(e.getMessage(), ":"));
                    delay(1);
                    element = Browser.findElement(by);
                    wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
                }
            }
        }
    }

    public void setTextAndEnter(String text) {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            for (int i = 0; i <= 2; i++) {
                try {
                    int count = 0;
                    while (!element.isDisplayed() && count != 5) {
                        delay(1);
                        count++;
                    }
                    wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
                    element.clear();
                    element.sendKeys(text);
                    element.sendKeys(Keys.ENTER);
                    Log.testStep("PASSED", name + " TextBox is populated with '" + text + "'",
                            name + " TextBox is populated with '" + text + "'");
                    break;
                } catch (Exception e) {
                    Log.setLog(StringUtils.substringBefore(e.getMessage(), ":"));
                    delay(1);
                    element = Browser.findElement(by);
                    wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
                }
            }
        }
    }

    public void verifyAttributeContains(String attribute, String attributeValue) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            if (element.getAttribute(attribute).contains(attributeValue)) {
                Log.testStep("PASSED", name + " TextBox attribute contains " + attributeValue,
                        name + " TextBox attribute contains " + attributeValue);
            } else {
                Log.testStep("FAILED", name + " TextBox attribute NOT contains " + attributeValue,
                        name + " TextBox attribute contains " + attributeValue);
            }
        }
    }

    public void verifyAttributeEquals(String attribute, String attributeValue) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            if (element.getAttribute(attribute).equals(attributeValue)) {
                Log.testStep("PASSED", name + " TextBox attribute is equal to " + attributeValue,
                        name + " TextBox attribute is equal to " + attributeValue);
            } else {
                Log.testStep("FAILED", name + " TextBox attribute is NOT equal to " + attributeValue,
                        name + " TextBox attribute is equal to " + attributeValue);
            }
        }
    }

    public void verifyDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            Log.testStep("PASSED", name + " TextBox is displayed", name + " TextBox is displayed");
        }
    }

    public void verifyEmpty() {
        element = Browser.findElement(by);
        if (element.getAttribute("value").isEmpty()) {
            Log.testStep("PASSED", name + " TextBox is empty", name + " TextBox is empty");
        } else {
            Log.testStep("FAILED", name + " TextBox is NOT empty", name + " TextBox is empty");
        }
    }

    public void verifyNotDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("PASSED", name + " TextBox is NOT displayed", name + " TextBox is NOT displayed");
        } else {
            try {
                WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
                wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
                Log.testStep("PASSED", name + " TextBox is NOT displayed", name + " TextBox is NOT displayed");
            } catch (Exception e) {
                Log.testStep("FAILED", name + " TextBox is displayed", name + " TextBox is NOT displayed");
            }
        }
    }

    public void verifyTextContains(String text) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            if (element.getText().contains(text)) {
                Log.testStep("PASSED", name + " TextBox text contains " + text,
                        name + " TextBox text contains " + text);
            } else {
                Log.testStep("FAILED", name + " TextBox text NOT contains " + text,
                        name + " TextBox text contains " + text);
            }
        }
    }

    public void verifyTextEquals(String text) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            if (element.getText().equals(text)) {
                Log.testStep("PASSED", name + " TextBox text is equal to " + text,
                        name + " TextBox text is equal to " + text);
            } else {
                Log.testStep("FAILED", name + " TextBox text is NOT equal to " + text,
                        name + " TextBox text is equal to " + text);
            }
        }
    }

}
