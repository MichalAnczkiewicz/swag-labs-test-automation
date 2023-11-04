package com.saucedemo.core.utils;

import java.sql.Timestamp;

import static com.saucedemo.core.utils.EmailParameters.RECEIVER;
import static com.saucedemo.core.utils.EmailParameters.SENDER;

public class EmailCreator {

    private static final String CURRENT_DATETIME = new Timestamp(System.currentTimeMillis()).toString();
    private static final String BROWSER = System.getProperty("BROWSER") != null ? System.getProperty("BROWSER") : "CHROME";
    private static final String VIEW_MODE = System.getProperty("VIEW_MODE") != null ? System.getProperty("VIEW_MODE") : "Desktop";

    public EmailSender createEmail() {

        return new EmailSender(
                SENDER,
                RECEIVER,
                "Tests results from: " + CURRENT_DATETIME,
                "In attachment there is a report from running tests on: " + CURRENT_DATETIME +
                        "\nBrowser: " + BROWSER +
                        "\nView mode: " + VIEW_MODE,
                true,
                "target/testResults.html");
    }
}
