package helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс с методами ожиданий.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Waiters {

    /**
     * Ожидание видимости элемента.
     *
     * @param webDriver драйвер
     * @param locator   Web element
     */
    public static void waitUntilVisible(final WebDriver webDriver,
                                        final By locator) {
        new WebDriverWait(webDriver, Integer.parseInt(ParametersProvider.getPropertyByName("elementTimeout")))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Ожидание невидимости элемента.
     *
     * @param webDriver драйвер
     * @param locator   Web element
     */
    public static void waitUntilInvisible(final WebDriver webDriver,
                                          final By locator) {
        new WebDriverWait(webDriver, Integer.parseInt(ParametersProvider.getPropertyByName("elementTimeout")))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
