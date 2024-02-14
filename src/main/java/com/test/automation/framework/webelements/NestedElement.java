package com.test.automation.framework.webelements;

import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NestedElement extends Browser {
    private By childBy;
    private List<WebElement> elements;
    private WebElement element;
    private String name;
    private By parentBy;

    public NestedElement(String name, By parentBy, By childBy) {
        this.parentBy = parentBy;
        this.childBy = childBy;
        this.name = name;
    }

    public void clickByAttributeValue(String attribute, String attributeValue) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        elements = findElements(parentBy, childBy);
        for (WebElement element : elements) {
            if (element.getAttribute(attribute).contains(attributeValue)) {
                int count = 0;
                while (!element.isDisplayed() && count != 5) {
                    delay(1);
                    count++;
                }
                wait.ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                Log.testStep("PASSED", name + " with attribute '" + attributeValue + "' is clicked",
                        name + " with attribute '" + attributeValue + "' is clicked");
                break;
            }
        }
    }

    public void clickByIndex(int index) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        elements = findElements(parentBy, childBy);
        int count = 0;
        while (!elements.get(index).isDisplayed() && count != 5) {
            delay(1);
            count++;
        }
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(elements.get(index)));
        elements.get(index).click();
        Log.testStep("PASSED", name + " with index '" + index + "' is clicked",
                name + " with index '" + index + "' is clicked");
    }

    public void clickByText(String text) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());

        elements = getDriver().findElements(childBy);
        element = getDriver().findElement(parentBy);

        element.click();
        for (WebElement el : elements) {
            if (el.getText().contains(text)) {
                int count = 0;
                while (!el.isDisplayed() && count != 5) {
                    delay(1);
                    count++;
                }
                wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(el));
                el.click();
                Log.testStep("PASSED", name + " with text '" + text + "' is clicked",
                        name + " with text '" + text + "' is clicked");
                break;
            }
        }
    }

    public String getAttributeByIndex(String attribute, int index) {
        elements = findElements(parentBy, childBy);
        return elements.get(index).getAttribute(attribute);
    }

    public int getIndexByAttribute(String attribute, String attributeValue) {
        int index = 0;
        elements = findElements(parentBy, childBy);
        for (WebElement element : elements) {
            if (element.getAttribute(attributeValue).equals(attribute)) {
                index = elements.indexOf(element);
                break;
            }
        }
        return index;
    }

    public int getIndexByText(String text) {
        int index = 0;
        elements = findElements(parentBy, childBy);
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                index = elements.indexOf(element);
                break;
            }
        }
        return index;
    }

    public void getValueByEnteredText(String text) {

        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());

        elements = getDriver().findElements(childBy);
        element = getDriver().findElement(parentBy);

        if (element == null) {
            Log.testStep("FAILED", name + " TextBox is NOT displayed", name + " TextBox is displayed");
        } else {
            for (int i = 0; i <= 2; i++) {
                int count = 0;
                while (!element.isDisplayed() && count != 5) {
                    delay(1);
                    count++;
                }
                wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
                element.clear();
                element.sendKeys(text);
                Log.testStep("INFO", name + " TextBox is populated with '" + text + "'",
                        name + " TextBox is populated with '" + text + "'");
            }

            for (WebElement el : elements) {
                if (el.getText().contains(text)) {
                    int counter = 0;
                    while (!el.isDisplayed() && counter != 5) {
                        delay(1);
                        counter++;
                    }
                    wait.ignoring(StaleElementReferenceException.class)
                            .until(ExpectedConditions.elementToBeClickable(el));
                    el.click();
                    Log.testStep("PASSED", name + " with text '" + text + "' is clicked",
                            name + " with text '" + text + "' is clicked");
                    break;
                }
            }
        }

    }

    public void hoverValueAndClick(String text) {

        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());

        elements= getDriver().findElements(childBy);
        element = getDriver().findElement(parentBy);

        if (element == null) {
            Log.testStep("FAILED", name + " is NOT displayed", name + " is displayed");
        } else {

            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            Actions action = new Actions(Browser.getDriver());
            action.moveToElement(element);


            for (WebElement el : elements) {
                if (el.getText().contains(text)) {
                    int counter = 0;
                    while (!el.isDisplayed() && counter != 5) {
                        delay(1);
                        counter++;
                    }
                    action.moveToElement(el);
                    wait.ignoring(StaleElementReferenceException.class)
                            .until(ExpectedConditions.elementToBeClickable(el));
                    el.click();

                    Log.testStep("PASSED", name + " with text '" + text + "' is clicked",
                            name + " with text '" + text + "' is clicked");
                    break;
                }else {

                }
            }
        }

    }

    public String getName() {
        return name;
    }

    public int getRowCount() {
        elements = findElements(parentBy, childBy);
        return elements.size();
    }

    public String getTextByIndex(int index) {
        elements = findElements(parentBy, childBy);
        return elements.get(index).getText();
    }
}
