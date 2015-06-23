package com.goeuro.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by mac on 6/23/15.
 */
public class GoEuroUtils {

    /**
     * Method which need for getting test data from testData.properties which are places in resources folder.
     * @param proprtyName
     * @return value of property
     */
    public static String getSpecificParam(String proprtyName) {
        Properties prop = new Properties();
        InputStream input;
        final String pathToPropsFile = System.getProperty("user.dir") + "/src/main/resources/testData.properties";

        try {
            input = new FileInputStream(pathToPropsFile);
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop.getProperty(proprtyName);
    }


    /**
     * Method need for set sleep for currenct thread
     * @param millis
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }

}
