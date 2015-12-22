package com.moneytapp.webuitests.pageobjects;

import com.google.common.base.Stopwatch;
import com.moneytapp.webuitests.core.Configuration;
import com.moneytapp.webuitests.core.TestSession;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.concurrent.TimeUnit;

/**
 * Created by u.yahyoev on 22.12.2015.
 */
public abstract class Page<T> {

    private static final Logger logger = LoggerFactory.getLogger(Page.class);
    protected static Configuration config = Configuration.getInstance();
    public WebDriver driver;
    public String baseUrl;
    protected String url;

    public Page(String url) {
        driver = TestSession.getWebdriver();
        config = Configuration.getInstance();
        baseUrl = config.getProperty("webdriver.base.host");
        this.url = baseUrl + url;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    /**
     * Open url and return instance of opened and initialized page.
     *
     * @return instance of opened page.
     */
    public T open() {
        if (url == null) {
            throw new IllegalStateException("Url is not specified for the page " + getPageName());
        }
        logger.info("Opening {}, url: {}", getPageName(), url);
        Stopwatch s = Stopwatch.createStarted();
        try {
            driver.get(url);
        } catch (TimeoutException e) {
            logger.warn("Timeout has reached while getting the following url {}." +
                    "\nDespite this, we proceed having in mind, " +
                    "that the page might not have been loaded properly.", url);
        }
        logger.debug("Done: Opening {}, url: {}, time: {} ms.", getPageName(), url, s.elapsed(TimeUnit.MILLISECONDS));
        Assert.assertTrue(isDemandedPage());
        //.as("check page must be: %s", this.getPageName());
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
        return (T) this;
    }

    public abstract boolean isDemandedPage();

    public String getPageName() {
        return this.getClass().getSimpleName();
    }

    public String getAssignedUrl() {
        return url;
    }

    public boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

}
