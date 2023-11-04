package com.saucedemo.core.browserConfig;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.saucedemo.core.browserConfig.BrowsersTypes.*;
import static com.saucedemo.core.browserConfig.ViewModeConfig.setBrowserViewport;

public class BrowserConfig {

    private static final Logger LOGGER
            = LogManager.getLogger(BrowserConfig.class);
    private static final String CHOSEN_BROWSER_MESSAGE = "" +
            "Choosen browser: %s, version %s";
    private static final String BROWSER_PROPERTY = System.getProperty("BROWSER");
    private static final String VIEW_MODE_PROPERTY = System.getProperty("VIEW_MODE");
    private final int[] browserViewPort = setBrowserViewport();
    private Playwright playwright;

    private BrowserContext browserContext;
    private final boolean loginWithCookie;

    public BrowserConfig(boolean loginWithCookie) {

        this.loginWithCookie = loginWithCookie;
    }

    public Page createPage() {

        browserContext = createBrowserContext();
        return browserContext.newPage();
    }

    public void closeBrowser() {

        playwright.close();
    }

    private BrowserContext createBrowserContext() {

        playwright = Playwright.create();

        Browser browser = determineBrowser();
        Browser.NewContextOptions newContextOptions = createNewContextOptions();

        browserContext = browser.newContext(newContextOptions);
        browserContext.addInitScript("delete window.navigator.serviceWorker");

        if (this.loginWithCookie)
            browserContext.addCookies(createCookies());
        return browserContext;
    }

    private Browser.NewContextOptions createNewContextOptions() {

        Browser.NewContextOptions newContextOptions = new Browser.NewContextOptions();

        newContextOptions
                .setIgnoreHTTPSErrors(true)
                .setViewportSize(getViewportWidth(), getViewportHeight());

        if (!"FIREFOX".equals(BROWSER_PROPERTY) && "MOBILE".equals(VIEW_MODE_PROPERTY)) {

            newContextOptions
                    .setUserAgent(
                            "Automated Tests Mozilla/5.0 (iPhone; CPU iPhone OS 14_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.1 Mobile/15E148 Safari/604.1");

            newContextOptions.setIsMobile(true);
            newContextOptions.setHasTouch(true);
        }
        return newContextOptions;
    }

    private int getViewportWidth() {

        return browserViewPort[0];
    }

    private int getViewportHeight() {

        return browserViewPort[1];
    }

    private Browser determineBrowser() {

        Browser browser;

        if (BROWSER_PROPERTY == null) {

            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            LOGGER.info(String.format(CHOSEN_BROWSER_MESSAGE, CHROME, browser.version()));
            return browser;
        } else {

            switch (BROWSER_PROPERTY) {

                case "FIREFOX": {

                    browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                            .setHeadless(true));
                    LOGGER.info(String.format(CHOSEN_BROWSER_MESSAGE, FIREFOX, browser.version()));
                    return browser;
                }

                case "WEBKIT": {

                    browser = playwright.webkit().launch(new BrowserType.LaunchOptions()
                            .setHeadless(true));
                    LOGGER.info(String.format(CHOSEN_BROWSER_MESSAGE, WEBKIT, browser.version()));
                    return browser;
                }

                default: {

                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                            .setHeadless(true));
                    LOGGER.info(String.format(CHOSEN_BROWSER_MESSAGE, CHROME, browser.version()));
                    return browser;
                }
            }
        }
    }

    private List<Cookie> createCookies() {
        List<Cookie> cookies = new ArrayList<>();

        Cookie cookie = new Cookie("session-username", "standard_user");
        cookie.setPath("/");
        cookie.setDomain("www.saucedemo.com");
        cookies.add(cookie);
        return cookies;
    }
}

