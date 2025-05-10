package com.common;

import com.common.helpers.ExcelHelpers;
import com.common.ultilities.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    static ExcelHelpers excelHelpers = new ExcelHelpers();
    static int rownum;
    static int colnum;

    public static void setExcellFile(String ExcelPath, String SheetName, int row, int col) throws Exception {
        excelHelpers.setExcelFile(ExcelPath, SheetName);
        rownum = row;
        colnum = col;
    }


    // đc gọi sau khi thực hiện xong tất cả các test
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test finished");
    }

    // đc gọi trước khi thực hiện tất cả các test
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test started");
    }

    //------------------------------------
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        try {
            excelHelpers.setCellData("FAIL", rownum, colnum);
            LogUtils.error(arg0.getName() +" - " + arg0.getThrowable().getMessage());
            rownum++;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        try {
            excelHelpers.setCellData("PASS", rownum, colnum);
            LogUtils.info(arg0.getName() +" - " + "PASS");
            rownum++;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
