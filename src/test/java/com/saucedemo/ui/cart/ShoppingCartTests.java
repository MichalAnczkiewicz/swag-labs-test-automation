package com.saucedemo.ui.cart;

import com.microsoft.playwright.Page;
import com.saucedemo.core.annotations.JiraIssue;
import com.saucedemo.core.browserConfig.AfterTestsConfig;
import com.saucedemo.core.browserConfig.BrowserConfig;
import com.saucedemo.core.browserConfig.TestReportsConfig;
import com.saucedemo.helpers.SauceLabsTestHelpersWeb;
import com.saucedemo.helpers.interfaces.TestHelpersWeb;
import com.saucedemo.ui.BaseTest;
import com.saucedemo.ui.cartPages.CartPage;
import com.saucedemo.ui.listingPages.ListingPage;
import com.saucedemo.ui.listingPages.ProductPage;
import com.saucedemo.ui.navbarPages.NavbarPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.saucedemo.core.assertions.WebAssertions.*;
import static com.saucedemo.helpers.ServiceUrls.LISTING_URL;
import static com.saucedemo.helpers.TestParametersFactory.SAUCE_REGRESSION;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Shopping cart tests")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(TestReportsConfig.class)
@Tag(SAUCE_REGRESSION)
public class ShoppingCartTests extends BaseTest {

    private final BrowserConfig browserConfig = new BrowserConfig(true);
    private final Page page = browserConfig.createPage();

    @RegisterExtension
    public AfterTestsConfig screenshotsConfig = new AfterTestsConfig(page, browserConfig);

    private ListingPage listingPage;
    private ProductPage productPage;
    private NavbarPage navbarPage;
    private CartPage cartPage;
    private TestHelpersWeb testHelpersWeb;

    @BeforeEach
    public void setup() {

        testHelpersWeb = new SauceLabsTestHelpersWeb(page);
        listingPage = new ListingPage(page);
        productPage = new ProductPage(page);
        navbarPage = new NavbarPage(page);
        cartPage = new CartPage(page);
        testHelpersWeb.openWebsite(LISTING_URL);
    }

    @JiraIssue("JIRA-5")
    @DisplayName("Shopping cart badge should update if product added to cart")
    @Test
    public void shoppingCartBadgeShouldUpdateIfAddedProduct() {

        int numberOfClicks = testHelpersWeb.initIndex(3);
        for (int i = 0; i < numberOfClicks; i++) {

            productPage.clickAddToCartButton(i);
        }

        webAssertNumbersToBeTheSame(numberOfClicks, Integer.parseInt(navbarPage.getShoppingCartBadge()));
    }

    @JiraIssue("JIRA-5")
    @DisplayName("Button should change to remove if product added to cart")
    @Test
    public void buttonShouldChangeIfProductAddedToCart() {

        productPage.clickAddToCartButton(1);

        webAssertElementToBeVisible(productPage.isRemoveButtonVisible(), productPage.getRemoveFromCart());
    }

    @JiraIssue("JIRA-7")
    @DisplayName("Info about product should be the same on listing and cart")
    @Test
    public void infoAboutProductShouldBeTheSameOnCartAsOnListing() {

        int index = testHelpersWeb.initIndex(3);

        String nameOnListing = productPage.getProductItemName(index);
        String descriptionOnListing = productPage.getProductDescription(index);
        String priceOnListing = productPage.getPriceOfProduct(index);

        productPage.clickAddToCartButton(index);
        navbarPage.clickShoppingCart();

        assertAll(
                () -> webAssertStringsToBeTheSame(nameOnListing, cartPage.getCartItemName()),
                () -> webAssertStringsToBeTheSame(descriptionOnListing, cartPage.getCartItemDescription()),
                () -> webAssertStringsToBeTheSame(priceOnListing, cartPage.getCartItemPrice())
        );
    }
}
