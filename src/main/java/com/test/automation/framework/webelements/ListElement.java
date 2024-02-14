package com.test.automation.framework.webelements;

import com.test.automation.framework.core.Browser;
import com.test.automation.framework.core.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ListElement extends Browser {
    private By by;
    private List<WebElement> elements;
    private String name;

    public ListElement(String name, By by) {
        this.by = by;
        this.name = name;
    }

    public void clickByAttributeValue(String attribute, String attributeValue) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), setWaitDurationTime());
        elements = findElements(by);
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
        elements = findElements(by);
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

        elements = getDriver().findElements(by);

        for (WebElement element : elements) {
            if (element.getText().contains(text)) {
                int count = 0;
                while (!element.isDisplayed() && count != 5) {
                    delay(1);
                    count++;
                }
                wait.ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                Log.testStep("PASSED", name + " with text '" + text + "' is clicked",
                        name + " with text '" + text + "' is clicked");
                break;
            }
        }
    }

    public String getAttributeByIndex(String attribute, int index) {
        elements = findElements(by);
        return elements.get(index).getAttribute(attribute);
    }

    public int getIndexByAttribute(String attribute, String attributeValue) {
        int index = 0;
        elements = findElements(by);
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
        elements = findElements(by);
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                index = elements.indexOf(element);
                break;
            }
        }
        return index;
    }

    public int getRowCount() {
        elements = findElements(by);
        return elements.size();
    }

    public String getTextByIndex(int index) {
        elements = findElements(by);
        return elements.get(index).getText();
    }
}
