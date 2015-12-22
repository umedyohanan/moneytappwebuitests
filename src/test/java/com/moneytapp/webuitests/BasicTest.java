package com.moneytapp.webuitests;

import com.moneytapp.webuitests.core.Configuration;
import com.moneytapp.webuitests.core.TestSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

/**
 * Created by u.yahyoev on 21.12.2015.
 */
public abstract class BasicTest {
    private static final Logger logger = LoggerFactory.getLogger(BasicTest.class);

    protected static Configuration configuration = Configuration.getInstance();

    protected WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        driver = initializeWebDriver();
        TestSession.setWebdriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

    protected WebDriver initializeWebDriver() {
        String browserName = configuration.getProperty("browser.name");
        if (browserName.equals("firefox")) {
            return new FirefoxDriver();
        } else if (browserName.equals("chrome")) {
            return new ChromeDriver();
        } else if (browserName.equals("internet explorer")) {
            return new InternetExplorerDriver();
        } else {
            throw new RuntimeException(
                    String.format("You have tried to set unsupported browser: %s.", browserName));
        }
    }

    @DataProvider(name = "appname")
    public Object[][] newAppName() {
        return new Object[][]{{"testapp" + RandomStringUtils.randomAlphanumeric(5)}};
    }


}
