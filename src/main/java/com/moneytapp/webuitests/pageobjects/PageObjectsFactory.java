package com.moneytapp.webuitests.pageobjects;

import com.moneytapp.webuitests.core.TestSession;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * Created by u.yahyoev on 22.12.2015.
 */
public class PageObjectsFactory {
    /**
     * Generating current page instance.
     *
     * @return page instance
     */
    public static Page getCurrentPage() {
        WebDriver driver = TestSession.getWebdriver();

        String url = driver.getCurrentUrl();

        Page page = null;

        if (url.contains("/login")) {
            page = new LoginPage();
        } else if (url.contains("/dashboard")) {
            page = new DashboardPage();
        } else if (url.contains("/apps/list")) {
            page = new AppsPage();
        } else {
            page = new DashboardPage();
        }

        PageFactory.initElements(new HtmlElementDecorator(driver), page);
        return page;
    }

    public static <T extends Page> Page<T> getInstanceOfPage(Class<T> clazz) {
        WebDriver driver = TestSession.getWebdriver();
        Page page = null;
        try {
            page = (T) clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot initialize instance of class", e);
        }

        PageFactory.initElements(new HtmlElementDecorator(driver), page);
        return page;
    }

}
