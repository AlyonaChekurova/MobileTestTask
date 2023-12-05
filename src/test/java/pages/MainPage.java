package pages;

import data.MainNotesPageData;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.openqa.selenium.By;
import page_checkers.MainPageChecker;

import static java.lang.String.format;

@Getter
public class MainPage extends BasePage<MainPage, MainPageChecker> implements MainNotesPageData {

    /**
     * Поле поиска
     */
    private final By searchField = By.id("com.akshatbhuhagal.mynotes:id/search_view");

    /**
     * Плейсхолдер в поле поиска
     */
    private final By searchFieldPlaceholder = By.id("com.akshatbhuhagal.mynotes:id/search_src_text");

    /**
     * Заголовок 'Notes'
     */
    private final By notesTitle = By.xpath(format(byTextLocator, NOTES_TITLE));

    /**
     * Заголовок 'Tasks'
     */
    private final By tasksTitle = By.xpath(format(byTextLocator, TASKS_TITLE));

    /**
     * Кнопка создания заметки
     */
    private final By addNoteButton = By.id("com.akshatbhuhagal.mynotes:id/fabCreateNoteBtn");

    /**
     * Конструктор
     *
     * @param driver
     */
    public MainPage(AndroidDriver driver){
        super(driver);
    }

    public MainPageChecker getChecker() {
        return new MainPageChecker(this);
    }
}
