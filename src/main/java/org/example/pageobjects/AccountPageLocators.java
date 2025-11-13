package org.example.pageobjects;

import org.openqa.selenium.By;

public class AccountPageLocators {
    // Кнопка выхода
    public static final By LOGOUT_BUTTON = By.xpath("//button[contains(text(),'Выход')]");

    // Информационный текст под меню
    public static final By INFO_TEXT = By.xpath("//p[contains(@class,'Account_text') and contains(text(),'персональные данные')]");

    // Логотип в шапке
    public static final By HEADER_LOGO = By.xpath("//header//a[@href='/' and contains(@class,'AppHeader_header__link__3D_hX')]");


}