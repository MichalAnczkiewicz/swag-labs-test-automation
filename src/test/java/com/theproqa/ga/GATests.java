package com.theproqa.ga;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.TimeoutError;
import com.saucedemo.core.browserConfig.AfterTestsConfig;
import com.saucedemo.core.browserConfig.BrowserConfig;
import com.saucedemo.core.browserConfig.TestReportsConfig;
import com.saucedemo.helpers.interfaces.TestHelpersWeb;
import com.theproqa.FormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Google analytics tests")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(TestReportsConfig.class)
public class GATests {

    private final BrowserConfig browserConfig = new BrowserConfig(false);
    private final Page page = browserConfig.createPage();

    @RegisterExtension
    public AfterTestsConfig screenshotsConfig = new AfterTestsConfig(page, browserConfig);

    @DisplayName("GA event should be triggered upon sending form")
    @Test
    public void gaEventShouldBeTriggeredAfterSendingForm() {

        TestHelpersWeb testHelpersWeb = new TheProQaTestHelpersWeb(page);
        testHelpersWeb.openWebsite("/contact");
        FormPage formPage = new FormPage(page);

        formPage.enterAndSendMessage("test", "test@test.com", "test");

        String eventName = "en=Send_Form_Contact";

        String response = getGARequestUrl(eventName);
        assertTrue(response.contains(eventName), "Expected event name not found in GA request. Response URL: " + response);
    }

    private String getGARequestUrl(String eventName) {

        try {
            Response resp = page.waitForResponse(
                    response ->
                            (response.status() == 200 || response.status() == 204)
                                    && response.url().contains(eventName), () -> {

                    });

            return resp.url();
        } catch (TimeoutError error) {

            fail("Timeout waiting for GA request: " + error.getMessage());
        }
        return "";
    }
}
