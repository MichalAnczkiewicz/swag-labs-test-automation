package com.saucedemo.core.assertions;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.function.Executable;

import java.util.Collection;
import java.util.List;

public class WebAssertions {

    public static void webAssertElementsAreCorrectlySorted(boolean areElementsSorted, String sortingMethod) {

        if (!areElementsSorted)
            throw new AssertionError(String.format(
                    "\nElements are not sorted corrected." +
                            "\nThey should be sorted by: %s", sortingMethod));
    }

    public static void webAssertStringIsNotEmpty(String value) {

        if (value == null || value.isEmpty()) {
            throw new AssertionError("\nString is empty.");
        }
    }

    public static void webAssertElementToContainText(String actualText, String expectedText) {

        if (!actualText.contains(expectedText)) {
            throw new AssertionError(String.format("\nText: %s \ndoesn't contain expected string: %s", actualText, expectedText));
        }
    }

    public static void webAssertNumberOfElementsToNotBe0(int expectedNumberOfElements) {

        if (expectedNumberOfElements == 0) {
            throw new AssertionError("\nWrong number of elements. Expected number should be more than 0");
        }
    }

    public static Executable webAssertElementToBeVisible(boolean isElementVisible, Locator element) {

        if (!isElementVisible)
            throw new AssertionError(String.format("\nElement: %s is not visible", element));
        return null;
    }

    public static void webAssertNumbersToBeTheSame(long expectedNumber, long actualNumber) {

        if (expectedNumber != actualNumber) {
            throw new AssertionError(
                    String.format(
                            "\nNumbers are different. \nExpected: %d \nOn website: %d ",
                            expectedNumber,
                            actualNumber
                    )
            );
        }
    }

    public static void webAssertStringsToBeTheSame(String expectedText, String actualText) {

        if (!actualText.equals(expectedText))
            throw new AssertionError(
                    String.format(
                            "\nString are different. \nExpected: %s \nOn website: %s ",
                            expectedText,
                            actualText
                    )
            );
    }
}
