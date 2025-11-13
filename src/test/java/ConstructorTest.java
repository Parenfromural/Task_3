import org.example.pageobjects.MainPage;
import io.qameta.allure.Step;
import org.example.pageobjects.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;

    private final String BASE_URL = "https://stellarburgers.education-services.ru";

    @BeforeEach
    public void setUp(TestInfo testInfo) {
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest(name = "Проверка переходов и заголовков в браузере: {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    @DisplayName("Проверка переходов по вкладкам Соусы, Начинки, Булки и видимости заголовков")
    public void testTabsNavigationAndSectionHeaders(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        mainPage = new MainPage(driver);

        clickSaucesTabAndVerify();
        clickFillingsTabAndVerify();
        clickBunsTabAndVerify();
    }

    @Step("Клик по вкладке «Соусы» и проверка заголовка раздела")
    private void clickSaucesTabAndVerify() {
        mainPage.clickSaucesTab();
        Assertions.assertTrue(mainPage.isSectionHeaderVisible("Соусы"), "Заголовок 'Соусы' должен быть видим после клика");
    }

    @Step("Клик по вкладке «Начинки» и проверка заголовка раздела")
    private void clickFillingsTabAndVerify() {
        mainPage.clickFillingsTab();
        Assertions.assertTrue(mainPage.isSectionHeaderVisible("Начинки"), "Заголовок 'Начинки' должен быть видим после клика");
    }

    @Step("Клик по вкладке «Булки» и проверка заголовка раздела")
    private void clickBunsTabAndVerify() {
        mainPage.clickBunsTab();
        Assertions.assertTrue(mainPage.isSectionHeaderVisible("Булки"), "Заголовок 'Булки' должен быть видим после клика");
    }
}