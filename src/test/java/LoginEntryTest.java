import org.example.pageobjects.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginEntryTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;

    private final String BASE_URL = "https://stellarburgers.education-services.ru";

    private String validEmail;
    private final String validPassword = "validPassword123";
    private String accessToken;

    private ApiUserClient apiUserClient;

    @BeforeAll
    public void initApiClient() {
        apiUserClient = new ApiUserClient(BASE_URL);
    }

    @AfterEach
    @Step("Удалить пользователя и закрыть браузер")
    public void tearDown() throws Exception {
        if (accessToken != null) {
            apiUserClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    private void initializeTest(String browser, String testName) throws Exception {
        validEmail = "user_" + UUID.randomUUID() + "@yandex.com";

        ApiUserClient.UserTokens tokens = apiUserClient.registerUser(validEmail, validPassword, "Test User");
        accessToken = tokens.getAccessToken();

        driver = WebDriverFactory.createDriver(browser);

        switch (testName) {
            case "Вход через кнопку «Войти в аккаунт» на главной":
            case "Вход через кнопку «Личный кабинет»":
                driver.get(BASE_URL + "/");
                break;
            case "Вход через кнопку в форме регистрации":
                driver.get(BASE_URL + "/register");
                break;
            case "Вход через кнопку в форме восстановления пароля":
                driver.get(BASE_URL + "/forgot-password");
                break;
            default:
                driver.get(BASE_URL + "/");
                break;
        }
        registerPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Step("Авторизоваться на странице логина")
    private void performLogin() {
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();
    }

    @Step("Проверить, что выполнен успешный вход (редирект на главную страницу)")
    private void verifySuccessfulLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean redirected = wait.until(d -> d.getCurrentUrl().endsWith("/"));
        Assertions.assertTrue(redirected, "После логина не произошло перенаправление на главную страницу");
    }

    @ParameterizedTest(name = "{1} в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной")
    @Step("Вход через кнопку «Войти в аккаунт» на главной")
    public void testLoginViaMainLoginButton(String browser) throws Exception {
        initializeTest(browser, "Вход через кнопку «Войти в аккаунт» на главной");
        mainPage.clickLoginButton();
        Assertions.assertTrue(loginPage.isLoginPageDisplayed(), "Страница логина не отображается");
        performLogin();
        verifySuccessfulLogin();
    }

    @ParameterizedTest(name = "{1} в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Step("Вход через кнопку «Личный кабинет»")
    public void testLoginViaPersonalAccountButton(String browser) throws Exception {
        initializeTest(browser, "Вход через кнопку «Личный кабинет»");
        mainPage.clickPersonalAccount();
        Assertions.assertTrue(loginPage.isLoginPageDisplayed(), "Страница логина не отображается");
        performLogin();
        verifySuccessfulLogin();
    }

    @ParameterizedTest(name = "{1} в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    @DisplayName("Вход через кнопку в форме регистрации")
    @Step("Вход через кнопку в форме регистрации")
    public void testLoginViaRegisterForm(String browser) throws Exception {
        initializeTest(browser, "Вход через кнопку в форме регистрации");
        registerPage.clickLoginButton();
        Assertions.assertTrue(loginPage.isLoginPageDisplayed(), "Страница логина не отображается");
        performLogin();
        verifySuccessfulLogin();
    }

    @ParameterizedTest(name = "{1} в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Step("Вход через кнопку в форме восстановления пароля")
    public void testLoginViaForgotPasswordForm(String browser) throws Exception {
        initializeTest(browser, "Вход через кнопку в форме восстановления пароля");
        forgotPasswordPage.clickLoginLink();
        Assertions.assertTrue(loginPage.isLoginPageDisplayed(), "Страница логина не отображается");
        performLogin();
        verifySuccessfulLogin();
    }
}