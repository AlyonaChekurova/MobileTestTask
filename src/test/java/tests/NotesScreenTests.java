package tests;

import data.MainNotesPageData;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import page_checkers.MainPageChecker;
import page_checkers.NotePageChecker;
import pages.MainPage;

import java.io.IOException;

/**
 * Класс с тестами приложения
 */
@Epic("Тесты заметок")
public class NotesScreenTests extends BaseTest {

    public MainPage createNote(String title, String text) {
        return openMainNotesPage()
                .clickAddNoteButton()
                .fillInputTitleField(title)
                .fillInputDescriptionField(text)
                .clickSaveNoteButtonSuccessful();
    }

    @Test(description = "Mob-1: Установка и открытие приложения")
    public void openAppTest() throws IOException {
        setUp();
        openMainNotesPage()
                .checkPage(MainPageChecker::checkMainNotesPageElements);
    }

    @Test(description = "Mob-2: Создание новой заметки")
    public void createNoteTest() {
        openMainNotesPage()
                .clickAddNoteButton()
                .checkPage(NotePageChecker::checkNotePageElements)
                .clickSaveNoteButton()
                .checkPage(NotePageChecker::checkNotePageTitleError)
                .clickSnackbarOkButton()
                .checkPage(NotePageChecker::checkNotePageErrorMessageDisappears)
                .fillInputTitleField(SOME_STRING)
                .checkPage(NotePageChecker::checkNoteSomeString)
                .clickSaveNoteButton()
                .checkPage(NotePageChecker::checkNotePageDescriptionError)
                .clickSnackbarOkButton()
                .checkPage(NotePageChecker::checkNotePageErrorMessageDisappears)
                .fillInputDescriptionField(SOME_STRING)
                .checkPage(NotePageChecker::checkNoteSomeString)
                .clickSaveNoteButtonSuccessful()
                .checkPage(MainPageChecker::checkNewNotesPageElements);
    }

    @Test(description = "Mob-3: Поле поиска. Совпадение при поиске найдено")
    public void searchFieldMatchFoundTest() {
        int size = 3;
        for (int i = 0; i < size; i++) {
            createNote("one" + i, "text" + i);
        }

        openMainNotesPage()
                .fillSearchFieldInput(MainNotesPageData.NOTES_TITLE_LIST_MATCH_FOUND)
                .checkPage(MainPageChecker::checkSearchFieldPageElements)
                .clickClearSearchFieldButton()
                .checkPage(checker -> checker.checkClearedSearchFieldPageElements(size));
    }

    @Test(description = "Mob-3: Поле поиска. Совпадение при поиске не найдено")
    public void searchFieldMatchNotFoundTest() {
        int size = 3;
        for (int i = 0; i < size; i++) {
            createNote("one" + i, "text" + i);
        }

        openMainNotesPage()
                .fillSearchFieldInput(MainNotesPageData.NOTES_TITLE_LIST_MATCH_NOT_FOUND)
                .checkPage(MainPageChecker::checkSearchFieldNoMatch)
                .clickClearSearchFieldButton()
                .checkPage(checker -> checker.checkClearedSearchFieldPageElements(size));
    }
}
