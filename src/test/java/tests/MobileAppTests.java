package tests;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Класс с тестами приложения
 */
public class MobileAppTests extends BaseTest{

    @Test
    public void openAppTest() throws IOException {
        setUp();
        openMainNotesPage()
        .checkPage(checker -> checker.checkMainNotesPageElements());
    }
}
