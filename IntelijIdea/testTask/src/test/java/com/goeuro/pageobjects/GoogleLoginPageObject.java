package com.goeuro.pageobjects;

import com.goeuro.utils.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.goeuro.utils.GoEuroUtils;

/**
 * Created by mac on 6/23/15.
 */
public class GoogleLoginPageObject {
    private WebDriver web;

    /**
     * This constructor provide getting the webdriver, open the page and check page was open. If page after 5 seconds
     * will be not open, will be thrown an exception
     * @param web
     */
    public GoogleLoginPageObject(WebDriver web) {
        this.web = web;
        this.web.get(GoEuroUtils.getSpecificParam("page.gmail"));
        AbstractTest.explicityWait(loginPageLocator());
    }

    /**
     * Method provide authorization user in Google Gmail account
     * @param email
     * @param password
     * @return page object of Gmail page
     */
    public GmailPageObject doAuthorization(String email, String password) {
        web.findElement(By.id("Email")).sendKeys(email);
        web.findElement(By.id("next")).click();
        GoEuroUtils.sleep(2000);
        web.findElement(By.id("Passwd")).sendKeys(password);
        web.findElement(By.id("signIn")).click();

        return new GmailPageObject(web);
    }

    /**
     * This additional method which provide checking is it Login Page or not, if
     * specific element exists on current page, will be return - true, if not - false
     * @return boolean state
     */
    public boolean isGoogleLoginPageVisible() {
        return web.findElement(loginPageLocator()).isDisplayed();
    }

    /**
     * Specific element on Login Page
     * @return
     */
    private By loginPageLocator() { return By.id("cc_iframe_parent"); }
}