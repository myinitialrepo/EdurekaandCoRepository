package com.selenium.tests;

import com.selenium.util.PropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * @author apoorva
 */
public class EdurekaHomePageTest {

    private String contactNumber = PropertyUtil.getProperty("user.phone");
    private String email = PropertyUtil.getProperty("user.email");
    private String password = PropertyUtil.getProperty("user.password");

    private static final Logger LOG = LoggerFactory.getLogger(EdurekaHomePageTest.class);

    private final WebDriver driver;
    private static final String ERR_MSG = "WebDriver cannot be null.";


    public EdurekaHomePageTest(WebDriver driver){
        if(driver == null)
            throw new IllegalArgumentException(ERR_MSG);
        this.driver = driver;
    }

    private void load(){
        String urlToBeLaunched = PropertyUtil.getProperty("url.edureka.homepage");
        LOG.info("loading homepage with URL: {}", urlToBeLaunched);
        WebDriver.Options options = driver.manage();
        options.deleteAllCookies();
        WebDriver.Window window = options.window();
        window.maximize();
        driver.get(urlToBeLaunched);
        LOG.info("title of the page: {}", driver.getTitle());
    }

    public void signUpTest(){
        this.load();

        By signUpButtonLocator = By.xpath("/html/body/header/nav/ul/li[5]/span");
        WebElement signUpElement = driver.findElement(signUpButtonLocator);
        signUpElement.click();

        String windowHandle = driver.getWindowHandle();
        LOG.info("current window handle {}", windowHandle);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        By userNameLocator = By.id("sg_popup_email");
        WebElement userNameElement = driver.findElement(userNameLocator);
        userNameElement.clear();
        userNameElement.sendKeys(email);

        WebElement phoneNumberLocator = driver.findElement(By.id("sg_popup_phone_no"));
        phoneNumberLocator.sendKeys(contactNumber);

        WebElement signUPLocator = driver.findElement(By.xpath("//*[@id=\"new_sign_up_optim\"]/div/div/div[2]/div[1]/form/button"));
        signUPLocator.click();

        checkUserExistance();
        chooseCommunity();
        closeDriver();
    }

    public void checkUserExistance() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            LOG.info("handling interrupted Exception");
        }

        WebElement registeredUserMsg = driver.findElement(By.id("emailError"));
            if (registeredUserMsg.isDisplayed()) {
                LOG.info("User Already exist, please Click Log in to continue");

                WebElement logInElementForExistingUser = driver.findElement(By.xpath("//span[@class='login-vd']"));
                logInElementForExistingUser.click();

                WebElement logInemailElementForExistingUser = driver.findElement(By.id("si_popup_email"));
                logInemailElementForExistingUser.sendKeys(email);

                WebElement logInPasswordElementForExistingUser = driver.findElement(By.id("si_popup_passwd"));
                logInPasswordElementForExistingUser.sendKeys(password);

                WebElement loginButtonForExistingUser =  driver.findElement(By.xpath("//button[@class='clik_btn_log btn-block']"));
                loginButtonForExistingUser.click();

            }else {
                driver.getWindowHandle();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOG.error("interupted the execution flow ");
                }
                boolean isSelected  = driver.findElement(By.id("signup_password")).isSelected();
                if (isSelected){
                    driver.findElement(By.id("signup_password")).sendKeys(password);
                }else {
                    driver.findElement(By.id("signup_password")).click();
                    driver.findElement(By.id("signup_password")).sendKeys(password);
                }
            driver.findElement(By.xpath("//*[@id=\"new_sign_up_optim\"]/div/div/div[2]/div[2]/form/button")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

    public void chooseCommunity(){
        WebElement communityLocator = driver.findElement(By.xpath("//a[@class='dropdown-toggle ga_ecom_info trackButton']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        communityLocator.click();

    }

    public void closeDriver(){
        driver.close();
    }
}

