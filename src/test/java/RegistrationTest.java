import org.example.pageobjects.RegistrationPage;
import io.qameta.allure.Step;
import org.example.pageobjects.WebDriverFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private RegistrationPage registrationPage;

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Step("Успешная регистрация с валидными данными в браузере {0}")
    public void testSuccessfulRegistration(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://stellarburgers.education-services.ru/register");
        registrationPage = new RegistrationPage(driver);

        String name = "Test User";
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        String password = "password123";

        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.clickRegisterButton();

        boolean registered = registrationPage.waitForUrlContains("/login");
        assertTrue(registered, "Регистрация не завершилась переходом на страницу логина");

        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Step("Ошибка при регистрации с некорректным паролем (меньше 6 символов) в браузере {0}")
    public void testRegistrationInvalidPassword(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://stellarburgers.education-services.ru/register");
        registrationPage = new RegistrationPage(driver);

        String name = "Test User";
        String email = "testuser" + System.currentTimeMillis() + "@yandex.com";
        String shortPassword = "123";

        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(shortPassword);
        registrationPage.clickRegisterButton();

        String errorText = registrationPage.getPasswordErrorText();
        assertTrue(errorText.contains("Некорректный пароль"), "Ошибка по паролю не отображается или некорректна");

        driver.quit();
    }
}