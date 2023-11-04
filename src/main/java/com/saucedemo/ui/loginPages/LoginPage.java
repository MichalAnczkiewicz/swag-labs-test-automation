package com.saucedemo.ui.loginPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.saucedemo.ui.BasePage;
import lombok.Getter;

@Getter
public class LoginPage extends BasePage {

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator error;

    public LoginPage(Page page) {
        super(page);
        this.usernameInput = page.locator("[data-test='username']");
        this.passwordInput = page.locator("[data-test='password']");
        this.loginButton = page.locator("[data-test='login-button']");
        this.error = page.locator("[data-test='error']");
    }

    public void loginToService(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void enterUsername(String username) {

        fill(usernameInput, username);
    }

    public void enterPassword(String password) {

        fill(passwordInput, password);
    }

    public void clickLoginButton() {

        click(loginButton);
    }

    public boolean isErrorVisible() {

        return isElementVisible(error);
    }

    public String getErrorText() {

        return getTextFromElement(error);
    }
}
