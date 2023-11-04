package com.saucedemo.core.browserConfig;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.saucedemo.core.annotations.JiraIssue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Optional;

import static com.saucedemo.core.utils.ScreenshotPathCreator.createPathToFileWithFailedTestScreenshots;

public class AfterTestsConfig implements TestWatcher, BeforeTestExecutionCallback {

    private static final Logger LOGGER = LogManager
            .getLogger(AfterTestsConfig.class);
    private static final String QUIT = "Playwright will be closed.";

    private final Page page;
    private final BrowserConfig browserConfig;
    private long startTime;

    public AfterTestsConfig(Page page, BrowserConfig browserConfig) {

        this.page = page;
        this.browserConfig = browserConfig;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {

        LOGGER.warn("Test: {} -> disabled: {}",
                context.getDisplayName().toUpperCase(),
                reason.orElse("without a reason")
        );

        browserConfig.closeBrowser();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {

        reportExecutionTime(context.getDisplayName().toUpperCase(), System.currentTimeMillis() - startTime);
        LOGGER.info("Test: {} -> finished with success.", context.getDisplayName().toUpperCase());
        LOGGER.info(QUIT);

        browserConfig.closeBrowser();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {

        LOGGER.warn("Test: {} ->  aborted: {}",
                context.getDisplayName().toUpperCase(),
                cause.getMessage()
        );
        LOGGER.warn(QUIT);

        browserConfig.closeBrowser();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {

        reportExecutionTime(context.getDisplayName().toUpperCase(), System.currentTimeMillis() - startTime);

        if (!processJiraIssueAnnotation(context).equals(""))
            LOGGER.error("Test: {} based on jira issue {} -> failed: {}",
                    context.getDisplayName().toUpperCase(),
                    processJiraIssueAnnotation(context),
                    cause.getMessage()
            );
        else
            LOGGER.error("Test: {} -> failed: {}",
                    context.getDisplayName().toUpperCase(),
                    cause.getMessage()
            );

        LOGGER.info("Saving screenshot to -> {}",
                createPathToFileWithFailedTestScreenshots(context.getDisplayName())
        );

        createScreenshot(context);

        browserConfig.closeBrowser();
    }

    private void reportExecutionTime(String testName, long executionTimeMillis) {
        double executionTimeSeconds = executionTimeMillis / 1000.0;
        String formattedTime = String.format("%.2f", executionTimeSeconds);
        LOGGER.info("Test " + testName.toUpperCase() + " execution time: " + formattedTime + " sek");
    }

    private String processJiraIssueAnnotation(ExtensionContext context) {
        Optional<Method> testMethod = context.getTestMethod();
        if (testMethod.isPresent()) {
            JiraIssue jiraIssue = testMethod.get().getAnnotation(JiraIssue.class);
            if (jiraIssue != null) {
                return jiraIssue.value();
            }
        }
        return "";
    }

    private void createScreenshot(ExtensionContext context) {

        try {

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(createPathToFileWithFailedTestScreenshots(
                            context.getDisplayName())))
                    .setFullPage(true));
        } catch (PlaywrightException exception) {

            LOGGER.info("There was a problem with making screenshot.");
            LOGGER.error(QUIT);
            exception.printStackTrace();
            browserConfig.closeBrowser();
        }
    }
}