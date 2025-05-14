package com.testcases;

import com.common.BaseSetup;
import com.common.TestListener;
import com.common.TimeSheetData;
import com.common.ultilities.LogUtils;
import com.common.ultilities.PropertiesFile;
import com.pages.LoginPage;
import com.pages.TimeSheetPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import com.common.Constants;
import org.testng.annotations.Test;

import java.util.List;

//TC about time sheet and data table
@Listeners(com.common.TestListener.class)
public class TimeSheetTest extends BaseSetup {
    WebDriver driver;
    LoginPage loginPage;
    TimeSheetPage timeSheetPage;

    @BeforeClass
    public void beforeClass() throws Exception {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        timeSheetPage = new TimeSheetPage(driver);
        PropertiesFile.setPropertiesFile();

        TestListener.setExcellFile(driver, Constants.TIME_SHEET_PAGE.EXCEL_PATH, Constants.TIME_SHEET_PAGE.SHEET_NAME, 1, 11);
    }

    @BeforeClass
    public void beforeTest_Login() throws Exception {
        LogUtils.info("Login and go to TimeSheet page");
        loginPage.sendEmail(PropertiesFile.getPropValue("email"));
        loginPage.sendPassword(PropertiesFile.getPropValue("password"));
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        driver.navigate().to(Constants.TIME_SHEET_PAGE.URL_TIME_SHEET);

        Assert.assertEquals(timeSheetPage.getTimeSheetTitleText(), Constants.TIME_SHEET_PAGE.TIME_SHEET_TITLE, "Verify successful login for a standard valid user");
    }

    @Test(priority = 1)
    public void VerifySearchButtonWithoutFilters() throws InterruptedException {
        LogUtils.info("TC-21");

        timeSheetPage.clickSearchButtonSubmit();
        List<TimeSheetData> timeSheetData = timeSheetPage.getTimeSheetTableBody();
//        Thread.sleep(3000);

        Assert.assertTrue(timeSheetData.isEmpty(), "Verify that the table has data");
    }

    @Test(priority = 2)
    public void FilterByEmployee() throws InterruptedException {
        LogUtils.info("TC-22");

        String name = "Julie workdo";

        timeSheetPage.clearStartDate();
        timeSheetPage.clearEndDate();
        timeSheetPage.selectEmployee("Julie workdo");
        timeSheetPage.clickSearchButtonSubmit();
        List<TimeSheetData> timeSheetData = timeSheetPage.getTimeSheetTableBody();
//        Thread.sleep(3000);

        System.out.println("------------" + timeSheetData.size());
        Assert.assertEquals(timeSheetData.size(), 2, "Verify that the table has data");
        Assert.assertEquals(timeSheetData.get(0).getEmployee(), name, "Verify that the table has data");
    }

    @Test(priority = 3)
    public void FilterByEmployeeAll() throws InterruptedException {
        LogUtils.info("TC-23");

        timeSheetPage.clickSearchResetButton();
        timeSheetPage.clearStartDate();
        timeSheetPage.clearEndDate();
        timeSheetPage.selectEmployee("All");

        List<TimeSheetData> timeSheetData = timeSheetPage.getTimeSheetTableBody();
        Assert.assertEquals(timeSheetData.size(), 7, "Verify that the table has data");
    }
    @Test(priority = 4)
    public void FilterByDate() throws InterruptedException {
        LogUtils.info("TC-24");

        timeSheetPage.clickSearchResetButton();
        timeSheetPage.clearStartDate();
        timeSheetPage.sendStartDate("01052025");
        timeSheetPage.clearEndDate();
        timeSheetPage.sendEndDate("30052025");

        timeSheetPage.clickSearchButtonSubmit();

        List<TimeSheetData> timeSheetData = timeSheetPage.getTimeSheetTableBody();
        Assert.assertEquals(timeSheetData.size(), 2, "Verify that the table has data");
    }

    @Test(priority = 5)
    public void FilterByStartDateOnly() throws InterruptedException {
        LogUtils.info("TC-25");
        throw new SkipException("TC is holding");
    }

    @Test(priority = 6)
    public void tc26() throws InterruptedException {
        LogUtils.info("TC-26");
        throw new SkipException("TC is holding");
    }

    @Test(priority = 7)
    public void tc27() throws InterruptedException {
        LogUtils.info("TC-27");
        throw new SkipException("TC is holding");
    }

    @Test(priority = 8)
    public void tc28() throws InterruptedException {
        LogUtils.info("TC-28");
        throw new SkipException("TC is holding");
    }

    @Test(priority = 9)
    public void tc29() throws InterruptedException {
        LogUtils.info("TC-29");
        throw new SkipException("TC is holding");
    }

    @Test(priority = 10)
    public void GeneralTableSearchByKeyword() throws InterruptedException {
        LogUtils.info("TC-30");

        timeSheetPage.clickSearchResetButton();
        timeSheetPage.sendSearchKeyword("15");

        List<TimeSheetData> timeSheetData = timeSheetPage.getTimeSheetTableBody();
        Assert.assertEquals(timeSheetData.size(), 2, "Verify that the table has data");
    }

    @Test(priority = 11)
    public void tc31() throws InterruptedException {
        LogUtils.info("TC-31");
        throw new SkipException("TC is holding");
    }
    @Test(priority = 12)
    public void tc32() throws InterruptedException {
        LogUtils.info("TC-32");
        throw new SkipException("TC is holding");
    }
    @Test(priority = 13)
    public void tc33() throws InterruptedException {
        LogUtils.info("TC-33");
        throw new SkipException("TC is holding");
    }
    @Test(priority = 14)
    public void tc34() throws InterruptedException {
        LogUtils.info("TC-34");
        throw new SkipException("TC is holding");
    }







}
