package com.selenium;

import com.selenium.constants.BrowserType;
import com.selenium.exceptions.IllegalValueException;
import com.selenium.tests.EdurekaHomePageTest;
import com.selenium.util.PropertyUtil;
import com.selenium.util.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdurekaExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(EdurekaExecutor.class);


    public WebDriver initialize(){
        String browserTypeKey = "webdriver.browser.type";
        String configuredBrowserType = PropertyUtil.getProperty(browserTypeKey);
        if(configuredBrowserType == null || configuredBrowserType.isEmpty() || configuredBrowserType.trim().isEmpty())
            throw new IllegalValueException("no browser type is configured with the key: " + browserTypeKey);
        LOG.debug("driver type is configured  as " + configuredBrowserType);
        BrowserType browserType = WebDriverUtil.parseBrowserType(configuredBrowserType);
        return initialize(browserType);

    }

    private WebDriver initialize(BrowserType type){
        String browserLookUpKey = WebDriverUtil.getLookUpKey(type);
        System.setProperty(browserLookUpKey, PropertyUtil.getProperty("webdriver.browser.executable.path"));
        switch (type){
            case CHROME:
                return  new ChromeDriver();
            case FIRE_FOX:
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("BrowserType: " + type + " is not configured yet for the execution.");
        }
    }

    public static void main(String[] args) {

        EdurekaExecutor automationExampleExecutor = new EdurekaExecutor();
        WebDriver driver = automationExampleExecutor.initialize();
        EdurekaHomePageTest auto = new EdurekaHomePageTest(driver);
        auto.signUpTest();
    }
}
