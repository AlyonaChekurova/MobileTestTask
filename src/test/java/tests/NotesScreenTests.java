package tests;

import data.MainNotesPageData;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import page_checkers.MainPageChecker;
import page_checkers.NotePageChecker;

import java.io.IOException;

/**
 * Класс с тестами приложения
 */
@Epic("Тесты заметок")
public class NotesScreenTests extends BaseTest{

    @Test(description = "Mob-1: Установка и открытие приложения")
    public void openAppTest() throws IOException {
        setUp();
        openMainNotesPage()
        .checkPage(MainPageChecker::checkMainNotesPageElements);
    }

    @Test(description = "Mob-2: Создание новой заметки")
    public void createNoteTest() throws IOException {
        openMainNotesPage()
                .addNoteButtonClick()
                .checkPage(NotePageChecker::checkNotePageElements)
                .saveNoteButtonClick()
                .checkPage(NotePageChecker::checkNotePageTitleError)
                .snackbarOkButtonClick()
                .checkPage(NotePageChecker::checkNotePageErrorMessageDisappears)
                .fillInputTitleField(SOME_STRING)
                .checkPage(NotePageChecker::checkNoteSomeString)
                .saveNoteButtonClick()
                .checkPage(NotePageChecker::checkNotePageDescriptionError)
                .snackbarOkButtonClick()
                .checkPage(NotePageChecker::checkNotePageErrorMessageDisappears)
                .fillInputDescriptionField(SOME_STRING)
                .checkPage(NotePageChecker::checkNoteSomeString)
                .saveNoteButtonSuccessfulClick()
                .checkPage(MainPageChecker::checkNewNotesPageElements);
    }

    @Test(description = "Mob-3: Поле поиска")
    public void searchFieldTest() throws IOException {
        createNote("one", "text1");
        createNote("onetwo", "text2");
        createNote("onetwothree", "text3");

        openMainNotesPage()
                .fillSearchFieldInput(MainNotesPageData.NOTES_TITLE_LIST)
                .checkPage(MainPageChecker::checkSearchFieldPageElements)
                .clearSearchFieldButtonClick()
                .checkPage(MainPageChecker::checkClearedSearchFieldPageElements);
    }
}
