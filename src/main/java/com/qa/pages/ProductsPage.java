
package com.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

	 	private WebDriver driver;
	    private WebDriverWait wait;
	    private JavascriptExecutor js;
	    private Actions act;

    // Search input field
    @FindBy(id = "search_product")
    private WebElement searchInput;

    // Search button
    @FindBy(id = "submit_search")
    private WebElement searchButton;

    // Product names displayed on the page
    @FindBy(css = ".productinfo p")
    private List<WebElement> productNames;

    // Constructor
    public ProductsPage(WebDriver driver) {
    	 this.driver = driver;
         this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         this.js = (JavascriptExecutor)driver;
         this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // Search for a product
    public void searchProduct(String productName) {

        wait.until(d -> searchInput.isDisplayed());

        searchInput.clear();
        searchInput.sendKeys(productName);

        searchButton.click();
    }

    // Verify whether the searched product is displayed
    public boolean isProductDisplayed(String expectedProduct) {

        return wait.until(d ->
            productNames.stream().anyMatch(product ->
                product.isDisplayed()
                && product.getText().trim().equalsIgnoreCase(expectedProduct)
            )
        );
    }
}
