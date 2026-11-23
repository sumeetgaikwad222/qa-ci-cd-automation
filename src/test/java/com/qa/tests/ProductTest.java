
package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.pages.HomePage;
import com.qa.pages.ProductsPage;
import com.qa.testdata.SearchDataProvider;

public class ProductTest extends BaseClass {

    @Test(dataProvider = "searchProducts" , dataProviderClass = SearchDataProvider.class)
    public void verifyProductSearchWithValidProductName(String productName) {

        // Step 1: Initialize HomePage
        HomePage homePage = new HomePage(driver.get());

        // Step 2: Navigate to Products page
        homePage.clickProducts();

        // Step 3: Initialize ProductsPage
        ProductsPage productsPage = new ProductsPage(driver.get());

        // Step 4: Search for product

        productsPage.searchProduct(productName);

        // Step 5: Verify search result
        boolean actualResult = productsPage.isProductDisplayed(productName);

        // Step 6: TestNG assertion
        Assert.assertTrue(
            actualResult,
            "Expected product was not displayed: " + productName
        );

        System.out.println("PASS: Product search successful for " + productName);
    }
}
