package com.saucedemo.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

import static com.saucedemo.core.utils.EmailParameters.EMAIL_PASSWORD;

public class CustomTestListener implements TestExecutionListener {

    private static final Logger LOGGER = LogManager.getLogger(CustomTestListener.class);

    public void testPlanExecutionStarted(TestPlan testPlan) {

    }

    public void testPlanExecutionFinished(TestPlan testPlan) {

        if (EMAIL_PASSWORD != null) {

            try {
                LOGGER.info("Sending email with test results");

                new EmailCreator().createEmail().sendEmail();
            } catch (Exception exception) {

                LOGGER.error("Couldn't send test results via email");
            }
        } else {

            LOGGER.info("Email password not provided. Test results won't be send.");
        }
    }
}
