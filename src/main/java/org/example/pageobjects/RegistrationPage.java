package org.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // Ввести имя пользователя
    public void enterName(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(RegistrationPageLocators.NAME_INPUT));
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    // Ввести email пользователя
    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(RegistrationPageLocators.EMAIL_INPUT));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    // Ввести пароль пользователя
    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(RegistrationPageLocators.PASSWORD_INPUT));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    // Кликнуть по кнопке Зарегистрироваться
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(RegistrationPageLocators.REGISTER_BUTTON)).click();
    }

    // Получить текст ошибки по паролю
    public String getPasswordErrorText() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(RegistrationPageLocators.PASSWORD_ERROR));
        return error.getText();
    }

    // Ожидание, что URL содержит заданную подстроку
    public boolean waitForUrlContains(String substring) {
        return wait.until(ExpectedConditions.urlContains(substring));
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(RegistrationPageLocators.LOGIN_LINK)).click();
    }
}