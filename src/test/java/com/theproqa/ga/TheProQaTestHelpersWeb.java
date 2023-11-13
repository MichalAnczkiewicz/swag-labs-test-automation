package com.theproqa.ga;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.saucedemo.helpers.interfaces.TestHelpersWeb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class TheProQaTestHelpersWeb implements TestHelpersWeb {

    private static final Logger LOGGER
            = LogManager.getLogger(TheProQaTestHelpersWeb.class);
    private final Page page;

    public TheProQaTestHelpersWeb(Page page) {

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
                        () -> page.navigate("https://yourservice.com".concat(url)));
                success = true;
                break;
            } catch (TimeoutError e) {

                LOGGER.error(String.format("Page: %s didn't open successfully %d times. ", url, retryCount + 1));
            }
        }

        if (!success) {
            Assertions.fail(String.format("Page %s didn't respond %d times. Testing aborted", url, maxRetries));
        }
    }
}
