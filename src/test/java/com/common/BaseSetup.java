package com.common;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseSetup {

    private WebDriver driver;

    static String driverChromePath = "src/main/resources/chromedriver-win64/chromedriver.exe";

    public WebDriver getDriver() {
        return driver;
    }

    //Hàm này để tùy chọn Browser. Cho chạy trước khi gọi class này (BeforeClass)
    private void setDriver(String browserType, String appURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                driver.navigate().to(appURL);
                break;
//            case "firefox":
//                driver = initFirefoxDriver(appURL);
//                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
                driver.navigate().to(appURL);
        }
    }


    //Khởi tạo cấu hình của các Browser để đưa vào Switch Case

    public WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
//        System.setProperty("webdriver.chrome.driver", driverChromePath);

        ChromeOptions options = new ChromeOptions();

// Ẩn danh → không lưu cache, không hiển thị gợi ý lưu mật khẩu
        options.addArguments("--incognito");

// Tắt popup mật khẩu
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
//        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }


    // Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
    @Parameters({"browserType", "appURL"})
    @BeforeClass
    public void initializeTestBaseSetup(String browserType, String appURL) {
        try {
            // Khởi tạo driver và browser
            setDriver(browserType, appURL);
            System.out.println("Initialize Test Base Setup");
        } catch (Exception e) {
            System.out.println("Error..." + Arrays.toString(e.getStackTrace()));
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
