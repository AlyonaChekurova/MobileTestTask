package page_checkers;

import data.BaseData;
import helpers.TestUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.validator.GenericValidator;
import org.openqa.selenium.By;
import pages.BasePage;

import java.util.List;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Базовый класс для проверок экранов
 */
public abstract class BasePageChecker<PAGE extends BasePage> implements BaseData {

    public PAGE page;
    protected AndroidDriver driver;

    public BasePageChecker(PAGE page) {
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
        assertTrue(element.isDisplayed(), format(ELEMENT_NOT_PRESENT_ERROR, elementName) +
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
     * Проверка соответствия заданного текста в каждом элементе из списка
     *
     * @param elementListName название списка элементов
     * @param locator         локатор списка элементов
     * @param text            заданный текст
     */
    protected final void checkElementsContainsText(final String elementListName,
                                                   final By locator,
                                                   final String text) {
        List<AndroidElement> elementList =
                driver.findElements(locator);
        for (AndroidElement element : elementList) {
            assertTrue(element.getText().contains(text),
                    format(ELEMENT_LIST_CONTAINS_TEXT_ERROR, elementListName, element));
        }
    }

    /**
     * Проверка соответствия размера списка элементов ожидаемому
     *
     * @param elementListName название списка элементов
     * @param locator         локатор списка элементов
     * @param size            ожидаемый размер списка элементов
     */
    protected final void checkElementsSize(final String elementListName,
                                           final By locator,
                                           final int size) {
        TestUtils.waitUntilVisible(driver, locator);
        assertEquals(driver.findElements(locator).size(), size,
                format(ELEMENT_LIST_SIZE_ERROR, elementListName));
    }

    /**
     * Проверка отсутствия элемента
     *
     * @param elementName название элемента
     * @param locator     локатор нужного элемента
     */
    protected final void checkElementNotPresent(final String elementName,
                                                final By locator) {
        TestUtils.waitUntilVisible(driver, locator);
        assertTrue(driver.findElements(locator).isEmpty(),
                format(ELEMENT_PRESENT_ERROR, elementName) +
                        format(AFTER_WAITING_TIME_SUFFIX, PROGRESS_BAR_TIMEOUT));
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
        assertTrue(element.getText().equals(expectedText),
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

    /**
     * Проверка соответствия формата даты ожидаемому
     *
     * @param elementName         название элемента
     * @param locator             элемент
     * @param expectedDatePattern ожидаемый формат даты
     */
    protected final void checkDate(final String elementName,
                                   final By locator,
                                   final String expectedDatePattern) {
        checkElementPresent(elementName, locator);
        assertTrue(GenericValidator.isDate(driver.findElement(locator).getText(),
                expectedDatePattern, true), format(INCORRECT_DATE_FORMAT, elementName));
    }
}
