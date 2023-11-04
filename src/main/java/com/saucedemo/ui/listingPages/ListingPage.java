package com.saucedemo.ui.listingPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.saucedemo.core.utils.PageValuesUtils;
import com.saucedemo.ui.BasePage;

import java.util.stream.Collectors;

import static com.saucedemo.core.utils.ListUtils.isSortedAscending;

public class ListingPage extends BasePage {

    private final Locator inventoryItem;
    private final Locator productSortContainer;
    private final Locator sortLowToHighOption;

    public ListingPage(Page page) {
        super(page);
        this.inventoryItem = page.locator(".inventory_item");
        this.productSortContainer = page.locator("select[data-test='product_sort_container']");
        this.sortLowToHighOption = page.locator("option[value='lohi']");
    }

    public int getNumberOfInventoryItems() {

        return getNumberOfElements(inventoryItem);
    }

    public void sortProductsByPriceLowToHigh(){

        click(productSortContainer);
        this.productSortContainer.selectOption("lohi");
    }
}
