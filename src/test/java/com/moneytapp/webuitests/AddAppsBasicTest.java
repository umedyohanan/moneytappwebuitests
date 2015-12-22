package com.moneytapp.webuitests;

import com.moneytapp.webuitests.pageobjects.AppPage;
import com.moneytapp.webuitests.pageobjects.AppsPage;
import com.moneytapp.webuitests.pageobjects.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by u.yahyoev on 21.12.2015.
 */
public class AddAppsBasicTest extends BasicTest {

    @BeforeMethod
    public void setUp() {
        LoginPage loginPage = new LoginPage();

        loginPage.open()
                .withEmail(configuration.getProperty("user.email"))
                .withPassword(configuration.getProperty("user.password"))
                .logIn();
    }

    @Test(dataProvider = "appname")
    public void addAndroidApp(String appname) {
        AppsPage appsPage = new AppsPage().open();
        appsPage.addNewApp()
                .withPlatformType("Android")
                .withAppName(appname)
                .withCategory()
                .save();

        appsPage.open();
        String appUrl = appsPage.getAppPageUrlByName(appname);
        AppPage appPage = new AppPage(appUrl).open();


    }

    @Test(dataProvider = "appname", enabled = false)
    public void addiOsApp(String appname) {
        AppsPage appsPage = new AppsPage().open();
        appsPage.addNewApp()
                .withPlatformType("iOS")
                .withAppName(appname)
                .withCategory()
                .save();

        appsPage.open();
        String appUrl = appsPage.getAppPageUrlByName(appname);
        AppPage appPage = new AppPage(appUrl).open();

    }

}
