package page_checkers;

import data.NotePageData;
import io.qameta.allure.Step;
import pages.NotePage;

public class NotePageChecker extends BasePageChecker<NotePage> implements NotePageData {

    public NotePageChecker(NotePage page) {
        super(page);
    }

    /**
     * Проверка элементов экрана с созданием заметки.
     */
    @Step("Проверка элементов экрана с созданием заметки")
    public void checkNotePageElements() {
        checkElementPresent("Кнопка-стрелка для возврата на главный экран",
                page.getReturnButton());
        checkDate("Дата", page.getDate(), "dd-MM-yyyy");
        checkElementPresent("Кнопка-галочка сохранения заметки",
                page.getSaveNoteButton());
        checkElementText("Плейсхолдер в поле ввода названия заметки",
                page.getInputFieldPlaceholder(), INPUT_FIELD_PLACEHOLDER);
        checkElementText("Плейсхолдер в текстовом поле для заметки",
                page.getTextFieldPlaceholder(), TEXT_FIELD_PLACEHOLDER);
    }

    /**
     * Проверка вывода сообщения об ошибке с пустым названием.
     */
    @Step("Проверка вывода сообщения об ошибке с пустым названием")
    public void checkNotePageTitleError() {
        checkElementText("Сообщение об ошибке", page.getSnackbarError(), SNACKBAR_TITLE_ERROR);
        checkElementText("Кнопка \"OK\" для подтверждения сообщения об ошибке",
                page.getSnackbarOkButton(), SNACKBAR_OK);
    }

    /**
     * Проверка исчезновения сообщения об ошибке.
     */
    @Step("Проверка исчезновения сообщения об ошибке")
    public void checkNotePageErrorMessageDisappears() {
        checkElementNotPresent("Сообщение об ошибке", page.getSnackbarError());
    }

    /**
     * Проверка ввода строки с различными символами.
     */
    @Step("Проверка ввода строки с различными символами")
    public void checkNoteSomeString() {
        checkElementText("Строка с различными символами", page.getInputFieldPlaceholder(),
                SOME_STRING);
    }

    /**
     * Проверка вывода сообщения об ошибке с пустым описанием.
     */
    @Step("Проверка вывода сообщения об ошибке с пустым описанием")
    public void checkNotePageDescriptionError() {
        checkElementText("Сообщение об ошибке", page.getSnackbarError(),
                SNACKBAR_DESCRIPTION_ERROR);
        checkElementText("Кнопка \"OK\" для подтверждения сообщения об ошибке",
                page.getSnackbarOkButton(), SNACKBAR_OK);
    }
}
