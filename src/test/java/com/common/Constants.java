package com.common;

import org.openqa.selenium.By;

public class Constants {
    public static class LOGIN_PAGE {
        public static final By e_loginTitle_text = By.xpath("//h2[normalize-space()='Login']");
        public static final By e_login_button = By.xpath("//button[normalize-space()='Login']");
        public static final By e_email_input = By.xpath("//input[@id='email']");
        public static final By e_password_input = By.xpath("//input[@id='password']");
        public static final By e_errorMessage_text = By.xpath("//small[1]");
        public static final By e_forgotPassword_link = By.xpath("//a[normalize-space()='Forgot Your Password?']");
        public static final By e_forgotPassword_text = By.xpath("//h2[normalize-space()='Forgot Password']");
        public static final By e_sendPasswordReset_btn = By.xpath("//button[normalize-space()='Send Password Reset Link']");

        public static final String EXCEL_PATH = "src/test/resources/report_demo.xlsx";
        public static final String SHEET_NAME = "Login";

        public static final String URL_LOGIN = "https://demo.workdo.io/hrmgo/login";
        public static final String ERROR_MESSAGE_EMAIL = "this email doesn't match";
        public static final String ERROR_MESSAGE = "These credentials do not match our records.";
        public static final String ERROR_MESSAGE_COLOR = "rgb(255, 58, 110)";

        public static final String URL_FORGOT_PASSWORD = "https://demo.workdo.io/hrmgo/forgot-password";
        public static final String FORGOT_PASSWORD_TITLE = "Forgot Password";
    }

    public static class DASHBOARD_PAGE {
        public static final String URL_DASHBOARD = "https://demo.workdo.io/hrmgo/dashboard";
    }

    public static class SIDE_BAR {
        public static final String MESSAGE_CHANGE_LANGUAGE = "Language change successfully.";

        public static final String EXCEL_PATH = "src/test/resources/report_demo.xlsx";
        public static final String SHEET_NAME = "Change_Language";
    }

}
