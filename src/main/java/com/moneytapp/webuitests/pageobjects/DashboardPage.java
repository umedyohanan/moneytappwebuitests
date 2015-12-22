package com.moneytapp.webuitests.pageobjects;

import org.openqa.selenium.By;

/**
 * Created by u.yahyoev on 22.12.2015.
 */
public class DashboardPage extends Page<DashboardPage> {

    public DashboardPage() {
        super("/dashboard");
    }

    @Override
    public boolean isDemandedPage() {
        return isElementPresent(By.xpath("//h1[.=\"Dashboard\"]"));
    }
}
