package com.test.automation.framework.webelements;

import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element extends Browser {
    private By by;
    private WebElement element;
    private String name;

    public Element(String name, By by) {
        this.by = by;
        this.name = name;
    }

    public void click() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
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
                    Log.testStep("PASSED", name + " is clicked", name + " is clicked");
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

    public void clickFromHover() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            int count = 0;
            while (!element.isDisplayed() && count != 5) {
                delay(1);
                count++;
            }
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
            Actions builder = new Actions(Browser.getDriver());
            builder.moveToElement(element).build().perform();
            element.click();
            Log.testStep("PASSED", name + " is clicked", name + " is clicked");
        }
    }

    public void clickJS() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            int count = 0;
            while (!element.isDisplayed() && count != 5) {
                delay(1);
                count++;
            }
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
            executor.executeScript("arguments[0].click();", element);
            Log.testStep("PASSED", name + " is clicked", name + " is clicked");
        }
    }

    public void doubleClick() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            int count = 0;
            while (!element.isDisplayed() && count != 5) {
                delay(1);
                count++;
            }
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
            Actions action = new Actions(Browser.getDriver());
            action.moveToElement(element);
            action.doubleClick();
            action.perform();
            Log.testStep("PASSED", name + " is double clicked", name + " is double clicked");
        }
    }

    public String getAttribute(String attribute) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
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

    public String getValue(String attribute) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
            return null;
        } else {
            return element.getAttribute(attribute);
        }
    }

    public void hoverElement() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            int count = 0;
            while (!element.isDisplayed() && count != 5) {
                delay(1);
                count++;
            }
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
            Actions builder = new Actions(Browser.getDriver());
            builder.moveToElement(element).build().perform();
            Log.testStep("PASSED", "Hover to " + name, "Hover to " + name);
        }
    }

    public void hoverElementJS() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            int count = 0;
            while (!element.isDisplayed() && count != 5) {
                delay(1);
                count++;
            }
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            ((JavascriptExecutor) Browser.getDriver()).executeScript(mouseOverScript, element);
            Log.testStep("PASSED", "Hover to " + name, "Hover to " + name);
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

    public void openNewTab() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            try {
                Actions newwin = new Actions(Browser.getDriver());
                newwin.keyDown(Keys.SHIFT).click(element).keyUp(Keys.SHIFT).build().perform();
            } catch (Exception e) {
            }
            Log.testStep("PASSED", name + " is opened in new tab", name + " is opened in new tab");
        }
    }

    public void rightClick() {
        element = Browser.findElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            int count = 0;
            while (!element.isDisplayed() && count != 5) {
                delay(1);
                count++;
            }
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
            Actions action = new Actions(Browser.getDriver()).contextClick(element);
            action.build().perform();
            Log.testStep("PASSED", name + " is right clicked", name + " is clicked");
        }
    }

    public void setAttribute(String attribute, String attributeValue) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
            js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + attributeValue + "')", element);
            Log.testStep("PASSED", name + " attribute '" + attribute + "' is updated to " + attributeValue,
                    name + " attribute '" + attribute + "' is updated to " + attributeValue);
        }
    }

    public void submit() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            Log.testStep("PASSED", name + " is submitted", name + " is submitted");
            element.submit();
        }
    }

    public void verifyAttributeContains(String attribute, String attributeValue) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            if (element.getAttribute(attribute).contains(attributeValue)) {
                Log.testStep("PASSED", name + " attribute contains " + attributeValue,
                        name + " attribute contains " + attributeValue);
            } else {
                Log.testStep("FAILED", name + " attribute NOT contains " + attributeValue,
                        name + " attribute contains " + attributeValue);
            }
        }
    }

    public void verifyAttributeEquals(String attribute, String attributeValue) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            if (element.getAttribute(attribute).equals(attributeValue)) {
                Log.testStep("PASSED", name + " attribute is equal to " + attributeValue,
                        name + " attribute is equal to " + attributeValue);
            } else {
                Log.testStep("FAILED", name + " attribute is NOT equal to " + attributeValue,
                        name + " attribute is equal to " + attributeValue);
            }
        }
    }

    public void verifyDisabled() {
        element = Browser.findElement(by);
        String disabled = element.getAttribute("disabled");
        if (disabled == null)
            Log.testStep("FAILED", name + " is disabled", name + " is NOT disabled");
        else if (disabled.contentEquals("true"))
            Log.testStep("PASSED", name + " is disabled", name + " is disabled");
        else
            Log.testStep("FAILED", name + " is disabled", name + " is NOT disabled");
    }

    public void verifyDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " Element is displayed");
        } else {
            Log.testStep("PASSED", name + " is displayed", name + " Element is displayed");
        }
    }

    public void verifyEnabled() {
        element = Browser.findElement(by);
        if (element.isEnabled()) {
            Log.testStep("PASSED", name + " is enabled", name + " is enabled");
        } else {
            Log.testStep("FAILED", name + " is disabled", name + " is enabled");
        }
    }

    public void verifyNotDisplayed() {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("PASSED", name + " is NOT displayed", name + " is NOT displayed");
        } else {
            try {
                WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
                wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
                Log.testStep("PASSED", name + " is NOT displayed", name + " is NOT displayed");
            } catch (Exception e) {
                Log.testStep("FAILED", name + " is displayed", name + " is NOT displayed");
            }
        }

    }

    public void verifySelected() {
        element = Browser.findElement(by);
        if (element.isSelected()) {
            Log.testStep("PASSED", name + " is selected", name + " is selected");
        } else {
            Log.testStep("FAILED", name + " is NOT selected", name + " is selected");
        }
    }

    public void verifyTextContains(String text) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            if (element.getText().contains(text)) {
                Log.testStep("PASSED", name + " text contains " + text, name + " text contains " + text);
            } else {
                Log.testStep("FAILED", name + " text NOT contains " + text, name + " text contains " + text);
            }
        }
    }

    public void verifyTextEquals(String text) {
        element = Browser.findElement(by);
        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {
            if (element.getText().equals(text)) {
                Log.testStep("PASSED", name + " text is equal to " + text, name + " text is equal to " + text);
            } else {
                Log.testStep("FAILED", name + " text is NOT equal to " + text, name + " text is equal to " + text);
            }
        }
    }
}
