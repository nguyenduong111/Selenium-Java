package com.pages;

import com.common.TimeSheetData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TimeSheetPage {
    WebDriver driver;
    WebDriverWait wait;

    public TimeSheetPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By e_timeSheetTitle_text = By.xpath("//div[@class='page-header-title']/h4");

    private final By e_searchInput_input = By.xpath("//input[@placeholder='Search...']");
    private final By e_selectEmployee_select = By.xpath("//select[@id='employee_id']");
    private final By e_selectStartDate_input = By.xpath("//input[@id='start_date']");
    private final By e_selectEndDate_input = By.xpath("//input[@id='end_date']");

    private final By e_searchButtonSubmit_button = By.xpath("//div[@class='col-auto float-end ms-2 mt-4']/a[1]");
    private final By e_searchButtonReset_button = By.xpath("//div[@class='col-auto float-end ms-2 mt-4']/a[2]");

    private final By e_timeSheetTable_header = By.xpath("//table[@id='pc-dt-simple']//thead//tr//th");
    private final By e_timeSheetTable_body = By.xpath("//tbody//tr");

    private final By e_entriesPerPage_select = By.xpath("//select[@class='dataTable-selector']");
    private final By e_infoTotalTtems_text = By.xpath("//div[@class='dataTable-info']");
    private final By e_nextPage_button = By.xpath("//a[contains(text(),'›')]");

    public String getTimeSheetTitleText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_timeSheetTitle_text));
        return driver.findElement(e_timeSheetTitle_text).getText();
    }

    public List<String> getTimeSheetTableHeader() {
        List<String> headerTitle = new java.util.ArrayList<>();

        wait.until(ExpectedConditions.visibilityOfElementLocated(e_timeSheetTable_header));
        List<WebElement> header = driver.findElements(e_timeSheetTable_header);

        for(WebElement element : header) {
            WebElement headerElement = element.findElement(By.tagName("a"));
            headerTitle.add(headerElement.getText());
            System.out.println(headerElement.getText());
        }

        return headerTitle;
    }

    public List<TimeSheetData> getTimeSheetTableBody() {
        List<TimeSheetData> timeSheetData = new java.util.ArrayList<>();

        wait.until(ExpectedConditions.visibilityOfElementLocated(e_timeSheetTable_body));
        List<WebElement> body = driver.findElements(e_timeSheetTable_body);

        for(WebElement element : body) {
            try {
                String employeeName = element.findElement(By.xpath(".//td[1]")).getText();
                String date = element.findElement(By.xpath(".//td[2]")).getText();
                String hours = element.findElement(By.xpath(".//td[3]")).getText();
                String remark = element.findElement(By.xpath(".//td[4]")).getText();

                System.out.println(employeeName + ", " + date + ", " + hours + ", " + remark);
                TimeSheetData data = new TimeSheetData(employeeName, date, hours, remark);
                timeSheetData.add(data);
            } catch (Exception e) {
                return timeSheetData;
            }
        }

        return timeSheetData;
    }

    public void clickSearchButtonSubmit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_searchButtonSubmit_button));
        driver.findElement(e_searchButtonSubmit_button).click();
    }

    public void clearStartDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_selectStartDate_input));
        driver.findElement(e_selectStartDate_input).clear();
    }

    public void sendStartDate(String startDate) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_selectStartDate_input));
        driver.findElement(e_selectStartDate_input).sendKeys(startDate);
    }

    public void clearEndDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_selectEndDate_input));
        driver.findElement(e_selectEndDate_input).clear();
    }

    public void sendEndDate(String startDate) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_selectEndDate_input));
        driver.findElement(e_selectEndDate_input).sendKeys(startDate);
    }

    public void selectEmployee(String employee) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_selectEmployee_select));
        Select select = new Select(driver.findElement(e_selectEmployee_select));

        select.selectByContainsVisibleText(employee);
    }

    public void clickSearchResetButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_searchButtonReset_button));
        driver.findElement(e_searchButtonReset_button).click();
    }

    public void sendSearchKeyword(String keyword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_searchInput_input));
        driver.findElement(e_searchInput_input).sendKeys(keyword);
    }

    public List<String> selectEntriesPerPage(String entriesPerPage) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_entriesPerPage_select));
        Select selectItemsPerPage = new Select(driver.findElement(e_entriesPerPage_select));

        List<String> listItems = new ArrayList<String>();
        List<WebElement> selectedOptions = selectItemsPerPage.getOptions();

//        System.out.println("Selected options: " + selectedOptions.size());
        for(WebElement e : selectedOptions) {
            System.out.println(e.getText());
            listItems.add(e.getText());
        }

        selectItemsPerPage.selectByVisibleText(entriesPerPage);

        return listItems;
    }

    public void pagnationData(String entriesPerPage) throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_infoTotalTtems_text));
        wait.until(ExpectedConditions.visibilityOfElementLocated(e_nextPage_button));

        WebElement infoTotalItems = driver.findElement(e_infoTotalTtems_text);

        String info = infoTotalItems.getText();
        System.out.println(info);

        String[] items = info.split(" ");
        System.out.println("Total Items: " + items[5]);
        int totalItems = Integer.parseInt(items[5].trim());

        float totalPage = (float) totalItems / Integer.parseInt(entriesPerPage);
        int int_totalPage = (int) Math.ceil(totalPage);
        System.out.println("Total Page: " + int_totalPage);

        for(int i = 0; i < int_totalPage-1; i++) {
            driver.findElement(e_nextPage_button).click();
            Thread.sleep(3000);
        }
    }


}
