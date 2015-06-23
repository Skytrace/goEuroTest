package com.goeuro.utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by mac on 6/23/15.
 */
public class AbstractTest {
    private static FirefoxDriver firefoxDriver;

    public static FirefoxDriver getFirefoxDriver() {
        return firefoxDriver;
    }

    public static void explicityWait(By element) {
        new WebDriverWait(firefoxDriver, 10).until(ExpectedConditions.presenceOfElementLocated(element));
    }

    @Before
    public void initFirefoxDriver() {
        firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
    }

    @After
    public void closeDriver() {
        firefoxDriver.close();
    }
}
