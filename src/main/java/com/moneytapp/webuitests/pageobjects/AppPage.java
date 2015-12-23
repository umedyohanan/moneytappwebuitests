package com.moneytapp.webuitests.pageobjects;

import org.openqa.selenium.By;

/**
 * Created by u.yahyoev on 22.12.2015.
 */
public class AppPage extends Page<AppPage> {

    public AppPage(String url) {
        super("/apps/" + url);
    }

    public String getAdPlaceName(String adPlaceName) {
        return driver
                .findElement(By.xpath(String.format("//table[@id='apps']/tbody//td[.=\"%s\"][1]", adPlaceName)))
                .getText();
    }

    public String getAdPlaceStatus(String adPlaceName) {
        return driver
                .findElement(By.xpath(String.format("//table[@id='apps']/tbody//td[.=\"%s\"][1]/..//button", adPlaceName)))
                .getText();
    }

    @Override
    public boolean isDemandedPage() {
        return isElementPresent(By.xpath("//p[.=\"View and edit your App's details and available AdPlaces\"]"));
    }
}
