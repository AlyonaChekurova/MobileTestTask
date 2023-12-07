package helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Утилитный класс.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

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
}
