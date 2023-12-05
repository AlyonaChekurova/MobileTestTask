package page_checkers;

import data.BaseData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.BasePage;

import static java.lang.String.format;

/**
 * Базовый класс для проверок экранов
 */
public abstract class BasePageChecker<PAGE extends BasePage> implements BaseData {
    protected AndroidDriver driver;
    public PAGE page;

    public BasePageChecker(PAGE page){
        this.page = page;
        this.driver = page.getDriver();
    }

    /**
     * Проверка отображения элемента
     *
     * @param elementName название элемента
     * @param element     нужный элемент
     */
    protected final void checkElementPresent(final String elementName,
                                             final AndroidElement element) {
        Assert.assertTrue(element.isDisplayed(), format(ELEMENT_NOT_PRESENT_ERROR, elementName) +
                format(AFTER_WAITING_TIME_SUFFIX, PROGRESS_BAR_TIMEOUT));
    }

    /**
     * Проверка отображения элемента
     *
     * @param elementName название элемента
     * @param locator     локатор нужного элемента
     */
    protected final void checkElementPresent(final String elementName,
                                             final By locator) {
        checkElementPresent(elementName, (AndroidElement) driver.findElement(locator));
    }

    /**
     * Проверка соответствия текста элемента ожидаемому
     *
     * @param elementName  название элемента
     * @param element      элемент
     * @param expectedText ожидаемый текст
     */
    protected final void checkElementText(final String elementName,
                                          final AndroidElement element,
                                          final String expectedText) {
        checkElementPresent(elementName, element);
        Assert.assertTrue(element.getText().equals(expectedText),
                format(TEXTS_NOT_EQUAL_ERROR, elementName, element.getText(), expectedText));
    }

    /**
     * Проверка соответствия текста элемента ожидаемому
     *
     * @param elementName  название элемента
     * @param locator      элемент
     * @param expectedText ожидаемый текст
     */
    protected final void checkElementText(final String elementName,
                                          final By locator,
                                          final String expectedText) {
        checkElementPresent(elementName, locator);
        checkElementText(elementName, (AndroidElement) driver.findElement(locator), expectedText);
    }
}
