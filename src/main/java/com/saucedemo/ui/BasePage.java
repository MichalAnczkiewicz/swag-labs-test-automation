package com.saucedemo.ui;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class BasePage {

    private static final Logger LOGGER
            = LogManager.getLogger(BasePage.class);
    protected final Page page;

    public BasePage(Page page) {

        this.page = page;
    }

    protected void click(Locator locator) {

        try {

            LOGGER.info("Clicking element: " + locator);
            locator.hover();
            locator.click();
        } catch (TimeoutError timeoutError) {

            throw new TimeoutError("Element hasn't been found: " + locator);
        }
    }

    protected boolean isElementVisible(Locator locator) {

        waitForElementToBeVisible(locator);
        return locator.isVisible();
    }

    protected void fill(Locator locator, String value) {

        waitForElementToBeVisible(locator);
        locator.fill(value);
    }

    protected String getTextFromElement(Locator locator) {

        waitForElementToBeVisible(locator);
        return locator.textContent().trim();
    }

    protected List<String> getTextFromAllElements(Locator locator) {

        waitForElementToBeVisible(locator);
        return locator.allTextContents().stream().map(String::trim).collect(Collectors.toList());
    }

    protected int getNumberOfElements(Locator locator) {

        waitForElementToBeVisible(locator);
        return locator.count();
    }

    protected void waitForElementToBeVisible(Locator locator) {

        try {

            locator.first().waitFor(new Locator.WaitForOptions().setState(VISIBLE));
        } catch (TimeoutError error) {
            throw new TimeoutError("Element hasn't been found: " + locator);
        }
    }
}
