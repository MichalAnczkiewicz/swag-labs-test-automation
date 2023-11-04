package com.saucedemo.ui.listingPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.saucedemo.core.utils.PageValuesUtils;
import com.saucedemo.ui.BasePage;
import lombok.Getter;

import java.util.stream.Collectors;

import static com.saucedemo.core.utils.ListUtils.isSortedAscending;

@Getter
public class ProductPage extends BasePage {

    private final Locator productItemName;
    private final Locator productDescription;
    private final Locator addToCartButton;
    private final Locator productPrice;
    private final Locator removeFromCart;

    public ProductPage(Page page) {

        super(page);
        this.productItemName = page.locator(".inventory_item_name");
        this.productDescription = page.locator(".inventory_item_desc");
        this.addToCartButton = page.locator("[data-test*='add-to-cart']");
        this.productPrice = page.locator(".inventory_item_price");
        this.removeFromCart = page.locator("[data-test*='remove-sauce-labs']");
    }

    public void clickAddToCartButton(int index) {

        click(addToCartButton.nth(index));
    }

    public void clickProductName(int index) {

        click(productItemName.nth(index));
    }

    public String getProductItemName(int index) {

        return getTextFromElement(productItemName.nth(index));
    }

    public String getProductDescription(int index) {

        return getTextFromElement(productDescription.nth(index));
    }

    public int getNumberOfAddToCartButtons() {

        return getNumberOfElements(addToCartButton);
    }

    public String getPriceOfProduct(int index) {

        return getTextFromElement(productPrice.nth(index));
    }

    public boolean isRemoveButtonVisible() {

        return isElementVisible(removeFromCart);
    }

    public boolean areProductsSortedAscendingByPrice() {

        return isSortedAscending(
                new PageValuesUtils(page).removeCurrencyFromStringAndReturnDouble(productPrice)
                        .stream()
                        .map(Double::intValue)
                        .sorted()
                        .collect(Collectors.toList()));
    }
}
