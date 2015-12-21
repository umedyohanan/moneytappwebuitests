package com.moneytapp.webuitests.core;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by u.yahyoev on 21.12.2015.
 */
public class TestSession {

    public static final String WEBDRIVER = "webdriver";

    private static ThreadLocal<Map<String, Object>> context = new InheritableThreadLocal();

    private static Map<String, Object> getContext() {
        Map<String, Object> res = context.get();
        if (res == null) {
            res = new HashMap();
            context.set(res);
        }
        return res;
    }

    /**
     * @return the webdriver object bound to the current test.
     */
    public static WebDriver getWebdriver() {
        return (WebDriver) getContext().get(WEBDRIVER);
    }

    public static void setWebdriver(WebDriver driver) {
        getContext().put(WEBDRIVER, driver);
    }
}
