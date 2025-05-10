package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SideBar {
    WebDriver driver;
    WebDriverWait wait;


    /// /ul[@class='dash-navbar']/li[1]//a/span/span
    By e_sideBarItems_text = By.xpath("//ul[@class='dash-navbar']/li");
    By e_dropDownLanguage_button = By.xpath("//a[@id='dropdownLanguage']");
    By e_menuLanguage_button = By.xpath("//li[@class='dropdown dash-h-item drp-language']//div/a");
    By e_popupChangeLanguage_text = By.xpath("//div[@class='toast-body']");

    public SideBar(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<String> getSideBarItemsText() {
        List<String> listSideBarItemsTitle = new java.util.ArrayList<>();
        List<WebElement> sideBarItems = driver.findElements(e_sideBarItems_text);
        System.out.println("----------" + sideBarItems.size());
        for (int i = 0; i < sideBarItems.size(); i++) {
            WebElement sideBarItem = driver.findElement(By.xpath("//ul[@class='dash-navbar']/li[" + (i + 1) + "]//a//span[2]"));
            System.out.println("\"" + sideBarItem.getText() + "\",");
            listSideBarItemsTitle.add(sideBarItem.getText());
        }
        return listSideBarItemsTitle;
    }

    public void clickLanguageMenu() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(e_dropDownLanguage_button));

        driver.findElement(e_dropDownLanguage_button).click();
        Thread.sleep(3000);
    }

    public List<WebElement> getMenuLanguage() throws InterruptedException {
        List<WebElement> listLanguageItems = new java.util.ArrayList<>();

        List<WebElement> menuLanguage = driver.findElements(e_menuLanguage_button);
        for (int i = 0; i < menuLanguage.size(); i++) {
            WebElement menuLanguageItem = driver.findElement(By.xpath("//li[@class='dropdown dash-h-item drp-language']//div/a[" + (i + 1) + "]"));
            System.out.println(menuLanguageItem.getText());
            listLanguageItems.add(menuLanguageItem);
        }

        return listLanguageItems;
    }

    public boolean selectLanguage(String language, List<WebElement> listLanguageItems) throws InterruptedException {
        for (WebElement listLanguageItem : listLanguageItems) {
            if (listLanguageItem.getText().equals(language)) {
                listLanguageItem.click();

                String popupChangeLanguageText = getPopupChangeLanguageText();
                System.out.println("------------" + popupChangeLanguageText);
                Thread.sleep(8000);
                return true;
            }
        }
        return false;
    }

    public String getPopupChangeLanguageText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_popupChangeLanguage_text));
        return driver.findElement(e_popupChangeLanguage_text).getText();
    }


}
