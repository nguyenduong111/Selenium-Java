package com.testcases;

import com.common.BaseSetup;
import com.common.Constants;
import com.common.TestListener;
import com.common.ultilities.LogUtils;
import com.common.ultilities.PropertiesFile;
import com.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


@Listeners(TestListener.class)
public class LoginPageTest extends BaseSetup {
    Constants constants = new Constants();

    WebDriver driver;
    LoginPage loginPage;
//    @BeforeClass
//    public void setUp() {
//        driver = getDriver();
//        loginPage = new LoginPage(driver);
//
//        Assert.assertEquals(driver.getCurrentUrl(), Constants.LOGIN_PAGE.URL_LOGIN, "verify url login page");
//    }

    @BeforeClass
    public void beforeClass() throws Exception {
        TestListener.setExcellFile(Constants.LOGIN_PAGE.EXCEL_PATH, Constants.LOGIN_PAGE.SHEET_NAME, 1, 11);
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        driver = initChromeDriver();
        driver.navigate().to(Constants.LOGIN_PAGE.URL_LOGIN);
        loginPage = new LoginPage(driver);
        PropertiesFile.setPropertiesFile();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test(priority = 1)
    public void LoginWithValidCredentials() throws InterruptedException {
        LogUtils.info("TC-1");

//        loginPage.sendEmail("company@example.com");
//        loginPage.sendPassword("1234");
        loginPage.sendEmail(PropertiesFile.getPropValue("email"));
        loginPage.sendPassword(PropertiesFile.getPropValue("password"));
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(), Constants.DASHBOARD_PAGE.URL_DASHBOARD, "Verify successful login for a standard valid user");
    }

    @Test(priority = 2)
    public void LoginWithInvalidEmail() throws InterruptedException {
        LogUtils.info("TC-2");

        loginPage.sendEmail("123@example.com");
        loginPage.sendPassword("1234");
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        Assert.assertEquals(loginPage.getLoginMessageError(), Constants.LOGIN_PAGE.ERROR_MESSAGE_EMAIL, "Verify login failure with incorrect Email (email does not exist)\n");
    }

    @Test(priority = 3)
    public void LoginWithInvalidPassword() throws InterruptedException {
        LogUtils.info("TC-3");

        loginPage.sendEmail("company@example.com");
        loginPage.sendPassword("1sdf5");
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        Assert.assertEquals(loginPage.getLoginMessageError(), Constants.LOGIN_PAGE.ERROR_MESSAGE, "Verify login failure with incorrect Password (email exists)\n");
    }

    @Test(priority = 4)
    public void LoginWithInvalidEmailAndPassword() throws InterruptedException {
        LogUtils.info("TC-4");

        loginPage.sendEmail("123@example.com");
        loginPage.sendPassword("1sdf");
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        Assert.assertEquals(loginPage.getLoginMessageError(), Constants.LOGIN_PAGE.ERROR_MESSAGE_EMAIL, "Verify login failure with incorrect Email (email does not exist)\n");
    }

    @Test(priority = 5)
    public void LoginWithEmptyEmail() throws InterruptedException {
        LogUtils.info("TC-5");

        loginPage.sendEmail("");
        loginPage.sendPassword("1234");
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        String temp = loginPage.getEmailElement().getCssValue("border-color");
        System.out.println(temp);
        Assert.assertEquals(temp, Constants.LOGIN_PAGE.ERROR_MESSAGE_COLOR, "Verify login failure with empty email\n");
    }

    @Test(priority = 6)
    public void LoginWithEmptyPassword() throws InterruptedException {
        LogUtils.info("TC-6");

        loginPage.sendEmail("company@example.com");
        loginPage.sendPassword("");
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        String temp = loginPage.getPasswordElement().getCssValue("border-color");
        System.out.println(temp);
        Assert.assertEquals(temp, Constants.LOGIN_PAGE.ERROR_MESSAGE_COLOR, "Verify login failure with empty password\n");
    }

    @Test(priority = 7)
    public void LoginWithEmptyEmailAndPassword() throws InterruptedException {
        LogUtils.info("TC-7");

        loginPage.sendEmail("");
        loginPage.sendPassword("");
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        String emailColor = loginPage.getEmailElement().getCssValue("border-color");
        Assert.assertEquals(emailColor, Constants.LOGIN_PAGE.ERROR_MESSAGE_COLOR, "Verify login failure with empty email and password\n");

        String passwordColor = loginPage.getPasswordElement().getCssValue("border-color");
        Assert.assertEquals(passwordColor, Constants.LOGIN_PAGE.ERROR_MESSAGE_COLOR, "Verify login failure with empty email and password\n");

    }

    @Test(priority = 8)
    public void LoginWithInvalidEmailFormat() throws InterruptedException {
        LogUtils.info("TC-8");

        loginPage.sendEmail("companyexample.com");
        loginPage.sendPassword("1234");
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        String emailColor = loginPage.getEmailElement().getCssValue("border-color");
        Assert.assertEquals(emailColor, Constants.LOGIN_PAGE.ERROR_MESSAGE_COLOR, "Verify login failure with empty email and password\n");
    }

    @Test(priority = 9)
    public void LoginWithInvalidPasswordFormat() throws InterruptedException {
        LogUtils.info("TC-9");

//        loginPage.sendEmail("company@example.com");
        loginPage.sendPassword("123");
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        String passwordType = loginPage.getPasswordElement().getDomAttribute("type");
        Assert.assertEquals(passwordType, "password", "Verify login failure with empty email and password\n");
    }

    @Test(priority = 10)
    public void VerifyForgotPasswordLink() throws InterruptedException {
        LogUtils.info("TC-10");

        Assert.assertTrue(loginPage.checkForgotPasswordVisibility(), "The \"Forgot Your Password?\" link is visible on the page.\n");
    }

    @Test(priority = 11)
    public void VerifyForgotPasswordLinkIsClickable() throws InterruptedException {
        LogUtils.info("TC-11");

        loginPage.clickForgotPasswordLink();

        Assert.assertTrue(driver.getCurrentUrl().contains(Constants.LOGIN_PAGE.URL_FORGOT_PASSWORD), "Verify \"Forgot Your Password?\" link is clickable\n");
        Assert.assertEquals(loginPage.getForgotPasswordTitle(), Constants.LOGIN_PAGE.FORGOT_PASSWORD_TITLE, "Verify \"Forgot Your Password?\" title\n");
        Assert.assertTrue(loginPage.clickSendPasswordResetLink(), "Verify \"Send Password Reset Instructions\" link is clickable\n");
    }




















}
