package com.moneytapp.webuitests.pageobjects;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by u.yahyoev on 22.12.2015.
 */
public class AppsPage extends Page<AppsPage> {

    @FindBy(xpath = "//table[@id='apps']")
    private WebElement tableApps;

    @FindBy(xpath = "//a[@class=\"btn btn-success pull-right frontButton\"]")
    private WebElement buttonNewApp;

    @FindBy(xpath = "//select[@id='platformType']")
    private WebElement selectPlatformType;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement inputAppName;

    @FindBy(xpath = "//div[@id='categoryTree']/li")
    private List<WebElement> categoryTree;

    @FindBy(xpath = "//a[.=\"Save\"]")
    private WebElement buttonSave;

    public AppsPage() {
        super("/apps/list");
    }

    public AppPage openAppByName(String name) {
        WebElement pageLink = tableApps.findElement(By.xpath(String.format("/a[.=\"%s\"]", name)));
        pageLink.click();
        return (AppPage) PageObjectsFactory.getInstanceOfPage(AppPage.class);
    }

    public AppsPage addNewApp() {
        buttonNewApp.click();
        return this;
    }

    public AppsPage withPlatformType(String platformType) {
        Select platformTypeSelect = new Select(selectPlatformType);
        platformTypeSelect.selectByVisibleText(platformType);
        return this;
    }

    public AppsPage withAppName(String appName) {
        inputAppName.sendKeys(appName);
        return this;
    }

    public AppsPage withCategory() {
        categoryTree
                .get(new RandomDataGenerator().nextInt(0, categoryTree.size() - 1))
                .findElement(By.xpath("//span[@class=\"tree-checkbox tree-checkbox0\"]"))
                .click();
        return this;
    }

    public NetworksPage save() {
        buttonSave.click();
        return (NetworksPage) PageObjectsFactory.getInstanceOfPage(NetworksPage.class);
    }

    public String getAppPageUrlByName(String appName) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(tableApps));
        ((JavascriptExecutor) driver).executeScript("$(arguments[0]).focus();", tableApps);
        WebElement appA = tableApps.findElement(By.xpath(String.format("//a[.=\"%s\"]", appName)));
        List<String> href = new ArrayList<String>(Arrays.asList(appA.getAttribute("href").split("/")));
        return href.get(href.size() - 1);
    }

    @Override
    public boolean isDemandedPage() {
        return isElementPresent(By.xpath("//h1[.=\"Apps\"]"));
    }
}
