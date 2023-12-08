package pages;

import data.MainNotesPageData;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import page_checkers.MainPageChecker;

import static java.lang.String.format;

/**
 * Класс главного эрана приложения.
 */
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
     * Название заметки
     */
    private final By noteTitle = By.id("com.akshatbhuhagal.mynotes:id/tvTitle");

    /**
     * Текст заметки
     */
    private final By noteText = By.id("com.akshatbhuhagal.mynotes:id/tvDesc");

    /**
     * Дата заметки
     */
    private final By noteDate = By.id("com.akshatbhuhagal.mynotes:id/tvDateTime");

    /**
     * Поле ввода поиска
     */
    private final By searchFieldInput = By.id("com.akshatbhuhagal.mynotes:id/search_src_text");

    /**
     * Кнопка очистки поля поиска
     */
    private final By clearSearchFieldButton = By.id("com.akshatbhuhagal.mynotes:id/search_close_btn");

    /**
     * Список названий заметок
     */
    private final By elementList = By.id("com.akshatbhuhagal.mynotes:id/tvTitle");

    /**
     * Конструктор
     *
     * @param driver
     */
    public MainPage(AndroidDriver driver) {
        super(driver);
    }

    public MainPageChecker getChecker() {
        return new MainPageChecker(this);
    }

    @Step("Нажатие на кнопку создания заметки")
    public NotePage clickAddNoteButton() {
        driver.findElement(addNoteButton).click();
        return new NotePage(getDriver());
    }

    @Step("Ввод названия заметки")
    public MainPage fillSearchFieldInput(String search) {
        driver.findElement(searchFieldInput).sendKeys(search);
        return this;
    }

    @Step("Нажатие на кнопку очистки поля поиска")
    public MainPage clickClearSearchFieldButton() {
        driver.findElement(clearSearchFieldButton).click();
        return this;
    }
}
