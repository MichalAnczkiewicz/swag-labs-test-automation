package com.saucedemo.ui.navbarPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.saucedemo.ui.BasePage;

public class NavbarPage extends BasePage {

    private final Locator shoppingCart;
    private final Locator shoppingCartBadge;

    public NavbarPage(Page page) {
        super(page);
        this.shoppingCartBadge = page.locator(".shopping_cart_badge");
        this.shoppingCart = page.locator(".shopping_cart_link");
    }

    public String getShoppingCartBadge() {

        return getTextFromElement(shoppingCartBadge);
    }

    public void clickShoppingCart() {

        click(shoppingCart);
    }
}
