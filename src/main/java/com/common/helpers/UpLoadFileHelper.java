package com.common.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class UpLoadFileHelper {
    WebDriver driver;
    WebDriverWait wait;

    public UpLoadFileHelper(WebDriver dr) {
        driver = dr;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

//    public void clickUpLoadFile(By inputUpLoadFile) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUpLoadFile));
//        driver.findElement(inputUpLoadFile).click();
//    }

    // tag input type file. Can drag/drop files
    public void sendUpLoadFile_sendKeys(By inputUpLoadFile, String filePath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUpLoadFile));
        driver.findElement(inputUpLoadFile).sendKeys(filePath);
    }

    // 1. click input file
    // 2. copy file path into clipboard
    // 3. ctrl + v to paste
    // 4. enter
    public void sendUpLoadFile_actions(String filePath) throws InterruptedException {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        //Copy file path to clipboard
        StringSelection stringSelection = new StringSelection(filePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        Thread.sleep(1000);

        //Paste file path : ctrl + v
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(3000);
    }
}
