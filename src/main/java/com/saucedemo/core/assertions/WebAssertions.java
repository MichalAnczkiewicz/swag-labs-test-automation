package com.saucedemo.core.assertions;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.function.Executable;

import java.util.Collection;
import java.util.List;

public class WebAssertions {

    public static void webAssertElementsAreCorrectlySorted(boolean areElementsSorted, String sortingMethod) {

        if (!areElementsSorted)
            throw new AssertionError(String.format(
                    "\nElementy nie sa poprawnie posortowane." +
                            "\nPowinny byc posortowane po: %s", sortingMethod));
    }

    public static void webAssertButtonIsDisabled(boolean isEnabled, Locator button) {

        if (isEnabled)
            throw new AssertionError(String.format(
                    "\nPrzycisk: %s nie jest wylaczony" +
                            "mimo, ze powinien byc", button));
    }

    public static void webAssertButtonIsEnabled(boolean isEnabled, Locator button) {

        if (!isEnabled)
            throw new AssertionError(String.format(
                    "\nPrzycisk: %s nie jest wlaczony" +
                            "mimo, ze powinien byc", button));
    }

    public static void webAssertStringIsNotEmpty(String value) {

        if (value == null || value.isEmpty()) {
            throw new AssertionError("\nWartosc stringa jest pusta.");
        }
    }

    public static void webAssertElementToContainText(String actualText, String expectedText) {

        if (!actualText.contains(expectedText)) {
            throw new AssertionError(String.format("\nTekst: %s \nnie zawiera spodziewanego tekstu: %s", actualText, expectedText));
        }
    }

    public static void webAssertElementToNotContainText(String actualText, String unexpectedText) {

        if (actualText.contains(unexpectedText))
            throw new AssertionError(String.format("\nTekst: %s \nzawiera fraze: %s mimo, ze nie powinien", actualText, unexpectedText));
    }

    public static void webAssertIfAnyParameterIsEmpty(List<String> values) {

        boolean hasNonEmptyString = values.stream().anyMatch(value -> !value.isEmpty());
        if (!hasNonEmptyString) {
            throw new AssertionError("\nLista zawiera pusty string");
        }
    }

    public static void webAssertNumberOfElementsToNotBe0(int expectedNumberOfElements) {

        if (expectedNumberOfElements == 0) {
            throw new AssertionError("\nNie zgadza się liczba elementów. Spodziewana liczba powinna być większa od 0");
        }
    }

    public static Executable webAssertElementToBeVisible(boolean isElementVisible, Locator element) {

        if (!isElementVisible)
            throw new AssertionError(String.format("\nElement: %s nie jest widoczny", element));
        return null;
    }

    public static void webAssertElementToBeHidden(boolean isElementHidden, Locator element) {

        if (!isElementHidden)
            throw new AssertionError(String.format("\nElement: %s jest widoczny, mimo ze nie powinien", element));
    }

    public static void webAssertGreaterThan(int actual, int expected) {
        if (actual <= expected) {
            throw new AssertionError(String.format("\nSpodziewano sie liczby wiekszej od: %d, \na jest: %d", expected, actual));
        }
    }

    public static void webAssertNumbersToBeTheSame(long expectedNumber, long actualNumber) {

        if (expectedNumber != actualNumber) {
            throw new AssertionError(
                    String.format(
                            "\nLiczby roznia sie. \nSpodziewana: %d \nNa serwisie: %d ",
                            expectedNumber,
                            actualNumber
                    )
            );
        }
    }

    public static void webAssertNumbersToBeDifferent(long expectedNumber, long actualNumber) {

        if (expectedNumber == actualNumber) {
            throw new AssertionError(
                    String.format(
                            "\nLiczby sa takie same, mimo ze nie powinny. \nSpodziewane %d, jest inne niz %d ",
                            expectedNumber,
                            actualNumber
                    )
            );
        }
    }

    public static void webAssertDoublesToBeTheSame(double expectedNumber, double actualNumber) {

        if (expectedNumber != actualNumber) {
            throw new AssertionError(
                    String.format(
                            "\nLiczby roznia sie. \nSpodziewana: %f \nNa serwisie: %f ",
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
                            "\nTeksty roznia sie. \nSpodziewany: %s \nNa serwisie: %s ",
                            expectedText,
                            actualText
                    )
            );
    }

    public static void webAssertStringsToBeDifferent(String unexpectedText, String actualText) {

        if (actualText.equals(unexpectedText))
            throw new AssertionError(
                    String.format(
                            "\nTeksty sa takie same Choc powinny byc rozne. \nPierwszy tekst: %s \nDrugi tekst: %s ",
                            unexpectedText,
                            actualText
                    )
            );
    }

    public static <T> void webAssertListsEqual(List<T> expectedList, List<T> actualList) {
        if (expectedList.size() != actualList.size()) {
            throw new AssertionError(
                    String.format(
                            "\nListy sa roznych rozmiarow, choc nie powinny" +
                                    "\nPierwsza lista:  %s" +
                                    "\nDruga lista: %s", expectedList, actualList));
        }

        boolean flag = false;

        for (int i = 0; i < expectedList.size(); i++) {
            if (!expectedList.get(i).equals(actualList.get(i))) {

                flag = true;
                break;
            }
        }

        if (flag)
            throw new AssertionError(
                    String.format("\nListy sa rozne." +
                            "\nPierwsza lista:  %s" +
                            "\nDruga lista: %s", expectedList, actualList));
    }

    public static <T> void webAssertListsAreDifferent(List<T> unexpectedList, List<T> actualList) {

        int numberOfLoops = Math.min(unexpectedList.size(), actualList.size());

        for (int i = 0; i < numberOfLoops; i++) {
            if (!unexpectedList.get(i).equals(actualList.get(i))) {
                return;
            }
        }

        if (unexpectedList.size() != actualList.size()) {
            return; // Lists have different sizes, so they are not equal
        }

        throw new AssertionError(
                String.format("\nListy sa takie same, mimo ze powinny byc inne." +
                        "\nPierwsza lista:  %s" +
                        "\nDruga lista: %s", unexpectedList, actualList));
    }

    public static <T> void webAssertAllElementsContainValue(Collection<T> collection, T expectedValue) {
        for (T element : collection) {
            if (!element.equals(expectedValue)) {
                throw new AssertionError(String.format("Nie wszystkie elementy zawieraja wartosc:  %s" + expectedValue));
            }
        }
    }
}
