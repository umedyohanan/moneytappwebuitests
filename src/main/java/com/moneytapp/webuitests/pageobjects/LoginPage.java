package com.moneytapp.webuitests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by u.yahyoev on 22.12.2015.
 */
public class LoginPage extends Page<LoginPage> {

    @FindBy(xpath = "//input[@id='j_username']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@id='j_password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@class=\"force-right\"]")
    private WebElement logIn;

    public LoginPage() {
        super("/login");
    }

    public LoginPage withEmail(String email) {
        inputEmail.sendKeys(email);
        return this;
    }

    public LoginPage withPassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    public DashboardPage logIn() {
        logIn.click();
        return (DashboardPage) PageObjectsFactory.getInstanceOfPage(DashboardPage.class);
    }

    @Override
    public boolean isDemandedPage() {
        return isElementPresent(By.xpath("//input[@class=\"force-right\"]"));
    }
}
