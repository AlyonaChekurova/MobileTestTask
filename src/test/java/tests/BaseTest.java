package tests;

import data.BaseData;
import helpers.DriverFactory;
import helpers.ParametersProvider;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Getter;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import page_checkers.MainPageChecker;
import page_checkers.NotePageChecker;
import pages.MainPage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.concurrent.TimeUnit;

/**
 * Базовый тест-класс
 */
@Getter
public abstract class BaseTest implements BaseData {

    /**
     * Android драйвер
     */
    private  AndroidDriver driver;

    /**
     * Метод подготовки драйвера для тестов
     *
     * @return новый драйвер
     * @throws IOException когда файл конфигурации недоступен.
     */
    @BeforeTest(description = "Подготовка драйвера для теста", alwaysRun = true)
    protected void setUpDriver() throws IOException {
        this.driver = DriverFactory.createDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Метод подготовки данных и драйвера для тестов
     *
     * @return новый драйвер.
     */
    @BeforeMethod(description = "Подготовка окружения для теста", alwaysRun = true)
    protected void setUp() throws IOException {
        setUpDriver();

        if (getDriver() == null) {
            System.out.println(DRIVER_NOT_CREATED_ERROR);
        }
        installApp();
        openApp();
    }

    @AfterMethod(alwaysRun = true, description = "Удаление приложения")
    public final void deleteApp() {
        String bundleId = ParametersProvider.getPropertyByName("bundleId");
        if (driver != null) {
            driver.terminateApp(bundleId);
            driver.removeApp(bundleId);
        } else
            System.out.println(DRIVER_NOT_EXIST_ERROR);
    }

    @AfterTest(alwaysRun = true, description = "Удаление драйвера")
    public final void deleteDriver(ITestContext context) {
        if (this.driver != null)
            this.driver.quit();
        else
            System.out.println(DRIVER_NOT_EXIST_ERROR);
    }

    /**
     * Метод установки приложения
     */
    @Step("Установка приложения")
    protected final void installApp() {
        String fileSeparator = FileSystems.getDefault().getSeparator();
        driver.installApp(
                System.getProperty("user.dir")
                        + fileSeparator
                        + ParametersProvider.getPropertyByName("app")
        );
    }

    /**
     * Метод запуска установленного приложения
     */
    @Step("Запуск приложения")
    protected final void openApp() {
        driver.activateApp(ParametersProvider.getPropertyByName("bundleId"));
    }

    /**
     * Метод открытия главного экрана приложения
     * @return
     */
    @Step("Открытие главного экрана приложения")
    protected MainPage openMainNotesPage() {
        return new MainPage(driver);
    }

    public MainPage createNote(String title, String text){
        return openMainNotesPage()
                .addNoteButtonClick()
                .fillInputTitleField(title)
                .fillInputDescriptionField(text)
                .saveNoteButtonSuccessfulClick();
    }
}
