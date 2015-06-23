package com.goeuro.pageobjects;

import com.goeuro.utils.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.goeuro.utils.GoEuroUtils;

/**
 * Created by mac on 6/23/15.
 */
public class GmailPageObject {
    private WebDriver web;

    /**
     * This constructor provide getting the webdriver, open the page and check page was open. If page after 5 seconds
     * will be not open, will be thrown an exception
     * @param web
     */
    public GmailPageObject(WebDriver web) {
        this.web = web;
        AbstractTest.explicityWait(getGmailPageLocator());
    }

    /**
     * This method need for click by AllApps in gmail
     * @return sub-object of menu AllApps
     */
    public GoogleProductsMenu clickOnAppMenu() {
        web.findElement(By.xpath(".//a[@title=\"Google Apps\"]")).click();
        return new GoogleProductsMenu();
    }

    /**
     * This method checking if we are no Gmail Page
     * @return visibility state
     */
    public boolean isGmailPageOpened() {
        return web.findElement(getGmailPageLocator()).isDisplayed();
    }

    /**
     * This method need for logout from gmail account on gmail page
     */
    public void logout() {
        web.findElement(By.cssSelector("span.gb_k")).click();
        web.findElement(By.linkText("Sign out")).click();
        GoEuroUtils.sleep(2000);
    }


    private By getGmailPageLocator() { return By.xpath("//div[text()='COMPOSE']"); }

    /**
     * This is sub-object of GmailPage for AllGoogleProducts
     */
    public class GoogleProductsMenu {

        public GoogleProductsMenu() {
        }

        /**
         * Method need for clicking by openGoogleDriver icon and switch to new opened window of browser
         * @return google driver page object
         */
        public GoogleDriverPageObject openGoogleDriver() {
            web.findElement(By.cssSelector("a[href*=\"https://drive.google.com/\"]")).click();
            String parentHandle = web.getWindowHandle();
            String currentWindow = null;

            for (String winHandle : web.getWindowHandles()) {
                currentWindow = winHandle;
            }

            web.switchTo().window(parentHandle);
            web.close();
            web.switchTo().window(currentWindow);

            return new GoogleDriverPageObject(web);
        }
    }
}
