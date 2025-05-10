package com.testcases;

import com.common.BaseSetup;
import com.common.Constants;
import com.common.LanguageClass;
import com.common.TestListener;
import com.pages.LoginPage;
import com.pages.SideBar;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Listeners(TestListener.class)
public class SideBarTest extends BaseSetup {
    WebDriver driver;
    SideBar sideBar;
    LoginPage loginPage;
//    LanguageClass languageClass = new LanguageClass();

    @BeforeClass
    public void beforeClass() throws Exception {
//        TestListener.setExcellFile(Constants.SIDE_BAR.EXCEL_PATH, Constants.SIDE_BAR.SHEET_NAME, 1, 11);
        driver = getDriver();
        sideBar = new SideBar(driver);
        loginPage = new LoginPage(driver);

        TestListener.setExcellFile(Constants.SIDE_BAR.EXCEL_PATH, Constants.SIDE_BAR.SHEET_NAME, 1, 11);
    }

    @BeforeClass
    public void beforeTest_Login() throws Exception {
        loginPage.sendEmail("company@example.com");
        loginPage.sendPassword("1234");
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(), Constants.DASHBOARD_PAGE.URL_DASHBOARD, "Verify successful login for a standard valid user");
    }

    @Test(priority = 1)
    public void VerifySideBarItems() throws InterruptedException {
        System.out.println("TC-1");
        sideBar.clickLanguageMenu();   // open
//        sideBar.getSideBarItemsText();

        Thread.sleep(3000);
        sideBar.clickLanguageMenu();   // close
    }

    @Test(priority = 2)
    public void VerifySelectLanguage() throws InterruptedException {
        System.out.println("TC-2");
        sideBar.clickLanguageMenu();
        List<WebElement> listLanguageItems = sideBar.getMenuLanguage();
        System.out.println(listLanguageItems.size());
        boolean result = sideBar.selectLanguage("Chinese", listLanguageItems);
        Thread.sleep(3000);
//        sideBar.getSideBarItemsText();

        Assert.assertTrue(result, "Verify select language");
    }

    @Test(priority = 3)
    public void VerifySideBarItemsAfterSelectLanguage() throws InterruptedException {
        System.out.println("TC-3");
//        sideBar.clickLanguageMenu();
        List<String> listSideBarItemsTitle = sideBar.getSideBarItemsText();
        String[] chineseTitles = LanguageClass.getTitles("chinese");

        Assert.assertEquals(listSideBarItemsTitle.size(), chineseTitles.length, "Verify select language");

        for (int i = 0; i < listSideBarItemsTitle.size(); i++) {
            System.out.println(listSideBarItemsTitle.get(i) + " - " + chineseTitles[i]);
            Assert.assertEquals(listSideBarItemsTitle.get(i), chineseTitles[i], "Verify select language");
        }
    }

    @Test(priority = 4)
    public void VerifySideBarItemsAfterSelectEnglish() throws InterruptedException {
        System.out.println("TC-4");
        sideBar.clickLanguageMenu();

        List<WebElement> listLanguageItems = sideBar.getMenuLanguage();
        boolean result = sideBar.selectLanguage("English", listLanguageItems);

        Assert.assertTrue(result, "Verify select language");
//        Thread.sleep(7000);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"), "complete"));
        List<String> listSideBarItemsTitle = sideBar.getSideBarItemsText();
        String[] englishTitles = LanguageClass.getTitles("english");

        Assert.assertEquals(listSideBarItemsTitle.size(), englishTitles.length, "Verify select language");

        for (int i = 0; i < listSideBarItemsTitle.size(); i++) {
            System.out.println(listSideBarItemsTitle.get(i) + " - " + englishTitles[i]);
            Assert.assertEquals(listSideBarItemsTitle.get(i), englishTitles[i], "Verify select language");
        }
    }

        @Test(priority = 5)
        public void VerifyPopUpChangeLanguage() throws InterruptedException {
            System.out.println("TC-5");
            sideBar.clickLanguageMenu();

            List<WebElement> listLanguageItems = sideBar.getMenuLanguage();
            boolean result = sideBar.selectLanguage("Chinese", listLanguageItems);

            Assert.assertTrue(result, "Verify select language");
    }
}
