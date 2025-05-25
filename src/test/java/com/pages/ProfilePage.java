package com.pages;

import com.common.helpers.UpLoadFileHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    WebDriver driver;
    WebDriverWait wait;
    UpLoadFileHelper upLoadFileHelper;

    By e_profileAvatar_input = By.xpath("//div[@class='choose-files ']//label[@for='profile']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        upLoadFileHelper = new UpLoadFileHelper(driver);
    }

    public void clickChooseProfileAvatar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_profileAvatar_input));
        driver.findElement(e_profileAvatar_input).click();
    }

    public void sendProfileAvatar(String filePath) throws InterruptedException {
        upLoadFileHelper.sendUpLoadFile_actions(filePath);
    }




}

