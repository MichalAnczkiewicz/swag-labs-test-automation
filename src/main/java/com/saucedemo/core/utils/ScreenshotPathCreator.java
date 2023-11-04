package com.saucedemo.core.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.sql.Timestamp;

public class ScreenshotPathCreator {

    private static final String FILE_NAME = new Timestamp(System.currentTimeMillis())
            .toString()
            .replace(" ", "")
            .replace(":", "")
            .concat("" + RandomStringUtils.randomAlphabetic(10))
            .replace(".", "");

    public static String createPathToFileWithFailedTestScreenshots(String name) {

        String directoryName = "target/screenshots/"
                .concat("sauce")
                .replace(" ", "_");

        File directory = new File(directoryName);

        if (!directory.exists()) {
            directory.mkdir();
        }

        return directoryName
                .concat("/")
                .concat(name.replaceAll("[^\\\\w.-]\", \"_\"", ""))
                .concat("/")
                .concat(FILE_NAME)
                .concat(".png");
    }
}
