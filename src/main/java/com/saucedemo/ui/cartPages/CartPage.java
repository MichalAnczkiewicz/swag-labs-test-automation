package com.saucedemo.ui.cartPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.saucedemo.ui.BasePage;

public class CartPage extends BasePage {

    private final Locator cartItemName;
    private final Locator cartItemDesc;
    private final Locator cartItemPrice;

    public CartPage(Page page) {

        super(page);
        this.cartItemName = page.locator(".inventory_item_name");
        this.cartItemDesc = page.locator(".inventory_item_desc");
        this.cartItemPrice = page.locator(".inventory_item_price");
    }

    public String getCartItemName() {

        return getTextFromElement(cartItemName);
    }

    public String getCartItemDescription() {

        return getTextFromElement(cartItemDesc);
    }

    public String getCartItemPrice() {

        return getTextFromElement(cartItemPrice);
    }
}
