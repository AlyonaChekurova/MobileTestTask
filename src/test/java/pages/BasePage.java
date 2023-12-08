package pages;

import data.BaseData;
import helpers.ParametersProvider;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.openqa.selenium.Dimension;
import page_checkers.BasePageChecker;

import java.util.function.Consumer;

/**
 * Базовый класс эрана приложения.
 */
@Getter
public abstract class BasePage<PAGE extends BasePage, CHECKER extends BasePageChecker> implements BaseData {

    /**
     * Время ожидания открытия экрана
     */
    protected final static int pageTimeout =
            Integer.valueOf(ParametersProvider.getPropertyByName("explicitTimeout")) * 1000;
    protected final String byTextLocator = "//*[@text='%s']";
    /**
     * Android драйвер
     */
    protected AndroidDriver driver;
    /**
     * Разрешение экрана
     */
    private Dimension window;
    private CHECKER checker;

    /**
     * Конструктор класса.
     *
     * @param driver драйвер
     */
    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        window = driver.manage().window().getSize();
    }

    /**
     * Получение размера окна устройства
     *
     * @return размер окна устройства
     */
    public Dimension getWindowSize() {
        return window;
    }

    /**
     * Вызов блока проверок для страницы. В качестве класса с проверками используется проинициализированный
     * по умолчанию класс в Page
     *
     * @param consumers - содержит методы проверок, которые нужно вызвать
     * @return текущая страница
     */
    @SafeVarargs
    public final PAGE checkPage(Consumer<CHECKER>... consumers) {
        CHECKER checker = getChecker();
        for (Consumer<CHECKER> consumer : consumers) {
            consumer.accept(checker);
        }
        return (PAGE) this;
    }

    protected CHECKER getChecker() {
        return checker;
    }
}
