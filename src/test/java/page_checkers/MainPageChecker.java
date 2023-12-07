package page_checkers;

import data.MainNotesPageData;
import io.qameta.allure.Step;
import pages.MainPage;

public class MainPageChecker extends BasePageChecker<MainPage> implements MainNotesPageData {

    /**
     * Конструктор
     *
     * @param page
     */
    public MainPageChecker(MainPage page) {
        super(page);
    }

    /**
     * Проверка элементов главного экрана с заметками
     */
    @Step("Проверка элементов главного экрана с заметками")
    public void checkMainNotesPageElements() {
        checkElementText("Заголовок 'Notes'", page.getNotesTitle(), NOTES_TITLE);
        checkElementText("Заголовок 'Tasks'", page.getTasksTitle(), TASKS_TITLE);
        checkElementPresent("Поле поиска", page.getSearchField());
        checkElementText("Плейсхолдер в поле поиска", page.getSearchFieldPlaceholder(),
                SEARCH_FIELD_PLACEHOLDER);
        checkElementPresent("Кнопка создания заметки", page.getAddNoteButton());
    }

    /**
     * Проверка элементов главного экрана с созданной заметкой
     */
    @Step("Проверка элементов главного экрана с созданной заметкой")
    public void checkNewNotesPageElements() {
        checkElementText("Название заметки", page.getNoteTitle(), SOME_STRING);
        checkElementText("Текст заметки", page.getNoteText(), SOME_STRING);
        checkElementPresent("Дата заметки", page.getNoteDate());
    }

    /**
     * Проверка элементов главного экрана с введенным текстом в поле поиска
     */
    @Step("Проверка элементов главного экрана с введенным текстом в поле поиска")
    public void checkSearchFieldPageElements() {
        checkElementPresent("Кнопка очистки поля поиска", page.getClearSearchFieldButton());
        checkElementsContainsText("Список заметок", page.getElementList(),
                MainNotesPageData.NOTES_TITLE_LIST);
    }

    /**
     * Проверка количества элементов главного экрана с очищенным полем поиска
     */
    @Step("Проверка количества элементов главного экрана с очищенным полем поиска")
    public void checkClearedSearchFieldPageElements() {
        checkElementsSize("Список заметок", page.getElementList(), 3);
    }
}
