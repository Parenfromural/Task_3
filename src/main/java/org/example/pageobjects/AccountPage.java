package org.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(AccountPageLocators.LOGOUT_BUTTON)).click();
    }

    public boolean isInfoTextDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.INFO_TEXT)).isDisplayed();
    }

    // Логотип в шапке
    public void clickHeaderLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(AccountPageLocators.HEADER_LOGO)).click();
    }
}