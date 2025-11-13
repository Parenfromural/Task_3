package org.example.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Клик по вкладке Булки
    public void clickBunsTab() {
        driver.findElement(MainPageLocators.TAB_BUNS).click();
    }

    // Клик по вкладке Соусы
    public void clickSaucesTab() {
        driver.findElement(MainPageLocators.TAB_SAUCES).click();
    }

    // Клик по вкладке Начинки
    public void clickFillingsTab() {
        driver.findElement(MainPageLocators.TAB_FILLINGS).click();
    }

    // Получить список всех ингредиентов на текущей вкладке
    public List<WebElement> getIngredients() {
        return driver.findElements(MainPageLocators.INGREDIENTS_LIST);
    }


    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(MainPageLocators.LOGIN_BUTTON));
        System.out.println("Кнопка 'Войти в аккаунт' найдена и кликается");
        loginButton.click();
    }

    public void clickPersonalAccount() {
        WebElement personalAccountLink = wait.until(ExpectedConditions.elementToBeClickable(MainPageLocators.PERSONAL_ACCOUNT_LINK));
        System.out.println("Ссылка 'Личный Кабинет' найдена и кликается");
        personalAccountLink.click();
    }

    public boolean isSectionHeaderVisible(String sectionName) {
        return !driver.findElements(MainPageLocators.sectionHeaderByName(sectionName)).isEmpty() &&
                wait.until(ExpectedConditions.visibilityOfElementLocated(MainPageLocators.sectionHeaderByName(sectionName))) != null;
    }
}