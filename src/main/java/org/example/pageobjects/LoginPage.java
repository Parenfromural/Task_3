package org.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoginPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.LOGIN_HEADER)).isDisplayed();
    }

    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(LoginPageLocators.EMAIL_INPUT));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(LoginPageLocators.PASSWORD_INPUT));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void togglePasswordVisibility() {
        wait.until(ExpectedConditions.elementToBeClickable(LoginPageLocators.PASSWORD_TOGGLE_ICON)).click();
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LoginPageLocators.LOGIN_BUTTON)).click();
    }

}