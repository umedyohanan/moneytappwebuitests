package com.moneytapp.webuitests.pageobjects;

import org.openqa.selenium.By;

/**
 * Created by u.yahyoev on 22.12.2015.
 */
public class NetworksPage extends Page<NetworksPage> {
    public NetworksPage() {
        super("");
    }

    @Override
    public boolean isDemandedPage() {
        return isElementPresent(By.xpath("//li[contains(text(),\"Networks\")]"));
    }
}
