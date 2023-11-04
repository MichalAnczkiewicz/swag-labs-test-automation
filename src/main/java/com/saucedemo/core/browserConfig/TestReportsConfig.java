package com.saucedemo.core.browserConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.Timestamp;

public class TestReportsConfig implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/testResults.html");
    private static final ExtentReports extentReports = new ExtentReports();
    private static ExtentTest extentTest;

    @Override
    public void beforeAll(ExtensionContext context) {

        extentSparkReporter.config(
                ExtentSparkReporterConfig.builder()
                        .theme(Theme.DARK)
                        .documentTitle("Tests_" +
                                new Timestamp(System.currentTimeMillis()))
                        .build());
        extentReports.attachReporter(extentSparkReporter);
        context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put("my_report", new CustomAfterSuite());
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {

        extentTest = extentReports.createTest(context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {

        if (!context.getExecutionException().isPresent()) {
            extentTest.pass(context.getDisplayName() + " - passed");
        } else {
            extentTest.fail(context.getExecutionException().get().getLocalizedMessage() + " - failed ");
        }
    }

    private static class CustomAfterSuite implements ExtensionContext.Store.CloseableResource {
        @Override
        public void close() {
            extentReports.flush();
        }
    }
}
