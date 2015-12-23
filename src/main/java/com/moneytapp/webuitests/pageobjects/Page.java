package com.moneytapp.webuitests.pageobjects;

import com.google.common.base.Stopwatch;
import com.moneytapp.webuitests.core.Configuration;
import com.moneytapp.webuitests.core.TestSession;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    public static boolean waitForPageFullyLoaded(int timeoutMs) {
        int previousElementsCount;
        int currentElementsCount = 0;
        int interval = 300;
        long start = System.currentTimeMillis();
        String result;

        boolean waitMore;
        boolean timeout;

        do {
            previousElementsCount = currentElementsCount;
            try {
                Thread.sleep(interval);
                logger.debug("waitForPageFullyLoaded, scanning for nodes...");
                currentElementsCount = TestSession.getWebdriver().findElements(By.cssSelector("*")).size();
                logger.debug("waitForPageFullyLoaded, current nodes count: {}, previous nodes count {};",
                        currentElementsCount, previousElementsCount);
                result = (String) ((JavascriptExecutor) TestSession.getWebdriver())
                        .executeScript("return document.readyState");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean elementsCountChanged = currentElementsCount > previousElementsCount;

            boolean domReady = result.equals("complete");

            timeout = System.currentTimeMillis() - start > timeoutMs;

            // We need to wait more if:
            //      waiting time is less then timeout argument
            //      AND
            //           elements count has changed since last try
            //      OR
            //           DOM is not yet ready

            logger.debug("Timeout: {}, elemetsCountChanged: {}, domReady: {}", timeout, elementsCountChanged, domReady);
            waitMore = !timeout && (elementsCountChanged || !domReady);
        } while (waitMore);

        return timeout;
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
        waitForPageFullyLoaded(5000);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
        Assert.assertTrue(isDemandedPage());
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
