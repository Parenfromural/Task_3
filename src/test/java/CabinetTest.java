import org.example.pageobjects.AccountPage;
import org.example.pageobjects.LoginPage;
import org.example.pageobjects.MainPage;
import io.qameta.allure.Step;
import org.example.pageobjects.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CabinetTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountPage accountPage;

    private final String BASE_URL = "https://stellarburgers.education-services.ru";

    private String testEmail;
    private final String testPassword = "validPassword123";
    private String accessToken;

    private ApiUserClient apiUserClient;

    @BeforeAll
    public void initApiClient() {
        apiUserClient = new ApiUserClient(BASE_URL);
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) throws Exception {
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

    @Step("Авторизоваться через главную страницу")
    private void loginViaMain() {
        mainPage.clickLoginButton();
        loginPage.enterEmail(testEmail);
        loginPage.enterPassword(testPassword);
        loginPage.clickLoginButton();
    }

    @Step("Проверить редирект на главную страницу")
    private void verifyRedirectToMain() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean redirected = wait.until(d -> d.getCurrentUrl().equals(BASE_URL + "/"));
        Assertions.assertTrue(redirected, "Не произошло перенаправление на главную страницу после логина");
    }

    private void initializeTest(String browser) throws Exception {
        testEmail = "user_" + UUID.randomUUID() + "@yandex.com";

        ApiUserClient.UserTokens tokens = apiUserClient.registerUser(testEmail, testPassword, "Test User");
        accessToken = tokens.getAccessToken();

        driver = WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        driver.get(BASE_URL + "/");

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
    }

    @ParameterizedTest(name = "Тест авторизации и личного кабинета в браузере: {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    @Step("Авторизация через главную и переход в личный кабинет")
    public void testLoginAndOpenAccount(String browser) throws Exception {
        initializeTest(browser);

        loginViaMain();
        verifyRedirectToMain();
        mainPage.clickPersonalAccount();
        Assertions.assertTrue(accountPage.isInfoTextDisplayed(), "Страница личного кабинета не отображается");
    }

    @ParameterizedTest(name = "Тест перехода на главную через логотип из личного кабинета в браузере: {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    @Step("Переход на главную по клику на логотип из личного кабинета")
    public void testNavigateToMainViaLogoFromAccount(String browser) throws Exception {
        initializeTest(browser);

        loginViaMain();
        verifyRedirectToMain();
        mainPage.clickPersonalAccount();
        accountPage.clickHeaderLogo();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean redirected = wait.until(d -> d.getCurrentUrl().equals(BASE_URL + "/"));
        Assertions.assertTrue(redirected, "Не произошло перенаправление на главную страницу после клика на логотип из личного кабинета");
    }

    @ParameterizedTest(name = "Тест выхода из аккаунта в браузере: {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    @Step("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void testLogoutFromAccount(String browser) throws Exception {
        initializeTest(browser);

        loginViaMain();
        verifyRedirectToMain();
        mainPage.clickPersonalAccount();
        accountPage.clickLogoutButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean redirected = wait.until(d -> d.getCurrentUrl().equals(BASE_URL + "/") || d.getCurrentUrl().contains("/login"));
        Assertions.assertTrue(redirected, "После выхода из аккаунта не произошло перенаправление на главную или страницу логина");
    }
}