package com.saucedemo.helpers;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.saucedemo.helpers.interfaces.TestHelpersWeb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.saucedemo.ui.BaseTest.SERVICE_URL;

public class SauceLabsTestHelpersWeb implements TestHelpersWeb {

    private static final Logger LOGGER
            = LogManager.getLogger(SauceLabsTestHelpersWeb.class);

    private final Page page;

    public SauceLabsTestHelpersWeb(Page page) {

        this.page = page;
    }

    @Override
    public void openWebsite(String url) {

        boolean success = false;
        int maxRetries = 2;

        for (int retryCount = 0; retryCount < maxRetries; retryCount++) {
            try {
                page.waitForResponse(response ->
                                response.url().contains(url) && response.status() == 200,
                        () -> page.navigate(SERVICE_URL.concat(url)));
                success = true;
                break;
            } catch (TimeoutError e) {

                LOGGER.error(String.format("Page: %s didn't open successfully %d times. ", url, retryCount+1));
            }
        }

        if (!success) {
            Assertions.fail(String.format("Page %s didn't respond %d times. Testing aborted", url, maxRetries));
        }
    }
}
