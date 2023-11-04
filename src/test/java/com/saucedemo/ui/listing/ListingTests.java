package com.saucedemo.ui.listing;

import com.microsoft.playwright.Page;
import com.saucedemo.core.annotations.JiraIssue;
import com.saucedemo.core.browserConfig.AfterTestsConfig;
import com.saucedemo.core.browserConfig.BrowserConfig;
import com.saucedemo.core.browserConfig.TestReportsConfig;
import com.saucedemo.helpers.SauceLabsTestHelpersWeb;
import com.saucedemo.helpers.interfaces.TestHelpersWeb;
import com.saucedemo.ui.BaseTest;
import com.saucedemo.ui.listingPages.DetailsPage;
import com.saucedemo.ui.listingPages.ListingPage;
import com.saucedemo.ui.listingPages.ProductPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.stream.IntStream;

import static com.saucedemo.core.assertions.WebAssertions.*;
import static com.saucedemo.helpers.ServiceUrls.LISTING_URL;
import static com.saucedemo.helpers.TestParametersFactory.SAUCE_REGRESSION;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Listing tests")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(TestReportsConfig.class)
@Tag(SAUCE_REGRESSION)
public class ListingTests extends BaseTest {

    private final BrowserConfig browserConfig = new BrowserConfig(true);
    private final Page page = browserConfig.createPage();

    @RegisterExtension
    public AfterTestsConfig screenshotsConfig = new AfterTestsConfig(page, browserConfig);

    private ListingPage listingPage;
    private ProductPage productPage;
    private DetailsPage detailsPage;
    private TestHelpersWeb testHelpersWeb;

    @BeforeEach
    public void setup() {

        testHelpersWeb = new SauceLabsTestHelpersWeb(page);
        listingPage = new ListingPage(page);
        productPage = new ProductPage(page);
        detailsPage = new DetailsPage(page);
        testHelpersWeb.openWebsiteAndAcceptPP(LISTING_URL);
    }

    @JiraIssue("JIRA-2")
    @DisplayName("Listing should contain products")
    @Test
    public void listingShouldHaveProducts() {

        webAssertNumberOfElementsToNotBe0(listingPage.getNumberOfInventoryItems());
    }

    @JiraIssue("JIRA-3")
    @DisplayName("Number of CTA buttons should equal to number of products")
    @Test
    public void numberOfCtaButtonsShouldBeTheSameAsNumberOfProducts() {

        webAssertNumbersToBeTheSame(listingPage.getNumberOfInventoryItems(), productPage.getNumberOfAddToCartButtons());
    }

    @JiraIssue("JIRA-3")
    @DisplayName("All products should have price visible")
    @Test
    public void everyProductShouldHavePriceVisible() {

        IntStream.range(0, listingPage.getNumberOfInventoryItems()).forEach(
                index -> webAssertStringIsNotEmpty(productPage.getPriceOfProduct(index)));
    }

    @JiraIssue("JIRA-4")
    @DisplayName("Info about product should be the same on listing and details")
    @Test
    public void infoAboutProductShouldBeTheSameOnDetailsAsOnListing() {

        int index = testHelpersWeb.initIndex(listingPage.getNumberOfInventoryItems());

        String nameOnListing = productPage.getProductItemName(index);
        String descriptionOnListing = productPage.getProductDescription(index);
        String priceOnListing = productPage.getPriceOfProduct(index);

        productPage.clickProductName(index);

        assertAll(
                () -> webAssertStringsToBeTheSame(nameOnListing, detailsPage.getProductDetailsName()),
                () -> webAssertStringsToBeTheSame(descriptionOnListing, detailsPage.getProductDetailsDescription()),
                () -> webAssertStringsToBeTheSame(priceOnListing, detailsPage.getProductDetailsPrice())
        );
    }

    @JiraIssue("JIRA-6")
    @DisplayName("Products should correctly sort by price low to high")
    @Test
    public void productsShouldCorrectlySortByPriceLowToHigh() {

        listingPage.sortProductsByPriceLowToHigh();

        webAssertElementsAreCorrectlySorted(
                productPage.areProductsSortedAscendingByPrice(),
                "Price low to high"
        );
    }
}
