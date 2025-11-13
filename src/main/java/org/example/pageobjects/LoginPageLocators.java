package org.example.pageobjects;

import org.openqa.selenium.By;

public class LoginPageLocators {
    // Заголовок окна входа
    public static final By LOGIN_HEADER = By.xpath("//h2[text()='Вход']");

    // Поле ввода Email
    public static final By EMAIL_INPUT = By.xpath("//input[@type='text' and @name='name']");

    // Поле ввода Пароля
    public static final By PASSWORD_INPUT = By.xpath("//input[@type='password' and @name='Пароль']");

    // Кнопка показать/скрыть пароль (иконка)
    public static final By PASSWORD_TOGGLE_ICON = By.xpath("//div[contains(@class,'input__icon-action')]");

    // Кнопка Войти
    public static final By LOGIN_BUTTON = By.xpath("//button[contains(text(),'Войти')]");

}