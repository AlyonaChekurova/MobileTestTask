package pages;

import data.NotePageData;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import page_checkers.NotePageChecker;

/**
 * Класс эрана заметок приложения.
 */
@Getter
public class NotePage extends BasePage<NotePage, NotePageChecker> implements NotePageData {

    /**
     * Кнопка-стрелка для возврата на главный экран.
     */
    private final By returnButton = By.id("com.akshatbhuhagal.mynotes:id/imgBack");

    /**
     * Дата.
     */
    private final By date = By.id("com.akshatbhuhagal.mynotes:id/tvDateTime");

    /**
     * Кнопка-галочка сохранения заметки.
     */
    private final By saveNoteButton = By.id("com.akshatbhuhagal.mynotes:id/imgDone");

    /**
     * Поле ввода названия заметки с плейсхолдером "Notes Title".
     */
    private final By inputFieldPlaceholder = By.id("com.akshatbhuhagal.mynotes:id/etNoteTitle");

    /**
     * Текстовое поле для заметки с плейсхолдером "Enter Notes Here".
     */
    private final By textFieldPlaceholder = By.id("com.akshatbhuhagal.mynotes:id/etNoteDesc");

    /**
     * Сообщение об ошибке с текстом "Title is Required".
     */
    private final By snackbarError = By.id("com.akshatbhuhagal.mynotes:id/snackbar_text");

    /**
     * Кнопка "OK" для подтверждения сообщения об ошибке.
     */
    private final By snackbarOkButton = By.id("com.akshatbhuhagal.mynotes:id/snackbar_action");

    /**
     * Конструктор класса.
     *
     * @param driver драйвер
     */
    public NotePage(AndroidDriver driver) {
        super(driver);
    }

    public NotePageChecker getChecker() {
        return new NotePageChecker(this);
    }

    @Step("Нажатие на кнопку сохранения заметки")
    public NotePage clickSaveNoteButton() {
        driver.findElement(saveNoteButton).click();
        return this;
    }

    @Step("Нажатие на кнопку сохранения заметки")
    public MainPage clickSaveNoteButtonSuccessful() {
        driver.findElement(saveNoteButton).click();
        return new MainPage(driver);
    }

    @Step("Нажатие на кнопку \"OK\" для подтверждения сообщения об ошибке")
    public NotePage clickSnackbarOkButton() {
        driver.findElement(snackbarOkButton).click();
        return this;
    }

    @Step("Ввод названия заметки")
    public NotePage fillInputTitleField(String title) {
        driver.findElement(inputFieldPlaceholder).sendKeys(title);
        return this;
    }

    @Step("Ввод названия заметки")
    public NotePage fillInputDescriptionField(String description) {
        driver.findElement(textFieldPlaceholder).sendKeys(description);
        return this;
    }
}
