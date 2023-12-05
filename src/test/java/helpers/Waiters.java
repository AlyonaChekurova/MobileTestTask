package helpers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static data.BaseData.ELEMENT_NOT_PRESENT_ERROR;
import static java.lang.String.format;

/**
 * Вспомогательный класс для ожиданий
 */
public class Waiters {
    private final static int TIMEOUT = Integer.valueOf(ParametersProvider.getPropertyByName("explicitTimeout"));

    private Waiters() {}

    /**
     * Ожидание пока элемент не станет видимым
     *
     * @param driver Android драйвер
     * @param element
     */
    public static void waitUntilElementVisible(final AndroidDriver driver, final AndroidElement element) {
        try {
            new WebDriverWait(driver, TIMEOUT).
                    until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            e.printStackTrace();
            Assert.fail(format(ELEMENT_NOT_PRESENT_ERROR, element)); }
    }

    /**
     * Ожидание пока элемент не станет видимым
     *
     * @param driver Android драйвер
     * @param locator элемента
     */
    public static void waitUntilElementVisible(final AndroidDriver driver, final By locator) {
        try {
            new WebDriverWait(driver, TIMEOUT).
                    until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            e.printStackTrace();
            Assert.fail(format(ELEMENT_NOT_PRESENT_ERROR, locator)); }
    }
    /**
     * Ожидание пока активити не появится.
     *
     * @param driver андроид драйвер
     * @param activity название ожидаемой Активити
     * @param timeout значение в properties
     * @ когда поток был внезапно остановлен
     */
    public static void waitUntilActivityExists(final AndroidDriver driver, final String activity, String timeout)
    {
        int secondsCounter = 0;
        int timeoutValue = Integer.valueOf(ParametersProvider.getPropertyByName(timeout));
        while (!driver.currentActivity().contains(activity) && secondsCounter < timeoutValue) {
            ;
            secondsCounter++;
        }
        if (!driver.currentActivity().contains(activity)) {
            throw new TimeoutException("Activity is not presented after " + timeout + " seconds");
        }
    }
}
