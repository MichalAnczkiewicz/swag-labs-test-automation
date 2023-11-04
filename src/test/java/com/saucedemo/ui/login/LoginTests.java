package com.saucedemo.ui.login;

import com.microsoft.playwright.Page;
import com.saucedemo.core.annotations.JiraIssue;
import com.saucedemo.core.browserConfig.AfterTestsConfig;
import com.saucedemo.core.browserConfig.BrowserConfig;
import com.saucedemo.core.browserConfig.TestReportsConfig;
import com.saucedemo.helpers.SauceLabsTestHelpersWeb;
import com.saucedemo.helpers.interfaces.TestHelpersWeb;
import com.saucedemo.ui.BaseTest;
import com.saucedemo.ui.loginPages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.saucedemo.core.assertions.WebAssertions.webAssertElementToBeVisible;
import static com.saucedemo.core.assertions.WebAssertions.webAssertElementToContainText;
import static com.saucedemo.helpers.ServiceUrls.LISTING_URL;
import static com.saucedemo.helpers.TestParametersFactory.SAUCE_REGRESSION;

@DisplayName("Login form tests")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(TestReportsConfig.class)
@Tag(SAUCE_REGRESSION)
public class LoginTests extends BaseTest {

    private final BrowserConfig browserConfig = new BrowserConfig(false);
    private final Page page = browserConfig.createPage();

    @RegisterExtension
    public AfterTestsConfig screenshotsConfig = new AfterTestsConfig(page, browserConfig);

    private LoginPage loginPage;
    private TestHelpersWeb testHelpersWeb;

    @BeforeEach
    public void setup() {

        testHelpersWeb = new SauceLabsTestHelpersWeb(page);
        loginPage = new LoginPage(page);
        testHelpersWeb.openWebsiteAndAcceptPP("");
    }

    @JiraIssue("JIRA_1")
    @DisplayName("Username input validation")
    @ParameterizedTest(name = "Username input validation_{0}")
    @ValueSource(strings = {"", "abcd"})
    public void usernameInputValidation(String username) {

        loginPage.loginToService(username, SERVICE_PASSWORD);

        webAssertElementToBeVisible(loginPage.isErrorVisible(), loginPage.getError());

        if ("abcd".equals(username)) {

            webAssertElementToContainText(loginPage.getErrorText(), "Username and password do not match");
        }

        if ("".equals(username)) {

            webAssertElementToContainText(loginPage.getErrorText(), "Username is required");
        }
    }

    @JiraIssue("JIRA_1")
    @DisplayName("Password input validation")
    @ParameterizedTest(name = "Password input validation_{0}")
    @ValueSource(strings = {"", "abcd"})
    public void passwordInputValidation(String password) {

        loginPage.loginToService(SERVICE_USERNAME, password);

        webAssertElementToBeVisible(loginPage.isErrorVisible(), loginPage.getError());

        if ("abcd".equals(password)) {

            webAssertElementToContainText(loginPage.getErrorText(), "Username and password do not match");
        }

        if ("".equals(password)) {

            webAssertElementToContainText(loginPage.getErrorText(), "Password is required");
        }
    }

    @JiraIssue("JIRA_1")
    @DisplayName("Successful login")
    @Test
    public void shouldBeAbleToSuccessfullyLogin() {

        loginPage.loginToService(SERVICE_USERNAME, SERVICE_PASSWORD);
        webAssertElementToContainText(page.url(), LISTING_URL);
    }
}
