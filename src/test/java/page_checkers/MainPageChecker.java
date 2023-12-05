package page_checkers;

import data.MainNotesPageData;
import pages.MainPage;

public class MainPageChecker extends BasePageChecker<MainPage> implements MainNotesPageData {
    /**
     * Конструктор
     *
     * @param page
     */
    public MainPageChecker(MainPage page) {super(page);}

    /**
     * Проверка элементов главного экрана с заметками
     */
    public void checkMainNotesPageElements() {
        checkElementText("Заголовок 'Notes'", page.getNotesTitle(), NOTES_TITLE);
        checkElementText("Заголовок 'Tasks'", page.getTasksTitle(), TASKS_TITLE);
        checkElementPresent("Поле поиска", page.getSearchField());
        checkElementText("Плейсхолдер в поле поиска", page.getSearchFieldPlaceholder(), SEARCH_FIELD_PLACEHOLDER);
        checkElementPresent("Кнопка создания заметки", page.getAddNoteButton());
    }
}
