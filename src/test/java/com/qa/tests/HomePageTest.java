
package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

public class HomePageTest extends BaseClass {

    @Test
    public void verifyHomePageTitle() {

        String actualTitle = driver.get().getTitle();

        System.out.println("Page Title: " + actualTitle);

        Assert.assertTrue(
                actualTitle.contains("Automation Exercise"),
                "Home page title verification failed"
        );
    }
}
