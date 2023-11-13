package com.theproqa;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.saucedemo.ui.BasePage;

public class FormPage extends BasePage {

    private final Locator nameInput;
    private final Locator emailInput;
    private final Locator textArea;
    private final Locator submitButton;

    public FormPage(Page page) {
        super(page);
        this.nameInput = page.locator("input[placeholder='Name*']");
        this.emailInput = page.locator("input[placeholder='Email*']");
        this.textArea = page.locator("textarea[placeholder='Message*']");
        this.submitButton = page.locator("div.ww_submit_button_content");
    }

    public void enterAndSendMessage(String name, String email, String message) {

        fill(nameInput, name);
        fill(emailInput, email);
        fill(textArea, message);
        click(submitButton);
    }
}
