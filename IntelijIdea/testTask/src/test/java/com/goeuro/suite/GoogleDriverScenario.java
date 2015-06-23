package com.goeuro.suite;

import com.goeuro.utils.AbstractTest;
import junit.framework.Assert;
import org.junit.Test;
import com.goeuro.pageobjects.GoogleDriverPageObject;
import com.goeuro.pageobjects.GoogleLoginPageObject;
import com.goeuro.utils.GoEuroUtils;

/**
 * Created by mac on 6/23/15.
 */
public class GoogleDriverScenario extends AbstractTest {
    private GoogleLoginPageObject loginPage = null;
    private GoogleDriverPageObject driverPageObject = null;

    @Test
    public void checkLoginInGmail() {
        loginPage = new GoogleLoginPageObject(getFirefoxDriver());
        boolean actualResult = loginPage
                .doAuthorization(GoEuroUtils.getSpecificParam("user.email"), GoEuroUtils.getSpecificParam("user.password"))
                .isGmailPageOpened();

        Assert.assertTrue("It's not a gmail page", actualResult);
    }

    @Test
    public void openGoogleDriver() {
        String testFolder = "folder1";
        loginPage = new GoogleLoginPageObject(getFirefoxDriver());
        loginPage
                .doAuthorization(GoEuroUtils.getSpecificParam("user.email"), GoEuroUtils.getSpecificParam("user.password"))
                .clickOnAppMenu()
                .openGoogleDriver()
                .clickNewButton()
                .clickCreateNewFolder()
                .fillNameField(testFolder)
                .clickSaveButton();

        driverPageObject = new GoogleDriverPageObject(getFirefoxDriver());
        boolean actualResult = driverPageObject.isItemInGoogleDriverVisible(testFolder);
        Assert.assertTrue("Folder " + testFolder + "are not visible", actualResult);

        driverPageObject.selectFolder(testFolder);
        driverPageObject.clickRemoveSelectedItem();
    }

    @Test
    public void checkUnloginFromGmail() {
        loginPage = new GoogleLoginPageObject(getFirefoxDriver());
        loginPage
                .doAuthorization(GoEuroUtils.getSpecificParam("user.email"), GoEuroUtils.getSpecificParam("user.password"))
                .logout();
        boolean actualResult = loginPage.isGoogleLoginPageVisible();
        Assert.assertTrue("It's not a gmail login page", actualResult);
    }

}
