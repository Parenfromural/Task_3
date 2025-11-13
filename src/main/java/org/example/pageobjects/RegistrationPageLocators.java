package org.example.pageobjects;

import org.openqa.selenium.By;

public class RegistrationPageLocators {
    // Поле ввода Имя
    public static final By NAME_INPUT = By.xpath("//input[@type='text' and @name='name']");

    // Поле ввода Email
    public static final By EMAIL_INPUT = By.xpath("(//input[@type='text' and @name='name'])[2]");

    // Поле ввода Пароля
    public static final By PASSWORD_INPUT = By.xpath("//input[@type='password' and @name='Пароль']");

    // Кнопка Зарегистрироваться
    public static final By REGISTER_BUTTON = By.xpath("//button[contains(text(),'Зарегистрироваться')]");

    // Текст ошибки по паролю
    public static final By PASSWORD_ERROR = By.xpath("//p[contains(@class,'input__error')]");

    // Кнопка войти
    public static final By LOGIN_LINK = By.xpath("//a[contains(text(),'Войти')]");
}