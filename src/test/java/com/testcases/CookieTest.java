package com.testcases;

import com.common.BaseSetup;
import com.common.ultilities.PropertiesFile;
import com.pages.LoginPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

public class CookieTest extends BaseSetup {
    WebDriver driver;
    LoginPage loginPage;
    Set<Cookie> cookiesTest;


    @BeforeClass
    public void beforeClass() throws Exception {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        cookiesTest = new java.util.HashSet<>();
        PropertiesFile.setPropertiesFile();
    }

    @Test(priority = 1)
    public void getCookieAfterLogin() throws Exception {
        loginPage.sendEmail(PropertiesFile.getPropValue("email"));
        loginPage.sendPassword(PropertiesFile.getPropValue("password"));
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        Set<Cookie> cookies = driver.manage().getCookies();

        System.out.println("Number of cookies: " + cookies.size());
        for(Cookie c : cookies) {
            cookiesTest.add(c);
            System.out.println(c.getName() + " : " + c.getValue());
        }
    }

    @Test(priority = 2)
    public void addCookie() throws Exception {
        Date date = new Date(2025, 5, 18, 17, 00, 00);
        Cookie cookie = new Cookie("ck1", "123abc", "demo.workdo.io", "/", date, true, false);

        driver.manage().addCookie(cookie);

        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Number of cookies: " + cookies.size());
        for(Cookie c : cookies) {
            System.out.println(c.getName() + " : " + c.getValue());
        }
    }

    @Test(priority = 3)
    public void deleteCookie() throws Exception {
//        driver.manage().deleteCookieNamed("ck1");
        driver.manage().deleteAllCookies();


        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Number of cookies: " + cookies.size());
        for(Cookie c : cookies) {
            System.out.println(c.getName() + " : " + c.getValue());
        }

        driver.navigate().refresh();
        Thread.sleep(3000);
    }

    @Test(priority = 4)
    public void addCookieLogin() throws Exception {
        driver.manage().deleteAllCookies();

        for(Cookie c : cookiesTest) {
            driver.manage().addCookie(c);
        }

        driver.navigate().refresh();
        Thread.sleep(3000);
    }
}
