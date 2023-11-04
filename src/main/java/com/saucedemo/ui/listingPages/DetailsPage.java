package com.saucedemo.ui.listingPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.saucedemo.ui.BasePage;

public class DetailsPage extends BasePage {

    private final Locator productDetailsName;
    private final Locator productDetailsDescription;
    private final Locator productDetailsPrice;

    public DetailsPage(Page page) {
        super(page);
        this.productDetailsName = page.locator(".inventory_details_name");
        this.productDetailsDescription = page.locator(".inventory_details_desc");
        this.productDetailsPrice = page.locator(".inventory_details_price");
    }

    public String getProductDetailsName() {

        return getTextFromElement(productDetailsName);
    }

    public String getProductDetailsDescription() {

        return getTextFromElement(productDetailsDescription);
    }

    public String getProductDetailsPrice() {

        return getTextFromElement(productDetailsPrice);
    }
}
