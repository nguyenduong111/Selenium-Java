package com.pages;

import com.common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public void sendEmail(String email) {
        WebElement emailInput = driver.findElement(Constants.LOGIN_PAGE.e_email_input);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void sendPassword(String password) {
        WebElement passwordInput = driver.findElement(Constants.LOGIN_PAGE.e_password_input);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(Constants.LOGIN_PAGE.e_login_button).click();
    }

    public String getLoginMessageError() {
        WebElement errorMessage = driver.findElement(Constants.LOGIN_PAGE.e_errorMessage_text);
        return errorMessage.getText();
    }

    public WebElement getEmailElement() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.LOGIN_PAGE.e_email_input));
        return driver.findElement(Constants.LOGIN_PAGE.e_email_input);
    }

    public WebElement getPasswordElement() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.LOGIN_PAGE.e_password_input));
        return driver.findElement(Constants.LOGIN_PAGE.e_password_input);
    }

    public boolean checkForgotPasswordVisibility() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.LOGIN_PAGE.e_forgotPassword_link));
        WebElement forgotPasswordLink = driver.findElement(Constants.LOGIN_PAGE.e_forgotPassword_link);
        return forgotPasswordLink.isDisplayed();
    }

    public void clickForgotPasswordLink() {
        WebElement forgotPasswordLink = driver.findElement(Constants.LOGIN_PAGE.e_forgotPassword_link);
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        forgotPasswordLink.click();
    }

    public String getForgotPasswordTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.LOGIN_PAGE.e_forgotPassword_text));
        return driver.findElement(Constants.LOGIN_PAGE.e_forgotPassword_text).getText();
    }

    public boolean clickSendPasswordResetLink() {
        wait.until(ExpectedConditions.elementToBeClickable(Constants.LOGIN_PAGE.e_sendPasswordReset_btn));
        return driver.findElement(Constants.LOGIN_PAGE.e_sendPasswordReset_btn).isEnabled();
    }
}
