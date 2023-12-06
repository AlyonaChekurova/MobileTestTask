package tests;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Класс с тестами приложения
 */
public class NotesScreenTests extends BaseTest{

    @Test(description = "Mob-1: Установка и открытие приложения")
    public void openAppTest() throws IOException {
        setUp();
        openMainNotesPage()
        .checkPage(checker -> checker.checkMainNotesPageElements());
    }
}
