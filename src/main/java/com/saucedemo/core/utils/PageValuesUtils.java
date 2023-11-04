package com.saucedemo.core.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.saucedemo.ui.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class PageValuesUtils extends BasePage {

    public PageValuesUtils(Page page) {
        super(page);
    }

    public List<Double> removeCurrencyFromStringAndReturnDouble(Locator locator) {

        return getTextFromAllElements(locator).stream()
                .map(s -> s.replace("$", "")
                        .trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
