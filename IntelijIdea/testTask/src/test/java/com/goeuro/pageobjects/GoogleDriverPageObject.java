package com.goeuro.pageobjects;

import com.goeuro.utils.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.goeuro.utils.GoEuroUtils;

/**
 * Created by mac on 6/23/15.
 */
public class GoogleDriverPageObject {
    private WebDriver web;

    /**
     * This constructor provide getting the webdriver, open the page and check page was open. If page after 5 seconds
     * will be not open, will be thrown an exception
     * @param web
     */
    public GoogleDriverPageObject(WebDriver web) {
        this.web = web;
        this.web.get(GoEuroUtils.getSpecificParam("page.google.driver"));
        AbstractTest.explicityWait(newButtonLocator());
    }

    /**
     * This method need need for click on button 'New' on Google Driver page
     * @return object of New Menu
     */
    public NewMenu clickNewButton() {
        web.findElement(newButtonLocator()).click();
        return new NewMenu();
    }

    /**
     * This method provide checking visibility of specific element
     * @param name name of item on google driver
     * @return visibility state
     */
    public boolean isItemInGoogleDriverVisible(String name) {
        return web.findElement(By.xpath("//*[text()='" + name + "']")).isDisplayed();
    }

    /**
     * This method need for selecting specific folder
     * @param name
     */
    public void selectFolder(String name) {
        web.findElement(By.xpath("//*[text()='" + name + "']")).click();
    }

    /**
     * This method need for removing item from google driver which are already selected
     */
    public void clickRemoveSelectedItem() {
        web.findElement(By.cssSelector("[data-tooltip=\"Remove\"]")).click();
    }

    private By newButtonLocator() { return By.xpath("//div[text()='New']"); }

    /**
     * Sub-object NewMenu for Google driver page
     * contains:
     *     method click by 'Folder' button;
     *     sub-object for pop-up;
     */
    public class NewMenu {
        public NewMenu() {
            AbstractTest.explicityWait(By.xpath("//div[@role=\"menu\"][2]"));
        }

        /**
         * Method need for click by create new folder button
         * @return the object of Pop-up form for filling a new name of folder
         */
        public CreateNewNamePopUp clickCreateNewFolder() {
            web.findElement(By.xpath("//*[text()='Folder']")).click();
            return new CreateNewNamePopUp();
        }

        /**
         * Object for new folder pop-up
         */
        public class CreateNewNamePopUp {
            public CreateNewNamePopUp() {
                AbstractTest.explicityWait(By.cssSelector(".kb-r.Da-n-r"));
            }


            /**
             * Method need for filling name of new folder
             * @param name
             * @return
             */
            public CreateNewNamePopUp fillNameField(String name) {
                web.findElement(By.cssSelector(".kb-r.Da-n-r input")).sendKeys(name);
                return this;
            }

            public void clickSaveButton() {
                web.findElement(By.cssSelector(".kb-r.Da-n-r button[name='ok']")).click();
                GoEuroUtils.sleep(2000);
            }
        }
    }
}
