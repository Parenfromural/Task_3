package org.example.pageobjects;

import org.openqa.selenium.By;

public class MainPageLocators {

    // Вкладки
    public static final By TAB_BUNS = By.xpath("//div[contains(@class,'tab_tab') and .//span[text()='Булки']]");
    public static final By TAB_SAUCES = By.xpath("//div[contains(@class,'tab_tab') and .//span[text()='Соусы']]");
    public static final By TAB_FILLINGS = By.xpath("//div[contains(@class,'tab_tab') and .//span[text()='Начинки']]");

    // Список ингредиентов (общий для текущей вкладки)
    public static final By INGREDIENTS_LIST = By.cssSelector("a.BurgerIngredient_ingredient__1TVf6");

    // Ссылка Личный Кабинет в шапке
    public static final By PERSONAL_ACCOUNT_LINK = By.xpath("//a[contains(@class,'AppHeader_header__link__3D_hX') and .//p[text()='Личный Кабинет']]");

    // Кнопка "Войти в аккаунт"
    public static final By LOGIN_BUTTON = By.xpath("//button[contains(text(),'Войти в аккаунт')]");

    // Видимый заголовок активной вкладки
    public static By sectionHeaderByName(String sectionName) {
        return By.xpath("//h2[contains(@class,'text_type_main-medium') and text()='" + sectionName + "']");
    }
}