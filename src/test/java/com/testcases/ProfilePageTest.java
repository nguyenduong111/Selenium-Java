package com.testcases;

import com.common.BaseSetup;
import com.common.Constants;
import com.common.TestListener;
import com.common.ultilities.LogUtils;
import com.common.ultilities.PropertiesFile;
import com.pages.LoginPage;
import com.pages.ProfilePage;
import com.pages.TimeSheetPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProfilePageTest extends BaseSetup {
    WebDriver driver;
    LoginPage loginPage;
    ProfilePage profilePage;

    @BeforeClass
    public void beforeClass() throws Exception {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        PropertiesFile.setPropertiesFile();
    }

    @BeforeClass
    public void beforeTest_Login() throws Exception {
        LogUtils.info("Login successfully");
        loginPage.sendEmail(PropertiesFile.getPropValue("email"));
        loginPage.sendPassword(PropertiesFile.getPropValue("password"));
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        driver.navigate().to(Constants.PROFILE_PAGE.URL_PROFILE);
    }

    @Test(priority = 1)
    public void VerifyClickChangeProfileAvatar() throws InterruptedException {
        LogUtils.info("Click Change profile avatar");
        profilePage.clickChooseProfileAvatar();
    }

    @Test(priority = 2)
    public void VerifySendProfileAvatar() throws InterruptedException {
        LogUtils.info("Send profile avatar");
        profilePage.sendProfileAvatar(PropertiesFile.getPropValue("filePathAvatarProfile"));
    }
}
