package com.moneytapp.webuitests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by u.yahyoev on 22.12.2015.
 */
public class AppPage extends Page<AppPage> {

    @FindBy(xpath = "//table[@id='apps']")
    private WebElement appAdPlace;

    @FindBy(xpath = "//*[@id='apps']/tbody//td[.=\"Banner\"][1]")
    private WebElement appAddPlaceBannerName;

    @FindBy(xpath = "//*[@id='apps']/tbody//td[.=\"Fullscreen\"][1]")
    private WebElement appAddPlaceFullscreenName;

    @FindBy(xpath = "//*[@id='apps']/tbody//td[.=\"Video\"][1]")
    private WebElement appAddPlaceVideoName;

    public AppPage(String url) {
        super("/apps/" + url);
    }



    @Override
    public boolean isDemandedPage() {
        return isElementPresent(By.xpath("//p[.=\"View and edit your App's details and available AdPlaces\"]"));
    }
}
